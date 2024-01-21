package net.demo.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> handleTrackingParcelException(CustomException ex, WebRequest wb) {
		return new ResponseEntity<>(ex.getResponseObj(), HttpStatus.OK);

	}


}
