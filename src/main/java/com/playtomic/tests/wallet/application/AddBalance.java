package com.playtomic.tests.wallet.application;

import com.playtomic.tests.wallet.domain.Balance;
import com.playtomic.tests.wallet.domain.CreditCard;
import com.playtomic.tests.wallet.domain.WalletId;

import javax.inject.Named;

@Named
public class AddBalance {
    public void execute(WalletId walletId, Balance balance, CreditCard creditCard) {
        throw new RuntimeException("not implemented");
    }
}
