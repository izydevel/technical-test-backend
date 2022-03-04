package com.playtomic.tests.wallet.infrastructure.payment;

import com.playtomic.tests.wallet.domain.Balance;
import com.playtomic.tests.wallet.domain.CreditCard;
import com.playtomic.tests.wallet.domain.PaymentException;
import com.playtomic.tests.wallet.infrastructure.payment.stripe.StripeService;
import com.playtomic.tests.wallet.infrastructure.payment.stripe.StripeServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StripePaymentClientTest {

    @Mock
    private StripeService stripeService;

    private StripePaymentClient stripePaymentClient;

    @BeforeEach
    void setUp() {
        stripePaymentClient = new StripePaymentClient(stripeService);
    }

    @Test
    void should_throw_payment_exception_when_stripe_fails() {
        CreditCard creditCard = CreditCard.of("424242424242424242");
        Balance balance = Balance.of(4124124);
        doThrow(StripeServiceException.class).when(stripeService).charge(creditCard.getCardNumber(),balance.toCents());

        Throwable t = catchThrowable(() -> stripePaymentClient.addBalance(creditCard, balance));

        assertThat(t).isExactlyInstanceOf(PaymentException.class);
    }

    @Test
    void should_add_balance() {
        CreditCard creditCard = CreditCard.of("424242424242424242");
        Balance balance = Balance.of(4124124);

        stripePaymentClient.addBalance(creditCard, balance);

        verify(stripeService).charge(creditCard.getCardNumber(), balance.toCents());
    }
}