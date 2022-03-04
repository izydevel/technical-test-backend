package com.playtomic.tests.wallet.domain;

import java.util.Optional;

public interface WalletRepository {
    Optional<Wallet> findBy(WalletId walletId);

    void save(Wallet wallet);
}
