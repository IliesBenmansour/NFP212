package allumettes;

// quand un joueur veut tricher il lance cette exception
public class OperationInterditeException extends RuntimeException {
	public OperationInterditeException(String message) {
		super(message);
	}

}
