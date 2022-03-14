package com.playtomic.tests.wallet.infrastructure.api;

import com.playtomic.tests.wallet.application.AddBalance;
import com.playtomic.tests.wallet.application.GetWallet;
import com.playtomic.tests.wallet.domain.Balance;
import com.playtomic.tests.wallet.domain.CreditCard;
import com.playtomic.tests.wallet.domain.TransactionId;
import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.infrastructure.api.dto.AddBalanceRequest;
import com.playtomic.tests.wallet.infrastructure.api.dto.WalletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class WalletControllerTest {

    @Mock
    private GetWallet getWallet;

    @Mock
    private AddBalance addBalance;

    private WalletController walletController;

    @BeforeEach
    void setUp() {
        walletController = new WalletController(getWallet, addBalance);
    }

    @Test
    void should_get_the_wallet() {
        UUID uuid = UUID.randomUUID();
        int balance = 50;
        WalletResponse expectedResponse = expectedWalletResponse(uuid.toString(), balance);
        given(getWallet.execute(WalletId.of(uuid.toString()))).willReturn(Wallet.of(uuid.toString(), balance));

        WalletResponse walletResponse = walletController.get(uuid.toString());

        assertThat(walletResponse).usingRecursiveComparison().isEqualTo(expectedResponse);
    }

    @Test
    void should_add_balance() {
        String id = UUID.randomUUID().toString();
        String idTransaction = UUID.randomUUID().toString();
        AddBalanceRequest addBalanceRequest = AddBalanceRequest.of(idTransaction, "4242424242424242", 2000);

        walletController.put(id, addBalanceRequest);

        verify(addBalance).execute(TransactionId.of(addBalanceRequest.getId()), WalletId.of(id), Balance.of(addBalanceRequest.getBalanceToAdd()), CreditCard.of(addBalanceRequest.getCardNumber()));
    }

    private WalletResponse expectedWalletResponse(String id, int balance) {
        WalletResponse walletResponse = new WalletResponse();
        walletResponse.setId(id);
        walletResponse.setBalance(balance);
        return walletResponse;
    }
}
