package com.playtomic.tests.wallet.infrastructure.api;

import com.playtomic.tests.wallet.application.GetWallet;
import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.infrastructure.api.dto.WalletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    value = "/api/v1/wallets",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class WalletController {

  private GetWallet getWallet;

  public WalletController(GetWallet getWallet) {
    this.getWallet = getWallet;
  }

  @GetMapping("/{walletId}")
  public WalletResponse get(@PathVariable("walletId") String walletId) {
    return map(getWallet.execute(WalletId.of(walletId)));
  }

  private WalletResponse map(Wallet wallet) {
    return WalletResponse.from(wallet);
  }
}
