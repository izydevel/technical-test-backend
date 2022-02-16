package com.playtomic.tests.wallet.application;

import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.domain.WalletRepository;

import javax.inject.Named;

@Named
public class GetWallet {

  private WalletRepository walletRepository;

  public GetWallet(WalletRepository walletRepository) {
    this.walletRepository = walletRepository;
  }

  public Wallet execute(WalletId id) {
    return walletRepository.findBy(id).get();
  }
}
