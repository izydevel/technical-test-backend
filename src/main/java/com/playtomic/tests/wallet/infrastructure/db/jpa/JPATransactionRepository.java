package com.playtomic.tests.wallet.infrastructure.db.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JPATransactionRepository extends JpaRepository<TransactionEntity, String> {
}
