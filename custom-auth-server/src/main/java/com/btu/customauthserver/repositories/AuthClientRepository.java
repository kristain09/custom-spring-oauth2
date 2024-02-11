package com.btu.customauthserver.repositories;

import com.btu.customauthserver.entities.AuthClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AuthClientRepository extends JpaRepository<AuthClient, Long> {


  @Query("""
  SELECT c FROM AuthClient c WHERE c.clientId = :clientId
  """)
  Optional<AuthClient> findAuthClientByClientId(String clientId);

}