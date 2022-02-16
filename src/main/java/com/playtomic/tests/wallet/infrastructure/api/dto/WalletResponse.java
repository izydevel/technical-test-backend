package com.playtomic.tests.wallet.infrastructure.api.dto;

public class WalletResponse {
  private String id;
  private int balance;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }
}
