package src;
import java.io.Serializable;
import java.util.ArrayList;

import Shapes.*;

/**
 * @author justa
 *
 */
public class Selection implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private ArrayList<Shape> shapes;
	
	/**
	 * 
	 */
	public Selection()
	{
		shapes = new ArrayList<>();
	}
	
	/**
	 * @param shapes
	 */
	public Selection(ArrayList<Shape> shapes)
	{
		this.shapes = shapes;
	}
	
	/**
	 * @param shape
	 */
	public void add(Shape shape)
	{
		if(!shapes.contains(shape))
		{
			shapes.add(shape);
		}
	}
	
	/**
	 *
	 */
	public Selection clone() {
		@SuppressWarnings("unchecked")
		ArrayList<Shape> clone = (ArrayList<Shape>) shapes.clone();
		return new Selection(clone);
	}

	/**
	 * @return
	 */
	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	
}
