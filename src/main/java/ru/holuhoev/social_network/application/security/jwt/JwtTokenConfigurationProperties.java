package ru.holuhoev.social_network.application.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "jwt.token")
public class JwtTokenConfigurationProperties {

    private String secret;
    private long expireInMillis;
}
