package challenge.deckcards.exception;

/**
 * Exception throw when a handled bad request occured in the controllers
 * 
 * @author chris
 *
 */
public class BadRequestException extends Exception {
	private static final long serialVersionUID = 1L;

	public BadRequestException(String msg) {
		super(msg);
	}
}
