package dev.ms.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Schema(name = "Accounts", description = "Schema to hold account details")
@Data
@Builder
public class AccountsDto {
	@Schema(description = "Account number of the customer", example = "1234567890")
	@NotEmpty(message = "Account number cannot be empty or null")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
	private Long accountNumber;

	@Schema(description = "Account type", example = "Savings")
	@NotEmpty(message = "Account type cacnot be empty or null")
	private String accountType;

	@Schema(description = "Branch address of the account", example = "123 Main Street, New York")
	@NotNull(message = "Branch adress cannot be empty or null")
	private String branchAddress;
}
