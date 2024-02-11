package com.btu.customauthserver.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "m_authentication_method", schema = "\"authorization\"")
public class AuthenticationMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "method", nullable = false, length = Integer.MAX_VALUE)
    private String method;

    @ManyToMany(mappedBy = "authenticationMethods")
    private Set<AuthClient> clients;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Set<AuthClient> getClients() {
        return clients;
    }

    public void setClients(Set<AuthClient> clients) {
        this.clients = clients;
    }
}