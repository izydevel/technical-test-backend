package com.playtomic.tests.wallet.domain;

public class Balance {

  private int value;

  private Balance(int value) {
    this.value = value;
  }

  public static Balance of(int balance) {
    return new Balance(balance);
  }

  public int getValue() {
    return value;
  }
}
