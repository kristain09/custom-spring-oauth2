package com.btu.resourceserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${jwksUri}")
    private String jwksUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.oauth2ResourceServer(
                configurer -> configurer.jwt(
                        jwtConfigurer -> jwtConfigurer.jwkSetUri(jwksUri)
                                .jwtAuthenticationConverter(new CustomJwtAuthenticationTokenConverter())
                )
        );
        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        return httpSecurity.build();
    }
}
