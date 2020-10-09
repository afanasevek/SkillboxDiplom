package ru.afanasev.diplom.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExeptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CommentException.class)
	protected ResponseEntity<AwesomeException> handleCommentExeption() {
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	private static class AwesomeException {

	}

}
