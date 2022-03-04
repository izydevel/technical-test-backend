package com.playtomic.tests.wallet.domain;

public interface PaymentClient {
    void addBalance(CreditCard creditCard, Balance balance);
}
