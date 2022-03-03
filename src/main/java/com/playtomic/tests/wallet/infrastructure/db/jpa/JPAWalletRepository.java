package com.playtomic.tests.wallet.infrastructure.db.jpa;

import com.playtomic.tests.wallet.domain.WalletId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAWalletRepository extends JpaRepository<WalletEntity, String> {
}
