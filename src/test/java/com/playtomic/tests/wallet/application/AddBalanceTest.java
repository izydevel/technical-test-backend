package com.playtomic.tests.wallet.application;

import com.playtomic.tests.wallet.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddBalanceTest {

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private PaymentClient paymentClient;

    private AddBalance addBalance;

    @BeforeEach
    void setUp() {
        addBalance = new AddBalance(walletRepository, paymentClient);
    }

    @Test
    void should_do_not_update_balance_if_wallet_do_not_exists() throws WalletNotFoundException {
        WalletId walletId = WalletId.of(UUID.randomUUID().toString());
        Balance balance = Balance.of(3412123);
        CreditCard creditCard = CreditCard.of("12345566778941414");
        BDDMockito.given(walletRepository.findBy(walletId)).willReturn(Optional.empty());

        Throwable t = catchThrowable(() -> addBalance.execute(walletId, balance, creditCard));

        assertThat(t).isExactlyInstanceOf(WalletNotFoundException.class);
    }

    @Test
    void should_update_balance() throws WalletNotFoundException {
        WalletId walletId = WalletId.of(UUID.randomUUID().toString());
        Balance balance = Balance.of(3412);
        CreditCard creditCard = CreditCard.of("12345566778941414");
        Wallet wallet = Wallet.of(walletId.getValue().toString(), 1000);
        BDDMockito.given(walletRepository.findBy(walletId)).willReturn(Optional.of(wallet));

        addBalance.execute(walletId, balance, creditCard);

        verify(paymentClient).addBalance(creditCard, balance);
        verify(walletRepository).save(Wallet.of(walletId.getValue().toString(), balance.getValue() + 1000));
    }
}