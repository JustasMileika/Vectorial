package Actions;

import java.util.Stack;

import Exceptions.*;

/**
 * @author justa
 *
 */
public class RestoreController {

	/**
	 * 
	 */
	public Stack<Action> undoStack;
	/**
	 * 
	 */
	public Stack<Action> redoStack;

	/**
	 * 
	 */
	public RestoreController() {
		this.undoStack = new Stack<Action>();
		this.redoStack = new Stack<Action>();
	}

	/**
	 * @param action
	 */
	public void addAction(Action action) {
		this.redoStack.clear();
		this.undoStack.push(action);
	}

	/**
	 * @throws RestoreException
	 */
	public void redo() throws RestoreException{
		Action action = null;
		if(this.redoStack.size() <= 0)
		{
			throw new RestoreException("Cannot perfrom restore action");
		}
		else
		{
			try {
				action = this.redoStack.pop();
				action.redo();
				this.undoStack.push(action);
			}
			catch (Exception e)
			{
				throw new RestoreException(action, "Cannot perfrom restoring action");
			}
		}
	}

	/**
	 * @throws UndoStackEmptyException
	 * @throws RestoreException
	 */
	public void undo() throws UndoStackEmptyException, RestoreException{
		Action action = null;
		if(this.undoStack.size() <= 0)
		{
			throw new UndoStackEmptyException("Cannot perform 'undo'");
		}
		else
		{
			try {
				action = this.undoStack.pop();
				action.undo();
				this.redoStack.push(action);
			}
			catch (Exception e)
			{
				throw new RestoreException(action, "Cannot perfrom restoring action");
			}
			
		}
	}
}
