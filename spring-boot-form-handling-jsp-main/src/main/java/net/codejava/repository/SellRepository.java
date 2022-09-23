package net.codejava.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.domain.Sell;

public interface SellRepository extends JpaRepository<Sell, Long> {

	Optional<Sell> findByDocnum(String docnum);
    Optional<Sell> findByStatus(String status);
    List<Sell> findByUserid(Long userid);
    Optional<Sell> findByProductid(Long userid);

}
