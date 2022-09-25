package net.codejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codejava.domain.PaymentDetails;

public interface PaymentRepository extends JpaRepository<PaymentDetails, Long>{
	

}
