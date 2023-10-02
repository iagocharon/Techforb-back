package com.iagocharon.techforb.dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

public class JwtDto {

  private String token;
  private String bearer = "Bearer";
  private String username;
  private int id;

  public JwtDto(String token, String username, int id) {
    this.token = token;
    this.username = username;
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getBearer() {
    return bearer;
  }

  public void setBearer(String bearer) {
    this.bearer = bearer;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
