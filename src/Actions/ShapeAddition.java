package Actions;

import Shapes.Shape;

import GUIComponents.*;


/**
 * @author justa
 *
 */
public class ShapeAddition extends Action{
	
	/**
	 * 
	 */
	private Shape shape;
	
	/**
	 * @param drawing
	 * @param shape
	 */
	public ShapeAddition(Drawing drawing, Shape shape)
	{
		super(drawing);
		this.shape = shape;
	}

	/**
	 *
	 */
	public void perform()
	{
		super.drawing.addShape(shape);
	}
	
	/**
	 *
	 */
	public void undo()
	{
		super.drawing.removeShape(shape);
	}
	
	/**
	 *
	 */
	public void redo()
	{
		this.perform();
	}
}
