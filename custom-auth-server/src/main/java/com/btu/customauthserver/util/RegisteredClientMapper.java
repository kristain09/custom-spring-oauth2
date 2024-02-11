package com.btu.customauthserver.util;

import ch.qos.logback.core.net.server.Client;
import com.btu.customauthserver.entities.AuthClient;
import com.btu.customauthserver.entities.AuthenticationMethod;
import com.btu.customauthserver.entities.GrantType;
import com.btu.customauthserver.entities.RedirectUri;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegisteredClientMapper {

  public static AuthClient fromRegisteredClient(RegisteredClient regClient) {
    AuthClient authClient = new AuthClient();
    authClient.setAuthenticationMethods(fromClientAuthorizationMethod(regClient.getClientAuthenticationMethods()));
    authClient.setRedirectUris(fromRegisteredClientUris(regClient.getRedirectUris()));
    authClient.setGrantTypes(fromAuthorizationGrantType(regClient.getAuthorizationGrantTypes()));
    authClient.setClientId(regClient.getClientId());
    authClient.setSecret(regClient.getClientSecret());
    return authClient;
  }

  private static Set<GrantType> fromAuthorizationGrantType(Set<AuthorizationGrantType> authorizationGrantTypes) {
    Set<GrantType> grantTypes = new HashSet<>();

    for (AuthorizationGrantType authorizationGrantType : authorizationGrantTypes) {
      GrantType grantType = new GrantType();
      grantType.setGrantType(authorizationGrantType.getValue());
      grantTypes.add(grantType);
    }

    return grantTypes;
  }

  private static Set<RedirectUri> fromRegisteredClientUris(Set<String> uris) {
    Set<RedirectUri> redirectUris = new HashSet<>();
    uris.forEach(
        uri -> {
          RedirectUri redirectUri = new RedirectUri();
          redirectUri.setUrl(uri);
          redirectUris.add(redirectUri);
        }
    );
    return redirectUris;
  }

  private static Set<AuthenticationMethod> fromClientAuthorizationMethod
      (Set<ClientAuthenticationMethod> clientAuthenticationMethods) {

    Set<AuthenticationMethod> authenticationMethods = new HashSet<>();

    clientAuthenticationMethods.forEach(auth -> {
      AuthenticationMethod authenticationMethod = new AuthenticationMethod();
      authenticationMethod.setMethod(auth.getValue());
      authenticationMethods.add(authenticationMethod);
    });

    return authenticationMethods;
  }

  public static RegisteredClient toRegisteredClient(AuthClient authClient) {
    return RegisteredClient
        .withId(String.valueOf(authClient.getId()))
        .clientId(authClient.getClientId())
        .clientSecret(authClient.getSecret())
        .redirectUris(uri -> {
          Set<String> collect = authClient
              .getRedirectUris()
              .stream().map(RedirectUri::getUrl).collect(Collectors.toSet());
          for (String uriDb : collect) {
            uri.add(uriDb);
          }
        })
        .authorizationGrantTypes(
            grant -> {
              authClient.getGrantTypes()
                  .forEach(grantType -> {
                    grant.add(new AuthorizationGrantType(grantType.getGrantType()));
                  });
            }
        )
        .clientAuthenticationMethods(
            authMethod -> {
              authClient.getAuthenticationMethods()
                  .forEach(auth -> authMethod.add(
                      new ClientAuthenticationMethod(auth.getMethod())));
            }
        )
        .build();
  }

}

