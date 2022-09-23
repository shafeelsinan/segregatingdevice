package net.codejava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.HisUser;
import net.codejava.domain.ProductMain;

public interface ProductRepository extends JpaRepository<ProductMain, Long> {

	Optional<ProductMain> findByCode(String email);
    Optional<ProductMain> findByName(String username);
}
