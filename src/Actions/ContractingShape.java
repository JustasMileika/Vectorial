package Actions;

import GUIComponents.Drawing;

import Shapes.Shape;
import src.Selection;

/**
 * @author justa
 *
 */
public class ContractingShape extends Action{

	/**
	 * 
	 */
	Selection selected;
	/**
	 * 
	 */
	int percentage;

	/**
	 * @param drawing
	 * @param s
	 * @param percentage
	 */
	public ContractingShape(Drawing drawing, Selection s, int percentage) {
		super(drawing);
		this.selected = s.clone();
		this.percentage = percentage;
	}

	/**
	 *
	 */
	public void perform() {
		for (Shape s : selected.getShapes()) {
			s.contract(percentage);
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
		for (Shape s : selected.getShapes()) {
			s.expand(percentage);
		}
	}
}
