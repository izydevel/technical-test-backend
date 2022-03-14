package com.playtomic.tests.wallet.domain;

import java.util.Objects;

public class Transaction {

    private TransactionId id;

    private Transaction(TransactionId id) {
        this.id = id;
    }

    public static Transaction of(TransactionId transactionId) {
        return new Transaction(transactionId);
    }

    public TransactionId getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
