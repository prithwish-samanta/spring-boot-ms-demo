package dev.ms.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.ms.accounts.constants.AccountConstants;
import dev.ms.accounts.dto.CustomerDto;
import dev.ms.accounts.dto.ErrorResponseDto;
import dev.ms.accounts.dto.ResponseDto;
import dev.ms.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@Tag(name = "CRUD REST APIs for Accounts", description = "CRUD REST APIs to CREATE, UPDATE, FETCH and DELETE account details")
@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
@RequiredArgsConstructor
public class AccountsController {
	private final IAccountsService accountsService;

	@Operation(summary = "Create account REST API", description = "REST API to create new customer and account")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP status CREATED"),
			@ApiResponse(responseCode = "500", description = "HTTP status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto dto) {
		accountsService.createAccount(dto);
		ResponseDto res = ResponseDto.builder().statusCode(AccountConstants.STATUS_201)
				.statusMsg(AccountConstants.MESSAGE_201).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(res);

	}

	@Operation(summary = "Fetch account details REST API", description = "REST API to fetch customer and account details based on mobile number")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		CustomerDto res = accountsService.fetchAccount(mobileNumber);
		return ResponseEntity.ok(res);
	}

	@Operation(summary = "Update account details REST API", description = "REST API to update customer and account details based on mobile number")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP status OK"),
			@ApiResponse(responseCode = "417", description = "HTTP status operation failed with exception"),
			@ApiResponse(responseCode = "500", description = "HTTP status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto dto) {
		if (accountsService.updateAccount(dto)) {
			return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.builder()
					.statusCode(AccountConstants.STATUS_200).statusMsg(AccountConstants.MESSAGE_200).build());

		}
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseDto.builder()
				.statusCode(AccountConstants.STATUS_417).statusMsg(AccountConstants.MESSAGE_417_UPDATE).build());
	}

	@Operation(summary = "Delete account and customer details REST API", description = "REST API to delete customer and account details based on mobile number")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP status OK"),
			@ApiResponse(responseCode = "417", description = "HTTP status operation failed with exception"),
			@ApiResponse(responseCode = "500", description = "HTTP status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))) })
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccount(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber) {
		accountsService.deleteAccount(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.builder().statusCode(AccountConstants.STATUS_417)
				.statusMsg(AccountConstants.MESSAGE_417_DELETE).build());
	}
}
