package com.btu.customauthserver.repositories;

import com.btu.customauthserver.entities.AuthenticationMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationMethodRepository extends JpaRepository<AuthenticationMethod, Integer> {
}