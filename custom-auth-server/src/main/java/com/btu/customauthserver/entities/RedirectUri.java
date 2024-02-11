package com.btu.customauthserver.entities;

import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "m_redirect_uris", schema = "\"authorization\"")
public class RedirectUri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "url", nullable = false, length = Integer.MAX_VALUE)
    private String url;

    @ManyToMany(mappedBy = "redirectUris")
    private Set<AuthClient> clients;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public Set<AuthClient> getClients() {
        return clients;
    }

    public void setClients(Set<AuthClient> clients) {
        this.clients = clients;
    }
}