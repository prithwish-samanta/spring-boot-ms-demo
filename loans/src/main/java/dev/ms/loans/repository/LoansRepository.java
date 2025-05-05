package dev.ms.loans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ms.loans.entity.Loans;

@Repository
public interface LoansRepository extends JpaRepository<Loans, Long> {

	Optional<Loans> findByMobileNumber(String mobileNumber);

	Optional<Loans> findByLoanNumber(String loanNumber);

}