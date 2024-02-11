package com.btu.customauthserver.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "m_granted_authority", schema = "\"authorization\"")
public class MGrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "name", length = Integer.MAX_VALUE)
  private String name;

  @ManyToMany(mappedBy = "grantedAuthorities")
  private Set<ProgramUser> programUsers;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<ProgramUser> getProgramUsers() {
    return programUsers;
  }

  public void setProgramUsers(Set<ProgramUser> programUsers) {
    this.programUsers = programUsers;
  }
}