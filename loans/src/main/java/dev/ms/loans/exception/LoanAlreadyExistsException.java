package dev.ms.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 3698665451312146515L;

	public LoanAlreadyExistsException(String message) {
		super(message);
	}

}