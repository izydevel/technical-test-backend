package com.playtomic.tests.wallet.domain;

import java.util.Optional;

public interface TransactionRepository {
    Optional<Transaction> findBy(TransactionId transactionId);

    void save(Transaction transaction);
}
