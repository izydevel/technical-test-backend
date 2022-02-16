package com.playtomic.tests.wallet.application;

import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletId;

import javax.inject.Named;

@Named
public class GetWallet {
  public Wallet execute(WalletId id) {
    throw new RuntimeException("Not Implemented");
  }
}
