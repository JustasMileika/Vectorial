package Actions;

import GUIComponents.Drawing;

public abstract class Action {
	

	/**
	 * 
	 */
	public Drawing drawing;
	

	/**
	 * @param drawing
	 */
	Action(Drawing drawing)
	{
		this.drawing = drawing;
	}
	

	/**
	 * 
	 */
	public abstract void perform();
	

	/**
	 * 
	 */
	public abstract void undo();

	/**
	 * 
	 */
	public abstract void redo();
}
