package es.uniovi.asw.util;

/**
 * @author ivan
 *
 */
public class ReadCensusException extends Exception {
	private static final long serialVersionUID = -308694287126638961L;

	public ReadCensusException() {}

	public ReadCensusException(String message) {
		super(message);
	}

	public ReadCensusException(Throwable cause) {
		super(cause);
	}

	public ReadCensusException(String message, Throwable cause) {
		super(message, cause);
	}
}
