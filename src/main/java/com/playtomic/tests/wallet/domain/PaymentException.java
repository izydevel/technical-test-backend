package com.playtomic.tests.wallet.domain;

public class PaymentException extends RuntimeException {
    public PaymentException(Exception e) {
        super(e);
    }
}
