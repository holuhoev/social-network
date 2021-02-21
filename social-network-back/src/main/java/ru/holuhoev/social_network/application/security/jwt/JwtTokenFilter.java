package ru.holuhoev.social_network.application.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ru.holuhoev.social_network.core.domain.enums.AppErrorCode;
import ru.holuhoev.social_network.web.dto.base.BaseDTO;
import ru.holuhoev.social_network.web.dto.base.ErrorDTO;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * JWT token filter that handles all HTTP requests to application.
 */
@AllArgsConstructor
public class JwtTokenFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(
            final ServletRequest req,
            final ServletResponse res,
            final FilterChain filterChain
    ) throws IOException, ServletException {

        final String token = jwtTokenProvider.extractToken((HttpServletRequest) req);

        if (token != null) {
            try {
                jwtTokenProvider.validateToken(token);
            } catch (final ExpiredJwtException e) {
                final OutputStream out = res.getOutputStream();
                final ObjectMapper mapper = new ObjectMapper();

                res.setContentType("application/json");
                ((HttpServletResponse) res).setStatus(HttpStatus.UNAUTHORIZED.value());

                mapper.writeValue(
                        out,
                        BaseDTO.error(new ErrorDTO(
                                RandomStringUtils.randomAlphabetic(10),
                                AppErrorCode.TOKEN_EXPIRED.name(),
                                AppErrorCode.TOKEN_EXPIRED.getDescription()

                        ))
                );

                out.flush();

                return;
            }

            final Authentication auth = jwtTokenProvider.getAuthentication(token);

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(req, res);
    }

}
