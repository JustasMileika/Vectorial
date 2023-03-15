package Actions;

import GUIComponents.Drawing;
import Shapes.Group;
import Shapes.Shape;
import src.Selection;

/**
 * @author justa
 *
 */
public class GroupingShapes extends Action{

	/**
	 * 
	 */
	Selection selected;
	/**
	 * 
	 */
	Shape group;

	/**
	 * @param drawing
	 * @param s
	 */
	public GroupingShapes(Drawing drawing, Selection s) {
		super(drawing);
		this.selected = s.clone();
		group = new Group(selected, 0);
	}

	/**
	 *
	 */
	public void perform() {
		
		
		for (Shape s : selected.getShapes()) {
			s.setIsSelected(false);
			
			drawing.removeShape(s);
		}
		drawing.addShape(group);
		group.setIsSelected(false);
	}

	/**
	 *
	 */
	public void redo() {
		perform();
	}

	/**
	 *
	 */
	public void undo() {
		drawing.removeShape(group);
		for (Shape s : selected.getShapes()) {
			drawing.addShape(s);
		}
	}
}
