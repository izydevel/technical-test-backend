package com.playtomic.tests.wallet.domain;

import java.util.Objects;
import java.util.UUID;

public class TransactionId {

  private UUID value;

  private TransactionId(String uuid) {
    value = UUID.fromString(uuid);
  }

  public static TransactionId of(String uuid) {
    return new TransactionId(uuid);
  }

  public UUID getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TransactionId that = (TransactionId) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
