package com.playtomic.tests.wallet.infrastructure.api;

import com.playtomic.tests.wallet.application.GetWallet;
import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.infrastructure.api.dto.WalletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class WalletControllerTest {

  @Mock private GetWallet getWallet;

  private WalletController walletController;

  @BeforeEach
  void setUp() {
    walletController = new WalletController(getWallet);
  }

  @Test
  void should_get_the_wallet() {
    UUID uuid = UUID.randomUUID();
    int balance = 50;
    WalletResponse expectedResponse = expectedWalletResponse(uuid.toString(), balance);
    given(getWallet.execute(WalletId.of(uuid.toString())))
        .willReturn(Wallet.of(uuid.toString(), balance));

    WalletResponse walletResponse = walletController.get(uuid.toString());

    assertThat(walletResponse).usingRecursiveComparison().isEqualTo(expectedResponse);
  }

  private WalletResponse expectedWalletResponse(String id, int balance) {
    WalletResponse walletResponse = new WalletResponse();
    walletResponse.setId(id);
    walletResponse.setBalance(balance);
    return walletResponse;
  }
}
