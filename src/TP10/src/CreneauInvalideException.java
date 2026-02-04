package TP10.src;

/**
 * CreneauInvalideException indique qu'une date n'est pas valide.
 */
public class CreneauInvalideException extends RuntimeException {
	public CreneauInvalideException() {
		super();
	}

	public CreneauInvalideException(String message) {
		super(message);
	}
}
