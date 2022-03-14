package com.playtomic.tests.wallet.infrastructure.db;

import com.playtomic.tests.wallet.domain.Transaction;
import com.playtomic.tests.wallet.domain.TransactionId;
import com.playtomic.tests.wallet.domain.TransactionRepository;
import com.playtomic.tests.wallet.infrastructure.db.jpa.JPATransactionRepository;
import com.playtomic.tests.wallet.infrastructure.db.jpa.TransactionEntity;

import javax.inject.Named;
import java.util.Optional;

@Named
public class H2TransactionRepository implements TransactionRepository {

    private JPATransactionRepository jpaTransactionRepository;

    public H2TransactionRepository(JPATransactionRepository jpaTransactionRepository) {
        this.jpaTransactionRepository = jpaTransactionRepository;
    }

    @Override
    public Optional<Transaction> findBy(TransactionId transactionId) {
        return jpaTransactionRepository.findById(transactionId.getValue().toString())
                .map(t -> Transaction.of(TransactionId.of(t.getId())));
    }

    @Override
    public void save(Transaction transaction) {
        jpaTransactionRepository.save(TransactionEntity.of(transaction.getId().getValue().toString()));
    }
}
