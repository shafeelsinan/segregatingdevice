package net.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.domain.StockLedger;

public interface StkLedgerRepository extends JpaRepository<StockLedger, Long>{

}
