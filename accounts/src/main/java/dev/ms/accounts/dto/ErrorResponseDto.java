package dev.ms.accounts.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(name = "ErrorResponse", description = "Schema to hold error response details")
@Data
@Builder
public class ErrorResponseDto {
	@Schema(description = "API path invoked by the client")
	private String apiPath;

	@Schema(description = "Error code representing the error happened")
	private HttpStatus errorCode;

	@Schema(description = "Error message representing the error happened")
	private String errorMessage;

	@Schema(description = "Time representing when the error happened")
	private LocalDateTime errorTime;
}
