package Actions;

import GUIComponents.Drawing;
import Shapes.Group;
import Shapes.Shape;
import src.Selection;

/**
 * @author justa
 *
 */
public class CopyingShape extends Action{

	/**
	 * 
	 */
	private Selection selection;
	/**
	 * 
	 */
	private Selection newShape;
	
	/**
	 * @param drawing
	 * @param selection
	 */
	public CopyingShape(Drawing drawing, Selection selection)
	{
		super(drawing);
		this.selection = selection;
		this.newShape = new Selection();
	}

	/**
	 *
	 */
	public void perform()
	{
		for(Shape s : selection.getShapes())
		{
			Shape clone = null;
			try {
				
				if(s.getClass().equals(Group.class))
				{
					
					clone = (Shape)((Group) s).clone();
					
				}
				else
				{
					clone = (Shape)s.clone();
				}
				
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(clone != null)
			{
				clone.move(5, 5);
				clone.setIsSelected(false);
				newShape.add(clone);
				drawing.addShape(clone);
			}
			
			
			
		}
	}
	
	/**
	 *
	 */
	public void undo()
	{
		for(Shape s : newShape.getShapes())
			super.drawing.removeShape(s);
	}
	
	/**
	 *
	 */
	public void redo()
	{
		this.perform();
	}
}
