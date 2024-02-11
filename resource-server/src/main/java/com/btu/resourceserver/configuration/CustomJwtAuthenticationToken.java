package com.btu.resourceserver.configuration;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class CustomJwtAuthenticationToken extends JwtAuthenticationToken {

    public CustomJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
        super(jwt, authorities);
    }

    public CustomJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities, String name) {
        super(jwt, authorities, name);
    }
}
