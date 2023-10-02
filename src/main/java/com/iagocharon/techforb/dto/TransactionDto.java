package com.iagocharon.techforb.dto;

import com.iagocharon.techforb.enums.TransactionType;

public class TransactionDto {

  private int originId;
  private int targetId;
  private TransactionType type;
  private double amount;

  public TransactionDto() {}

  public TransactionDto(
    int originId,
    int targetId,
    TransactionType type,
    double amount
  ) {
    this.originId = originId;
    this.targetId = targetId;
    this.type = type;
    this.amount = amount;
  }

  public int getOriginId() {
    return this.originId;
  }

  public void setOriginId(int originId) {
    this.originId = originId;
  }

  public int getTargetId() {
    return this.targetId;
  }

  public void setTargetId(int targetId) {
    this.targetId = targetId;
  }

  public TransactionType getType() {
    return this.type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public double getAmount() {
    return this.amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}
