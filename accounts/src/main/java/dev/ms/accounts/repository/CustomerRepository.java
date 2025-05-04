package dev.ms.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ms.accounts.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	boolean existsByMobileNumber(String mobileNumber);

	Optional<Customer> findByMobileNumber(String mobileNumber);
}
