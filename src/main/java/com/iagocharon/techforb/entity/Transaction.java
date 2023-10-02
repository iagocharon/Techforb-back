package com.iagocharon.techforb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iagocharon.techforb.enums.TransactionType;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "origin_user_id")
  @JsonIgnoreProperties(
    {
      "hibernateLazyInitializer",
      "outgoingTransactions",
      "incomingTransactions",
    }
  )
  private User origin;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "target_user_id")
  @JsonIgnoreProperties(
    {
      "hibernateLazyInitializer",
      "outgoingTransactions",
      "incomingTransactions",
    }
  )
  private User target;

  @NotNull
  @Enumerated(EnumType.STRING)
  private TransactionType type;

  private LocalDateTime date;

  private double amount;

  public Transaction() {
    this.date = LocalDateTime.now();
  }

  public Transaction(
    User origin,
    User target,
    TransactionType type,
    double amount
  ) {
    this.origin = origin;
    this.target = target;
    this.type = type;
    this.amount = amount;
    this.date = LocalDateTime.now();
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public User getOrigin() {
    return this.origin;
  }

  public void setOrigin(User origin) {
    this.origin = origin;
  }

  public User getTarget() {
    return this.target;
  }

  public void setTarget(User target) {
    this.target = target;
  }

  public TransactionType getType() {
    return this.type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public LocalDateTime getDate() {
    return this.date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public double getAmount() {
    return this.amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}
