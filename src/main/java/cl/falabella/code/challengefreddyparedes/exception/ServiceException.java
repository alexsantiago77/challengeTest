package cl.falabella.code.challengefreddyparedes.exception;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * Build single central Exception
 * @author Freddy Paredes
 * @version 1.0
 */
public class ServiceException extends RuntimeException {
	/**
	 * Serialized
	 */
	private static final long serialVersionUID = 4442125330868704757L;
	
	/**
	 * error code
	 */
	private String code;

	/**
	 * timestamp
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	/**
	 * error list
	 */
	private HashMap<String, String> errors;


	/**
	 * @param code
	 * @param message
	 */
	public ServiceException(String code, String message) {
		super(message);
		this.code = code;
		this.timestamp = LocalDateTime.now();

	}

	/**
	 * 
	 * @param code
	 * @param message
	 * @param errors
	 */
	public ServiceException(String code, String message, HashMap<String,String> errors) {
		super(message);
		this.code = code;
		this.timestamp = LocalDateTime.now();
		this.errors = errors;

	}
	
	
	/** 
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	
	/** 
	 * @return LocalDateTime
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	
	/** 
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getErrors() {
		return errors;
	}
	

}//class closure