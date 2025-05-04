package dev.ms.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -1562007444946022471L;

	public CustomerAlreadyExistsException(String message) {
		super(message);
	}

}
