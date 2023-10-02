package com.iagocharon.techforb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotNull
  @Column(unique = true)
  private String username;

  @NotNull
  private String password;

  @NotNull
  private String name;

  @NotNull
  private String email;

  @NotNull
  private String phone;

  @NotNull
  private String dni;

  private double balance;

  @OneToMany(mappedBy = "target", cascade = CascadeType.ALL)
  @JsonIgnoreProperties({ "target" })
  private List<Transaction> incomingTransactions;

  @OneToMany(mappedBy = "origin", cascade = CascadeType.ALL)
  @JsonIgnoreProperties({ "origin" })
  private List<Transaction> outgoingTransactions;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @JsonIgnoreProperties({ "user" })
  private List<Card> cards;

  public User() {
    this.balance = 0;
    this.incomingTransactions = new ArrayList<>();
    this.outgoingTransactions = new ArrayList<>();
    this.cards = new ArrayList<>();
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
    this.balance = 0;
    this.incomingTransactions = new ArrayList<>();
    this.outgoingTransactions = new ArrayList<>();
    this.cards = new ArrayList<>();
  }

  public User(
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
    this.balance = 0;
    this.incomingTransactions = new ArrayList<>();
    this.outgoingTransactions = new ArrayList<>();
    this.cards = new ArrayList<>();
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
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

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public List<Transaction> getIncomingTransactions() {
    return this.incomingTransactions;
  }

  public void setIncomingTransactions(List<Transaction> incomingTransactions) {
    this.incomingTransactions = incomingTransactions;
  }

  public void addIncomingTransaction(Transaction transaction) {
    this.incomingTransactions.add(transaction);
  }

  public List<Transaction> getOutgoingTransactions() {
    return this.outgoingTransactions;
  }

  public void setOutgoingTransactions(List<Transaction> outgoingTransactions) {
    this.outgoingTransactions = outgoingTransactions;
  }

  public void addOutgoingTransaction(Transaction transaction) {
    this.outgoingTransactions.add(transaction);
  }

  public List<Card> getCards() {
    return this.cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }

  public void addCard(Card card) {
    this.cards.add(card);
  }
}
