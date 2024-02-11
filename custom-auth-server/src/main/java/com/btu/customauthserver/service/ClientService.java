package com.btu.customauthserver.service;

import com.btu.customauthserver.entities.AuthClient;
import com.btu.customauthserver.repositories.AuthClientRepository;
import com.btu.customauthserver.util.RegisteredClientMapper;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements RegisteredClientRepository {

    private final AuthClientRepository clientRepository;

    public ClientService(AuthClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        AuthClient authClient = RegisteredClientMapper.fromRegisteredClient(registeredClient);
        clientRepository.save(authClient);
    }

    @Override
    public RegisteredClient findById(String id) {
        AuthClient authClient = clientRepository.findById(Long.parseLong(id)).orElseThrow();
        return RegisteredClientMapper.toRegisteredClient(authClient);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        AuthClient authClient = clientRepository.findAuthClientByClientId(clientId).orElseThrow(
            () -> new OAuth2AuthenticationException(
                new OAuth2Error((OAuth2ErrorCodes.INVALID_REQUEST))
            )
        );
        return RegisteredClientMapper.toRegisteredClient(authClient);
    }
}
