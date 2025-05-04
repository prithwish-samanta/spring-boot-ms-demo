package dev.ms.accounts.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.ms.accounts.dto.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException ex,
			WebRequest req) {
		ErrorResponseDto dto = ErrorResponseDto.builder().apiPath(req.getDescription(false))
				.errorCode(HttpStatus.BAD_REQUEST).errorMessage(ex.getMessage()).errorTime(LocalDateTime.now()).build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex,
			WebRequest req) {
		ErrorResponseDto dto = ErrorResponseDto.builder().apiPath(req.getDescription(false))
				.errorCode(HttpStatus.NOT_FOUND).errorMessage(ex.getMessage()).errorTime(LocalDateTime.now()).build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {

		Map<String, String> validationErrors = new HashMap<>();
		List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
		validationErrorList.forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String validationMessage = error.getDefaultMessage();
			validationErrors.put(fieldName, validationMessage);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleAllOtherExceptions(Exception ex, WebRequest req) {
		ErrorResponseDto dto = ErrorResponseDto.builder().apiPath(req.getDescription(false))
				.errorCode(HttpStatus.INTERNAL_SERVER_ERROR).errorMessage(ex.getMessage())
				.errorTime(LocalDateTime.now()).build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
	}
}
