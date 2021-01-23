package ru.holuhoev.social_network.application.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.holuhoev.social_network.core.domain.entity.User;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * Util class that provides methods for generation, validation, etc. of JWT token.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtTokenConfigurationProperties properties;
    private final UserDetailsService userDetailsService;


    private String secret;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(properties.getSecret().getBytes());
    }

    public String createToken(final User user) {

        final Claims claims = Jwts.claims().setSubject(user.getUsername());

        claims.put("user_id", user.getUserId());
        claims.put("first_name", user.getFirstName());
        claims.put("last_name", user.getLastName());

        final Date now = new Date();
        final Date expiredDate = new Date(now.getTime() + properties.getExpireInMillis());

        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuedAt(now)
                   .setExpiration(expiredDate)
                   .signWith(SignatureAlgorithm.HS256, secret)
                   .compact();
    }

    Authentication getAuthentication(final String token) {
        final UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    String getUsername(final String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    String extractToken(final HttpServletRequest req) {
        return req.getHeader("Authorization");
    }

    void validateToken(final String token) {

        final Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

        if (claims.getBody().getExpiration().before(new Date())) {
            throw new IllegalArgumentException("Token is expired");
        }
    }
}
