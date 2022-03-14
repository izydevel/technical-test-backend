package com.playtomic.tests.wallet.application;

import com.playtomic.tests.wallet.domain.Balance;
import com.playtomic.tests.wallet.domain.CreditCard;
import com.playtomic.tests.wallet.domain.PaymentClient;
import com.playtomic.tests.wallet.domain.Transaction;
import com.playtomic.tests.wallet.domain.TransactionId;
import com.playtomic.tests.wallet.domain.TransactionRepository;
import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.domain.WalletNotFoundException;
import com.playtomic.tests.wallet.domain.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AddBalanceTest {

    @Mock
    private WalletRepository walletRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private PaymentClient paymentClient;

    private AddBalance addBalance;

    @BeforeEach
    void setUp() {
        addBalance = new AddBalance(transactionRepository, walletRepository, paymentClient);
    }

    @Test
    void should_do_not_update_balance_if_wallet_do_not_exists() {
        TransactionId transactionId = TransactionId.of(UUID.randomUUID().toString());
        WalletId walletId = WalletId.of(UUID.randomUUID().toString());
        Balance balance = Balance.of(3412123);
        CreditCard creditCard = CreditCard.of("12345566778941414");
        given(walletRepository.findBy(walletId)).willReturn(Optional.empty());

        Throwable t = catchThrowable(() -> addBalance.execute(transactionId, walletId, balance, creditCard));

        assertThat(t).isExactlyInstanceOf(WalletNotFoundException.class);
    }

    @Test
    void should_do_not_update_balance_if_transaction_already_exists() throws WalletNotFoundException {
        TransactionId transactionId = TransactionId.of(UUID.randomUUID().toString());
        WalletId walletId = WalletId.of(UUID.randomUUID().toString());
        Balance balance = Balance.of(3412123);
        CreditCard creditCard = CreditCard.of("12345566778941414");
        given(transactionRepository.findBy(transactionId)).willReturn(Optional.of(Transaction.of(transactionId)));

        addBalance.execute(transactionId, walletId, balance, creditCard);

        verify(paymentClient, never()).addBalance(creditCard, balance);
    }

    @Test
    void should_update_balance() throws WalletNotFoundException {
        TransactionId transactionId = TransactionId.of(UUID.randomUUID().toString());
        WalletId walletId = WalletId.of(UUID.randomUUID().toString());
        Balance balance = Balance.of(3412);
        CreditCard creditCard = CreditCard.of("12345566778941414");
        Wallet wallet = Wallet.of(walletId.getValue().toString(), 1000);
        given(walletRepository.findBy(walletId)).willReturn(Optional.of(wallet));
        given(transactionRepository.findBy(transactionId)).willReturn(Optional.empty());

        addBalance.execute(transactionId, walletId, balance, creditCard);

        verify(paymentClient).addBalance(creditCard, balance);
        verify(walletRepository).save(Wallet.of(walletId.getValue().toString(), balance.getValue() + 1000));
        verify(transactionRepository).save(Transaction.of(transactionId));
    }
}