package dev.ms.loans.service;

import dev.ms.loans.dto.LoansDto;

public interface ILoansService {
	void createLoan(String mobileNumber);

	LoansDto fetchLoan(String mobileNumber);

	boolean updateLoan(LoansDto loansDto);

	boolean deleteLoan(String mobileNumber);

}