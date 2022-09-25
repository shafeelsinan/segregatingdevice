package net.codejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.domain.BuyMain;
import net.codejava.domain.Sell;

public interface BuyRepository extends JpaRepository<BuyMain, Long>{

	List<BuyMain> findByUserid(long userid);

	List<BuyMain> findByStatus(String status);

}
