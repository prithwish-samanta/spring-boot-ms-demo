package dev.ms.accounts.service;

import dev.ms.accounts.dto.CustomerDto;

public interface IAccountsService {
	void createAccount(CustomerDto req);

	CustomerDto fetchAccount(String mobileNumber);

	boolean updateAccount(CustomerDto req);

	boolean deleteAccount(String mobileNumber);
}
