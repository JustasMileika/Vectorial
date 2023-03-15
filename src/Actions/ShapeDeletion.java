package Actions;

import GUIComponents.Drawing;


import src.Selection;

/**
 * @author justa
 *
 */
public class ShapeDeletion extends Action{
	
	/**
	 * 
	 */
	Selection selected;
	
	/**
	 * @param drawing
	 * @param selected
	 */
	public ShapeDeletion(Drawing drawing, Selection selected)
	{
		super(drawing);
		this.selected = selected.clone();
	}
	
	/**
	 *
	 */
	public void perform()
	{
		for(int i = 0; i < selected.getShapes().size(); ++i)
		{
			drawing.removeShape(selected.getShapes().get(i));
		}
		
	}
	
	/**
	 *
	 */
	public void undo()
	{
		for(int i = 0; i < selected.getShapes().size(); ++i)
		{
			drawing.addShape(selected.getShapes().get(i));
		}
	}
	
	/**
	 *
	 */
	public void redo()
	{
		this.perform();
	}
}
