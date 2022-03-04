package com.playtomic.tests.wallet.domain;

import java.util.Objects;

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

  public void addBalance(Balance balance) {
    this.balance.add(balance);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Wallet wallet = (Wallet) o;
    return Objects.equals(id, wallet.id) && Objects.equals(balance, wallet.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, balance);
  }
}
