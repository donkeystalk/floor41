package octane.floor.exceptions;

public class DuplicateUsernameException extends RuntimeException {

	private static final long serialVersionUID = -1115011678458724665L;
	
	public DuplicateUsernameException(String message)
	{
		super(message);
	}

}
