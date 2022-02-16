package com.playtomic.tests.wallet.infrastructure.api.dto;

import com.playtomic.tests.wallet.domain.Wallet;

public class WalletResponse {
  private String id;
  private int balance;

  public static WalletResponse from(Wallet wallet) {
    WalletResponse walletResponse = new WalletResponse();
    walletResponse.setId(wallet.getId().getValue().toString());
    walletResponse.setBalance(wallet.getBalance().getValue());
    return walletResponse;
  }

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
