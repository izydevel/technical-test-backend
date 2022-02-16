package com.playtomic.tests.wallet.infrastructure.api;

import com.playtomic.tests.wallet.infrastructure.api.dto.WalletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private Logger log = LoggerFactory.getLogger(WalletController.class);

  @GetMapping("/{walletId}")
  public WalletResponse get(@PathVariable("walletId") String walletId) {
    log.info("Logging from /");
    throw new RuntimeException("Not implemented yet");
  }
}
