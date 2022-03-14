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

import javax.inject.Named;

@Named
public class AddBalance {
    private final WalletRepository walletRepository;
    private final PaymentClient paymentClient;
    private TransactionRepository transactionRepository;

    public AddBalance(TransactionRepository transactionRepository, WalletRepository walletRepository, PaymentClient paymentClient) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.paymentClient = paymentClient;
    }

    public synchronized void execute(TransactionId transactionId, WalletId walletId, Balance balance, CreditCard creditCard) {
        if (transactionRepository.findBy(transactionId).isPresent()) return;

        Wallet wallet = walletRepository.findBy(walletId).orElseThrow(WalletNotFoundException::new);
        paymentClient.addBalance(creditCard, balance);
        wallet.addBalance(balance);
        walletRepository.save(wallet);
        transactionRepository.save(Transaction.of(transactionId));
    }
}
