package cl.falabella.code.challengefreddyparedes.error;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cl.falabella.code.challengefreddyparedes.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;


/**
 * Exception Handler
 * @author Freddy Paredes
 * @version 1.0
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = "Invalid request";
		return buildResponseEntity(HttpStatus.BAD_REQUEST, message, ex.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = "Missing path";
		return buildResponseEntity(HttpStatus.BAD_REQUEST, message, ex.getMessage());
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		List<String> errors = new ArrayList<>();
		for (FieldError fieldError : fieldErrors) {
			errors.add(fieldError.getDefaultMessage());
		}
		String message = "input data error";
		return buildResponseEntity(HttpStatus.BAD_REQUEST, message, errors);

	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
		List<String> errors = new ArrayList<>();
		for (ConstraintViolation<?> constraintViolation : constraintViolations) {
			errors.add(constraintViolation.getMessage());
		}
		String message = "Problem whit imput validation data";
		return buildResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY, message, errors);
	}

	@ExceptionHandler({ ServiceException.class })
	public ResponseEntity<Object> handleSchemaException(ServiceException ex, WebRequest request) {

		return buildResponseEntity(HttpStatus.valueOf(Integer.parseInt(ex.getCode())), ex.getMessage(), ex.getErrors());
	}

	@ExceptionHandler({ UnexpectedTypeException.class })
	public ResponseEntity<Object> handleUnexpectedTypeException(UnexpectedTypeException ex, WebRequest request) {
	
		String message = ex.getMessage();
		try {
			log.error("Error unexpected type {}",ex.getMessage());
			message ="Error, param"+ ex.getMessage().split("Check configuration for")[1] + " must be not null or blank";		
		} catch (Exception e) {
		}
		return buildResponseEntity(HttpStatus.BAD_REQUEST, "Unexpected Type Exception" ,message);
	}

	// buildResponseEntity
	private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message, String error) {
		List<String> errors = Arrays.asList(error);
		return buildResponseEntity(status, message, errors);
	}//method closure

	/**
	 * Build a central error exception handler with log
	 * @author Freddy Paredes
	 * 
	 * @param status
	 * @param message
	 * @param error
	 * @return ResponseEntity<Object>
	 */
	private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message, List<String> error) {
		ErrorResponse schemaError = new ErrorResponse(status.value(), message, error, LocalDateTime.now());
		log.error("Error status {} message {}",status.value(), message);
		return new ResponseEntity<>(schemaError, status);
	}//method closure
	
	/**
	 * Build a central error exception handler with log
	 * @author Freddy Paredes
	 * 
	 * @param status
	 * @param message
	 * @param error
	 * @return ResponseEntity<Object>
	 */
	private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String message, HashMap<String, String> error) {
		ErrorResponse schemaError = new ErrorResponse(status.value(), message, error, LocalDateTime.now());
		log.error("Error status {} message {}",status.value(), message);
		return new ResponseEntity<>(schemaError, status);
	}//method closure

	//
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<Object> handler(Exception exception) {

		if (exception.getCause().getCause() instanceof ConstraintViolationException) {
			
			ConstraintViolationException cons = (ConstraintViolationException) exception.getCause().getCause();
			Set<ConstraintViolation<?>> constraintViolations = cons.getConstraintViolations();
			
			String message = "Problem whit input data";
			List<String> errors = new ArrayList<>();
			for (ConstraintViolation<?> constraintViolation : constraintViolations) {
				errors.add(constraintViolation.getMessage());
			}

			return buildResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY, message, errors);

		}
		
		List<String> errors = Arrays.asList(exception.getMessage());
		return buildResponseEntity(HttpStatus.CONFLICT, "unexpected error", errors);
	}//method closure
}//class closure
