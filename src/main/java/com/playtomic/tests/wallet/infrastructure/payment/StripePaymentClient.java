package com.playtomic.tests.wallet.infrastructure.payment;

import com.playtomic.tests.wallet.domain.Balance;
import com.playtomic.tests.wallet.domain.CreditCard;
import com.playtomic.tests.wallet.domain.PaymentClient;
import com.playtomic.tests.wallet.domain.PaymentException;
import com.playtomic.tests.wallet.infrastructure.payment.stripe.StripeService;
import com.playtomic.tests.wallet.infrastructure.payment.stripe.StripeServiceException;

import javax.inject.Named;

@Named
public class StripePaymentClient implements PaymentClient {
    private StripeService stripeService;

    public StripePaymentClient(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @Override
    public void addBalance(CreditCard creditCard, Balance balance) {
        try {
            stripeService.charge(creditCard.getCardNumber(), balance.toCents());
        } catch (StripeServiceException e) {
            throw new PaymentException(e);
        }
    }
}
