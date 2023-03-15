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
public class FillingShape extends Action{

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
	public FillingShape(Drawing drawing, Selection s, MyColor current) {
		super(drawing);
		this.selected = s.clone();
		this.previous = new ArrayList<MyColor>();
		for(Shape shape : s.getShapes()){
			this.previous.add(shape.getFillColor());
		}
		
		this.current = current;
	}

	/**
	 *
	 */
	public void perform() {
		for (Shape s : selected.getShapes()) {
			s.setFillColor(current);
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
			selected.getShapes().get(i).setFillColor(previous.get(i));
		}
	}
}
