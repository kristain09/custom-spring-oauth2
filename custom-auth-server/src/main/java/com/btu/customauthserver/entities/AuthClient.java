package com.btu.customauthserver.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Set;


@Entity
@Table(name = "m_auth_client", schema = "\"authorization\"")
public class AuthClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "client_id", nullable = false, length = Integer.MAX_VALUE)
    private String clientId;

    @Column(name = "secret", nullable = false, length = Integer.MAX_VALUE)
    private String secret;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_client_authentication_method",
            schema = "authorization",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "authentication_id")
    )
    private Set<AuthenticationMethod> authenticationMethods;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_client_grant_type",
            schema = "authorization",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "grant_type_id")
    )
    private Set<GrantType> grantTypes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_client_redirect_uri",
            schema = "authorization",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "redirect_uri_id")
    )
    private Set<RedirectUri> redirectUris;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Set<AuthenticationMethod> getAuthenticationMethods() {
        return authenticationMethods;
    }

    public void setAuthenticationMethods(Set<AuthenticationMethod> authenticationMethods) {
        this.authenticationMethods = authenticationMethods;
    }

    public Set<GrantType> getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(Set<GrantType> grantTypes) {
        this.grantTypes = grantTypes;
    }

    public Set<RedirectUri> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(Set<RedirectUri> redirectUris) {
        this.redirectUris = redirectUris;
    }
}