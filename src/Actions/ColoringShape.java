package Actions;

import java.util.ArrayList;
import java.util.List;

import GUIComponents.Drawing;

import Shapes.Shape;
import src.MyColor;
import src.Selection;

/**
 * @author justa
 *
 */
public class ColoringShape extends Action{
	/**
	 * 
	 */
	Selection selected;
	/**
	 * 
	 */
	MyColor current;
	/**
	 * 
	 */
	List<MyColor> previous;

	/**
	 * @param drawing
	 * @param s
	 * @param current
	 */
	public ColoringShape(Drawing drawing, Selection s, MyColor current) {
		super(drawing);
		this.selected = s.clone();
		this.previous = new ArrayList<MyColor>();
		for(Shape shape : s.getShapes()){
			this.previous.add(shape.getOutlineColor());
		}
		this.current = current;
	}

	/**
	 *
	 */
	public void perform() {
		for (Shape s : selected.getShapes()) {
			s.setOutlineColor(current);
		}
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
		for(int i = 0; i < previous.size(); ++i)
		{
			selected.getShapes().get(i).setOutlineColor(previous.get(i));
		}
	}
}
