package com.btu.customauthserver.service;

import com.btu.customauthserver.entities.ProgramUser;
import com.btu.customauthserver.model.SecurityUser;
import com.btu.customauthserver.repositories.ProgramUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

  private final ProgramUserRepository repository;

  public UserService(ProgramUserRepository repository) {
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    ProgramUser user = repository.findProgramUserByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    return new SecurityUser(user);
  }
}
