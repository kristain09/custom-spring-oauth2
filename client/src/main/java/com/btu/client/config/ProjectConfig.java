package com.btu.client.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.oauth2Client(Customizer.withDefaults());

        httpSecurity.authorizeRequests(
                auth -> auth.anyRequest().permitAll()
        );

        return httpSecurity.build();
    }

    @Bean
    public OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository) {

        OAuth2AuthorizedClientProvider provider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .authorizationCode()
                        .refreshToken()
                        .clientCredentials()
                        .build();

        DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager
                = new DefaultOAuth2AuthorizedClientManager
                (clientRegistrationRepository, oAuth2AuthorizedClientRepository);
        defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);

        return defaultOAuth2AuthorizedClientManager;
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration client = ClientRegistration
                .withRegistrationId("1")
                .clientId("client")
                .clientSecret("secret")
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .tokenUri("http://localhost:8080/oauth2/token")
                .scope(OidcScopes.OPENID)
                .build();

        InMemoryClientRegistrationRepository clientRegistrationRepository =
                new InMemoryClientRegistrationRepository(client);
        return clientRegistrationRepository;
    }
}
