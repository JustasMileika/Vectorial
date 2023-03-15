package Exceptions;
import Actions.Action;

/**
 * @author justa
 *
 */
public class RestoreException extends Exception{

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
	Action action;
	
	/**
	 * 
	 */
	public RestoreException()
	{
		super();
	}
	
	/**
	 * @param message
	 */
	public RestoreException(String message)
	{
		super(message);
	}
	
	/**
	 * @param action
	 * @param message
	 */
	public RestoreException(Action action, String message)
	{
		super(message);
		this.action = action;
	}
}
