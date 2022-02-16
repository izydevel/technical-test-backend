package com.playtomic.tests.wallet.domain;

import java.util.Objects;
import java.util.UUID;

public class WalletId {

  private UUID value;

  private WalletId(String uuid) {
    value = UUID.fromString(uuid);
  }

  public static WalletId of(String uuid) {
    return new WalletId(uuid);
  }

  public UUID getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    WalletId walletId = (WalletId) o;
    return Objects.equals(value, walletId.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
