package cl.falabella.code.challengefreddyparedes.error;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * DTO Error model
 * 
 * @author Freddy Paredes
 *
 */
@AllArgsConstructor
@Data
public class ErrorResponse{

	private final int code;
	private final String message;
	private final List<String> errors;
	private final HashMap<String, String> errorsDetails;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private final LocalDateTime timestamp;

	public ErrorResponse(int code, String message, List<String> errors, LocalDateTime timestamp) {
		super();
		this.code = code;
		this.message = message;
		this.errors = errors;
		this.timestamp = timestamp;
		this.errorsDetails = null;
	}

	public ErrorResponse(int code, String message, String error, LocalDateTime timestamp) {
		super();
		this.code = code;
		this.message = message;
		errors = Arrays.asList(error);
		this.timestamp = timestamp;
		this.errorsDetails = null;
	}

	public ErrorResponse(int code, String message, HashMap<String, String> error, LocalDateTime timestamp) {
		super();
		this.code = code;
		this.message = message;
		errors = Arrays.asList("ServiceException");;
		this.timestamp = timestamp;
		this.errorsDetails = error;
	}

}//class closure