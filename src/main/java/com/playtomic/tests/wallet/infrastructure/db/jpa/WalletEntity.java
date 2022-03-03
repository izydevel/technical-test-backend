package com.playtomic.tests.wallet.infrastructure.db.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wallet")
public class WalletEntity {
    @Id
    private String id;
    @Column
    private int balance;

    public static WalletEntity of(String walletId, int balance) {
        WalletEntity wallet = new WalletEntity();
        wallet.setId(walletId);
        wallet.setBalance(balance);
        return wallet;
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    private void setBalance(int balance) {
        this.balance = balance;
    }
}
