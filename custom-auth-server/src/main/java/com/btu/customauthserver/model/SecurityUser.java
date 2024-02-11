package com.btu.customauthserver.model;

import com.btu.customauthserver.entities.GrantType;
import com.btu.customauthserver.entities.ProgramUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SecurityUser implements UserDetails {

  private final ProgramUser programUser;

  public SecurityUser(ProgramUser programUser) {
    this.programUser = programUser;
  }
  @Override
  public String getUsername() {
    return programUser.getUsername();
  }

  @Override
  public String getPassword() {
    return programUser.getPassword();
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> authorities = new HashSet<>();

    programUser.getGrantedAuthorities()
        .forEach(auth -> {
          authorities.add(new SimpleGrantedAuthority(auth.getName()));
        });

    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return programUser.getAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return programUser.getAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return programUser.getCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return programUser.getEnabled();
  }
}
