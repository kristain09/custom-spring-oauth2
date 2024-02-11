package com.btu.customauthserver.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "m_grant_types", schema = "\"authorization\"")
public class GrantType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "grant_type", nullable = false, length = Integer.MAX_VALUE)
    private String grantType;

    @ManyToMany(mappedBy = "grantTypes")
    private Set<AuthClient> clients;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public Set<AuthClient> getClients() {
        return clients;
    }

    public void setClients(Set<AuthClient> clients) {
        this.clients = clients;
    }
}