package dev.ms.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Schema(name = "Customer", description = "Schema to hold customer and account details")
@Data
@Builder
public class CustomerDto {
	@Schema(description = "Name of the customer", example = "John Doe")
	@NotEmpty(message = "Name canot be empty or null")
	@Size(min = 5, max = 30, message = "The length of the customer name must be between 5 and 30")
	private String name;

	@Schema(description = "Email address of the customer", example = "john.doe@example.com")
	@NotEmpty(message = "Email address cannot be empty or null")
	@Email(message = "Email address should be valid value")
	private String email;

	@Schema(description = "Mobile number of the customer", example = "9876543210")
	@NotEmpty(message = "Mobile number cananot be empty or null")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
	private String mobileNumber;

	@Schema(description = "Account details of the customer")
	private AccountsDto account;
}
