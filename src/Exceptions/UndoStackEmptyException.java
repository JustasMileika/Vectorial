package Exceptions;

/**
 * @author justa
 *
 */
public class UndoStackEmptyException extends RestoreException{

	/**
	 * 
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UndoStackEmptyException()
	{
		super();
	}
	
	/**
	 * @param message
	 */
	public UndoStackEmptyException(String message)
	{
		super(message);
	}
}
