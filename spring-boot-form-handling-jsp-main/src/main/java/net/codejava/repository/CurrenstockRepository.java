package net.codejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.codejava.domain.Currenstock;

public interface CurrenstockRepository extends JpaRepository<Currenstock, Long>{
	

	@Query("SELECT cs from Currenstock as cs where cs.productid.id = :productid and cs.sellingprice = :price")
	List<Currenstock> findByProductidAndSellingprice(Long productid,Double price);
	
	List<Currenstock> findByCurrenstockqtyGreaterThan(Long qty);
}
