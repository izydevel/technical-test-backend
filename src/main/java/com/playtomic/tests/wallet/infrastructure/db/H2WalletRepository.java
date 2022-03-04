package com.playtomic.tests.wallet.infrastructure.db;

import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.domain.WalletRepository;
import com.playtomic.tests.wallet.infrastructure.db.jpa.JPAWalletRepository;
import com.playtomic.tests.wallet.infrastructure.db.jpa.WalletEntity;

import javax.inject.Named;
import java.util.Optional;

@Named
public class H2WalletRepository implements WalletRepository {

    private JPAWalletRepository jpaWalletRepository;

    public H2WalletRepository(JPAWalletRepository jpaWalletRepository) {
        this.jpaWalletRepository = jpaWalletRepository;
    }

    @Override
    public Optional<Wallet> findBy(WalletId walletId) {
        return jpaWalletRepository.findById(walletId.getValue().toString())
                .map(w -> Wallet.of(w.getId(), w.getBalance()));
    }

    @Override
    public void save(Wallet wallet) {
        jpaWalletRepository.save(WalletEntity.of(wallet.getId().getValue().toString(), wallet.getBalance().getValue()));
    }
}
