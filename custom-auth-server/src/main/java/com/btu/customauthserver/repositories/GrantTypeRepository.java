package com.btu.customauthserver.repositories;

import com.btu.customauthserver.entities.GrantType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrantTypeRepository extends JpaRepository<GrantType, Integer> {
}