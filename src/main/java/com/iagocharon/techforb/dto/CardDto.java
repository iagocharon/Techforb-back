package com.iagocharon.techforb.dto;

public class CardDto {

  private String name;
  private String number;
  private String cvv;
  private String expirationDate;
  private String issuer;

  public CardDto() {}

  public CardDto(
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

  public String getIssuer() {
    return this.issuer;
  }

  public void setIssuer(String issuer) {
    this.issuer = issuer;
  }
}
