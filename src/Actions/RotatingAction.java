package Actions;

import GUIComponents.Drawing;


import Interfaces.Rotatable;
import Shapes.Shape;
import src.Selection;

/**
 * @author justa
 *
 */
public class RotatingAction extends Action{

	/**
	 * 
	 */
	Selection selected;
	
	/**
	 * @param drawing
	 * @param selected
	 */
	public RotatingAction(Drawing drawing, Selection selected) {
		super(drawing);
		this.selected = selected.clone();
	}

	/**
	 *
	 */
	@Override
	public void perform() {
		for (Shape s : selected.getShapes()) {
			{
				if (s instanceof Rotatable)
				{
					((Rotatable)s).rotate(30);
				}
			}
		}
	}

	/**
	 *
	 */
	@Override
	public void undo() {
		for (Shape s : selected.getShapes()) {
			{
				if (s instanceof Rotatable)
				{
					((Rotatable)s).rotate(-30);
				}
			}
		}
		
	}

	/**
	 *
	 */
	@Override
	public void redo() {
		this.perform();
	}

}
