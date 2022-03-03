package com.playtomic.tests.wallet.domain;

import java.util.Objects;

public class CreditCard {

    private String cardNumber;

    private CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public static CreditCard of(String cardNumber) {
        return new CreditCard(cardNumber);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return Objects.equals(cardNumber, that.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }
}
