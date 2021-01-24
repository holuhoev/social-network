package ru.holuhoev.social_network.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.holuhoev.social_network.application.security.jwt.JwtTokenProvider;
import ru.holuhoev.social_network.core.domain.entity.User;
import ru.holuhoev.social_network.core.usecase.FindUser;
import ru.holuhoev.social_network.web.dto.AccessTokenDTO;
import ru.holuhoev.social_network.web.dto.base.BaseDTO;
import ru.holuhoev.social_network.web.dto.input.LoginInputDTO;

import javax.annotation.Nonnull;
import java.util.Optional;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final FindUser findUser;

    @PostMapping
    public ResponseEntity<BaseDTO<AccessTokenDTO>> login(@RequestBody @Nonnull final LoginInputDTO loginInput) {

        final String username = loginInput.getUsername();
        final String password = loginInput.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        final Optional<User> userOpt = findUser.loadOptUserByUsername(username);

        if (!userOpt.isPresent()) {
            throw new BadCredentialsException("Bad credentials");
        }

        final String accessToken = jwtTokenProvider.createToken(userOpt.get());

        return ResponseEntity.ok(BaseDTO.success(new AccessTokenDTO(accessToken)));
    }
}
