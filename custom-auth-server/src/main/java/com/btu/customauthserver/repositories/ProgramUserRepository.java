package com.btu.customauthserver.repositories;

import com.btu.customauthserver.entities.ProgramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProgramUserRepository extends JpaRepository<ProgramUser, Integer> {

  @Query("""
  SELECT u FROM ProgramUser u WHERE u.username =:username
  """)
  Optional<ProgramUser> findProgramUserByUsername(String username);
}