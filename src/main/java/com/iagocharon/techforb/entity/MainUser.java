package com.iagocharon.techforb.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MainUser implements UserDetails {

  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public MainUser(String username, String password, String roleName) {
    this.username = username;
    this.password = password;
    List<GrantedAuthority> authorityList = new ArrayList<>();
    authorityList.add(new SimpleGrantedAuthority(roleName));
    this.authorities = authorityList;
  }

  public static MainUser build(User user) {
    return new MainUser(user.getUsername(), user.getPassword(), "USER");
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
