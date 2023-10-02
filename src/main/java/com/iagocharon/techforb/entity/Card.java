package com.iagocharon.techforb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Card {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private String number;
  private String cvv;
  private String expirationDate;
  private String issuer;

  @ManyToOne
  @JsonIgnoreProperties({ "hibernateLazyInitializer", "cards" })
  private User user;

  public Card() {}

  public Card(
    String name,
    String number,
    String cvv,
    String expirationDate,
    String issuer
  ) {
    this.name = name;
    this.number = number;
    this.cvv = cvv;
    this.expirationDate = expirationDate;
    this.issuer = issuer;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNumber() {
    return this.number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getCvv() {
    return this.cvv;
  }

  public void setCvv(String cvv) {
    this.cvv = cvv;
  }

  public String getExpirationDate() {
    return this.expirationDate;
  }

  public void setExpirationDate(String expirationDate) {
    this.expirationDate = expirationDate;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getIssuer() {
    return this.issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }
}
