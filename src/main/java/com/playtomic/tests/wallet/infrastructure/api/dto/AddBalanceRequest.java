package com.playtomic.tests.wallet.infrastructure.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AddBalanceRequest {

    private String cardNumber;
    private int balanceToAdd;

    public static AddBalanceRequest of(String creditCard, int balanceToAdd) {
        AddBalanceRequest addBalanceRequest = new AddBalanceRequest();
        addBalanceRequest.setBalanceToAdd(balanceToAdd);
        addBalanceRequest.setCardNumber(creditCard);
        return addBalanceRequest;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getBalanceToAdd() {
        return balanceToAdd;
    }

    public void setBalanceToAdd(int balanceToAdd) {
        this.balanceToAdd = balanceToAdd;
    }
}
