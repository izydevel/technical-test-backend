package com.playtomic.tests.wallet.infrastructure.db.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    private String id;

    public static TransactionEntity of(String id) {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(id);
        return transactionEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
