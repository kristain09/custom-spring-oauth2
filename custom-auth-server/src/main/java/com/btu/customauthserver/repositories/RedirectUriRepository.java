package com.btu.customauthserver.repositories;

import com.btu.customauthserver.entities.RedirectUri;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedirectUriRepository extends JpaRepository<RedirectUri, Integer> {
}