package Actions;

import GUIComponents.Drawing;

import Shapes.Shape;
import src.Selection;

/**
 * @author justa
 *
 */
public class RaiseShape extends Action{

	/**
	 * 
	 */
	Selection selected;
	
	/**
	 * @param drawing
	 * @param selected
	 */
	public RaiseShape(Drawing drawing, Selection selected) {
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
				int index = drawing.getShapes().indexOf(s);
				if (index < drawing.getShapes().size() - 1)
				{
					drawing.getShapes().remove(s);
					drawing.getShapes().add(index + 1, s);
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
				int index = drawing.getShapes().indexOf(s);
				if (index > 0)
				{
					drawing.getShapes().remove(s);
					drawing.getShapes().add(index - 1, s);
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
