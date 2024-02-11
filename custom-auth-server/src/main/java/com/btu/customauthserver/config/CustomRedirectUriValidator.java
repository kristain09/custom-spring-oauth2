package com.btu.customauthserver.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationContext;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.function.Consumer;

public class CustomRedirectUriValidator implements Consumer<OAuth2AuthorizationCodeRequestAuthenticationContext> {

    @Override
    public void accept(OAuth2AuthorizationCodeRequestAuthenticationContext context) {
        OAuth2AuthorizationCodeRequestAuthenticationToken auth = context.getAuthentication();
        RegisteredClient registeredClient = context.getRegisteredClient();
        String redirectUris = auth.getRedirectUri();
        if (!registeredClient.getRedirectUris().contains(redirectUris)) {
            var error = new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST);
            throw new OAuth2AuthenticationException(error, "invalid redirect uri");
        }
    }
}
