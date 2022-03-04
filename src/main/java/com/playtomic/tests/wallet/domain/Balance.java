package com.playtomic.tests.wallet.domain;

import java.math.BigDecimal;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Balance balance = (Balance) o;
    return value == balance.value;
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

    public BigDecimal toCents() {
      return new BigDecimal(value/100);
    }

    public void add(Balance balance) {
      this.value += balance.value;
    }
}
