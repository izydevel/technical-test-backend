package com.playtomic.tests.wallet.infrastructure.api;

import com.playtomic.tests.wallet.application.AddBalance;
import com.playtomic.tests.wallet.application.GetWallet;
import com.playtomic.tests.wallet.domain.Balance;
import com.playtomic.tests.wallet.domain.CreditCard;
import com.playtomic.tests.wallet.domain.Wallet;
import com.playtomic.tests.wallet.domain.WalletId;
import com.playtomic.tests.wallet.infrastructure.api.dto.AddBalanceRequest;
import com.playtomic.tests.wallet.infrastructure.api.dto.WalletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/api/v1/wallets",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class WalletController {

    private GetWallet getWallet;
    private AddBalance addBalance;

    public WalletController(GetWallet getWallet, AddBalance addBalance) {
        this.getWallet = getWallet;
        this.addBalance = addBalance;
    }

    @GetMapping("/{walletId}")
    public WalletResponse get(@PathVariable("walletId") String walletId) {
        return map(getWallet.execute(WalletId.of(walletId)));
    }

    @PutMapping("/{walletId}/add-balance")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void put(@PathVariable("walletId") String walletId, @RequestBody AddBalanceRequest addBalanceRequest) {
        addBalance.execute(WalletId.of(walletId), Balance.of(addBalanceRequest.getBalanceToAdd()), CreditCard.of(addBalanceRequest.getCardNumber()));
    }

    private WalletResponse map(Wallet wallet) {
        return WalletResponse.from(wallet);
    }
}
