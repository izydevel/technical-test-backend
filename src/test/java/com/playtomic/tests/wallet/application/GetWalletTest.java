package com.playtomic.tests.wallet.application;

import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.domain.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GetWalletTest {

  @Mock private WalletRepository walletRepository;

  private GetWallet getWallet;

  @BeforeEach
  void setUp() {
    getWallet = new GetWallet(walletRepository);
  }

  @Test
  void should_get_the_wallet() {
    String uuid = UUID.randomUUID().toString();
    WalletId walletId = WalletId.of(uuid);
    Wallet walletExpected = Wallet.of(uuid, 100);
    given(walletRepository.findBy(walletId)).willReturn(Optional.of(walletExpected));

    Wallet wallet = getWallet.execute(walletId);

    assertThat(wallet).usingRecursiveComparison().isEqualTo(walletExpected);
  }
}
