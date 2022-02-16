package com.playtomic.tests.wallet.domain;

import java.util.Optional;

public interface WalletRepository {
  Optional<Wallet> findBy(WalletId walletId);
}
