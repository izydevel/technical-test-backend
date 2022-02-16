package com.playtomic.tests.wallet.domain;

public class Wallet {

  private WalletId id;
  private Balance balance;

  private Wallet(String uuid, int balance) {
    this.id = WalletId.of(uuid);
    this.balance = Balance.of(balance);
  }

  public static Wallet of(String uuid, int balance) {
    return new Wallet(uuid, balance);
  }

  public WalletId getId() {
    return id;
  }

  public Balance getBalance() {
    return balance;
  }
}
