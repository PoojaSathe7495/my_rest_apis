
package com.spring.restful.webservices.springrestfulwebservice.exception;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.restful.webservices.springrestfulwebservice.model.ErrorDetails;

@ControllerAdvice
public class CustomiseResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	String errorMessage = " ";

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.NOT_FOUND);
	}

	// use handleMethodArgumentNotValid for validation Exception

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		int errorCount = ex.getErrorCount();
		
		Map<String, String> map;
		/*
		 * for(int i =0;i< errorCount;i++) { errorMessage = errorMessage +
		 * 
		 * ex.getFieldErrors().get(i).getDefaultMessage()+ ","; }
		 */
		ex.getBindingResult().getAllErrors().forEach(error -> {
			// String errorField =((FieldError) error).getField();
			errorMessage = errorMessage + error.getDefaultMessage();
			// map.put(errorField, errorMessage1);
		});

		// ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),
		// ex.getFieldError().getDefaultMessage(), request.getDescription(false));
		ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(), errorMessage, request.getDescription(false));
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/*
	 * @ResponseStatus(HttpStatus.BAD_REQUEST)
	 * 
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public Map<String,
	 * String> handleValidationExceptions( MethodArgumentNotValidException ex) {
	 * Map<String, String> errors = new HashMap<>();
	 * ex.getBindingResult().getAllErrors().forEach((error) -> { String fieldName =
	 * ((FieldError) error).getField(); String errorMessage =
	 * error.getDefaultMessage(); errors.put(fieldName, errorMessage); }); return
	 * errors; }
	 */

}
