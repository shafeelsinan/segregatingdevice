package net.codejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.domain.Currenstock;

public interface CurrenstockRepository extends JpaRepository<Currenstock, Long>{
	

	List<Currenstock> findByProductidAndSellingprice(Long productid,Double price);
}
