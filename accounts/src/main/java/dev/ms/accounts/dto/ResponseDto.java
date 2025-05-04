package dev.ms.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema(name = "Response", description = "Schema to hold successful response")
@Data
@Builder
public class ResponseDto {
	@Schema(description = "Status code of the response")
	private String statusCode;

	@Schema(description = "Status message of the response")
	private String statusMsg;
}
