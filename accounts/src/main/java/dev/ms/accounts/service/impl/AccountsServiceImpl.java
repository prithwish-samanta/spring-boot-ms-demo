package dev.ms.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import dev.ms.accounts.constants.AccountConstants;
import dev.ms.accounts.dto.AccountsDto;
import dev.ms.accounts.dto.CustomerDto;
import dev.ms.accounts.entity.Accounts;
import dev.ms.accounts.entity.Customer;
import dev.ms.accounts.exception.CustomerAlreadyExistsException;
import dev.ms.accounts.exception.ResourceNotFoundException;
import dev.ms.accounts.mapper.AccountsMapper;
import dev.ms.accounts.mapper.CustomerMapper;
import dev.ms.accounts.repository.AccountsRepository;
import dev.ms.accounts.repository.CustomerRepository;
import dev.ms.accounts.service.IAccountsService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
	private final AccountsRepository accountsRepository;
	private final CustomerRepository customerRepository;

	@Override
	public void createAccount(CustomerDto req) {
		if (customerRepository.existsByMobileNumber(req.getMobileNumber())) {
			throw new CustomerAlreadyExistsException(
					"Customer already exists with mobile number: " + req.getMobileNumber());
		}

		Customer customer = CustomerMapper.toEntity(req);
		customer.setCreatedAt(LocalDateTime.now());
		customer.setCreatedBy("BANK");
		Customer savedCustomer = customerRepository.save(customer);

		Accounts account = createNewAccount(savedCustomer);
		accountsRepository.save(account);
	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCreatedBy()));

		CustomerDto dto = CustomerMapper.toDto(customer);
		dto.setAccount(AccountsMapper.toDto(accounts));
		return dto;

	}

	@Override
	public boolean updateAccount(CustomerDto req) {
		boolean isUpdated = false;
		AccountsDto accountsDto = req.getAccount();
		if (accountsDto != null) {
			Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Account", "accountNumber",
							accountsDto.getAccountNumber().toString()));
			Long customerId = accounts.getCustomerId();
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

			if (StringUtils.hasText(accountsDto.getAccountType())) {
				accounts.setAccountType(accountsDto.getAccountType());
			}
			if (StringUtils.hasText(accountsDto.getBranchAddress())) {
				accounts.setBranchAddress(accountsDto.getBranchAddress());
			}
			accountsRepository.save(accounts);

			if (StringUtils.hasText(req.getEmail())) {
				customer.setEmail(req.getEmail());
			}
			if (StringUtils.hasText(req.getMobileNumber())) {
				customer.setMobileNumber(req.getMobileNumber());
			}
			if (StringUtils.hasText(req.getName())) {
				customer.setName(req.getName());
			}
			customerRepository.save(customer);
			isUpdated = true;
		}
		return isUpdated;
	}

	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCreatedBy()));
		customerRepository.delete(customer);
		accountsRepository.delete(accounts);
		return true;
	}

	private Accounts createNewAccount(Customer customer) {
		Long accNumber = 10000000000L + new Random().nextInt(900000000);
		return Accounts.builder().customerId(customer.getCustomerId()).accountNumber(accNumber)
				.accountType(AccountConstants.SAVINGS).branchAddress(AccountConstants.ADDRESS).build();
	}

}
