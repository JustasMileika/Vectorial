package Actions;

import GUIComponents.Drawing;


import Shapes.Shape;
import src.Selection;



/**
 * @author justa
 *
 */
public class ShapeMoving extends Action{
	/**
	 * 
	 */
	Selection selected;
	/**
	 * 
	 */
	int x;
	/**
	 * 
	 */
	int y;

	/**
	 * @param drawing
	 * @param s
	 * @param x
	 * @param y
	 */
	public ShapeMoving(Drawing drawing, Selection s, int x, int y) {
		super(drawing);
		this.selected = s.clone();
		this.x = x;
		this.y = y;
	}

	/**
	 *
	 */
	public void perform() {
		for (Shape s : selected.getShapes()) {
			s.move((int)x, (int)y);
		}
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return null;
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
		for (Shape s : selected.getShapes()) {
			s.move((int)-x, (int)-y);
		}
	}
}
