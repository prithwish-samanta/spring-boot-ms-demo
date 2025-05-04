package dev.ms.accounts.mapper;

import dev.ms.accounts.dto.CustomerDto;
import dev.ms.accounts.entity.Customer;

public class CustomerMapper {
	public static Customer toEntity(CustomerDto dto) {
		return Customer.builder().name(dto.getName()).email(dto.getEmail()).mobileNumber(dto.getMobileNumber()).build();
	}

	public static CustomerDto toDto(Customer entity) {
		return CustomerDto.builder().name(entity.getName()).email(entity.getEmail())
				.mobileNumber(entity.getMobileNumber()).build();
	}
}
