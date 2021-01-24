package ru.holuhoev.social_network.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.holuhoev.social_network.application.security.jwt.JwtTokenConfigurationProperties;

@SpringBootApplication(
        scanBasePackages = {"ru.holuhoev.social_network"}
)
@EnableConfigurationProperties(value = {
        JwtTokenConfigurationProperties.class
})
public class SocialNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialNetworkApplication.class, args);
    }

}
