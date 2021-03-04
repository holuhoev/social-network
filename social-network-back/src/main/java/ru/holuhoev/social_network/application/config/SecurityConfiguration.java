package ru.holuhoev.social_network.application.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.holuhoev.social_network.application.security.jwt.JwtConfigurerAdapter;
import ru.holuhoev.social_network.application.security.jwt.JwtTokenProvider;

/**
 * Security configuration class for JWT based Spring Security application.
 */
@Configuration
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String SWAGGER_API = "/v2/api-docs/**";
    private static final String SWAGGER_UI = "/webjars/springfox-swagger-ui/**";
    private static final String SWAGGER_UI_HTML = "/swagger-ui.html/**";
    private static final String SWAGGER_RESOURCES = "/swagger-resources/**";
    private static final String CREATE_USER = "/api/users/register";
    private static final String LOGIN_ENDPOINT = "/api/login";
    private static final String ACTUATOR = "/actuator/**";
    private static final String CONFIG_PROPS = "/configprops";

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        final String[] authWhiteList = new String[]{
                LOGIN_ENDPOINT,
                CREATE_USER,
                SWAGGER_API,
                SWAGGER_UI,
                SWAGGER_UI_HTML,
                SWAGGER_RESOURCES,
                ACTUATOR,
                CONFIG_PROPS,
                "/users/search**"
        };


        http.httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(authWhiteList).permitAll()
            .anyRequest().authenticated()
            .and()
            .apply(new JwtConfigurerAdapter(jwtTokenProvider));
    }
}
