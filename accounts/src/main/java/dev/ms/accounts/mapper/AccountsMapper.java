package dev.ms.accounts.mapper;

import dev.ms.accounts.dto.AccountsDto;
import dev.ms.accounts.entity.Accounts;

public class AccountsMapper {
	public static Accounts toEntity(AccountsDto dto) {
		return Accounts.builder().accountNumber(dto.getAccountNumber()).accountType(dto.getAccountType())
				.branchAddress(dto.getBranchAddress()).build();
	}

	public static AccountsDto toDto(Accounts entity) {
		return AccountsDto.builder().accountNumber(entity.getAccountNumber()).accountType(entity.getAccountType())
				.branchAddress(entity.getBranchAddress()).build();
	}
}
