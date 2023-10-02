package com.iagocharon.techforb.dto;

public class UserDto {

  private String username;
  private String password;
  private String name;
  private String email;

  private String phone;

  private String dni;

  public UserDto() {}

  public UserDto(
    String username,
    String password,
    String name,
    String email,
    String phone,
    String dni
  ) {
    this.username = username;
    this.password = password;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.dni = dni;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getDni() {
    return this.dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }
}
