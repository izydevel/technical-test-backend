package com.playtomic.tests.wallet.application;

import com.playtomic.tests.wallet.domain.*;

import javax.inject.Named;

@Named
public class AddBalance {
    private final WalletRepository walletRepository;
    private final PaymentClient paymentClient;

    public AddBalance(WalletRepository walletRepository, PaymentClient paymentClient) {
        this.walletRepository = walletRepository;
        this.paymentClient = paymentClient;
    }

    public void execute(TransactionId of, WalletId walletId, Balance balance, CreditCard creditCard) {
        Wallet wallet = walletRepository.findBy(walletId).orElseThrow(WalletNotFoundException::new);
        paymentClient.addBalance(creditCard, balance);
        wallet.addBalance(balance);
        walletRepository.save(wallet);
    }
}
