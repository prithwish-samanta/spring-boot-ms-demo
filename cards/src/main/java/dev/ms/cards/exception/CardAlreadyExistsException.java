package dev.ms.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -1697837293785111804L;

	public CardAlreadyExistsException(String message) {
		super(message);
	}

}