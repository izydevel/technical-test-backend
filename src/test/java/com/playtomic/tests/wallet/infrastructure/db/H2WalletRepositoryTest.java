package com.playtomic.tests.wallet.infrastructure.db;


import com.playtomic.tests.wallet.domain.Balance;
import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.infrastructure.db.jpa.JPAWalletRepository;
import com.playtomic.tests.wallet.infrastructure.db.jpa.WalletEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class H2WalletRepositoryTest {

    @Mock
    private JPAWalletRepository jpaWalletRepository;

    private H2WalletRepository h2WalletRepository;

    @BeforeEach
    void setUp() {
        h2WalletRepository = new H2WalletRepository(jpaWalletRepository);
    }

    @Test
    void should_obtain_wallet_when_not_exists() {
        String walletId = UUID.randomUUID().toString();
        given(jpaWalletRepository.findById(walletId)).willReturn(Optional.empty());

        Optional<Wallet> wallet = h2WalletRepository.findBy(WalletId.of(walletId));

        assertThat(wallet).isEmpty();
    }

    @Test
    void should_obtain_wallet() {
        String walletId = UUID.randomUUID().toString();
        int balance = 4321;
        WalletEntity walletEntity = WalletEntity.of(walletId, balance);
        given(jpaWalletRepository.findById(walletId)).willReturn(Optional.of(walletEntity));

        Optional<Wallet> wallet = h2WalletRepository.findBy(WalletId.of(walletId));

        assertThat(wallet.get())
                .hasFieldOrPropertyWithValue("id", WalletId.of(walletId))
                .hasFieldOrPropertyWithValue("balance", Balance.of(balance));
    }
}