package src;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author justa
 *
 */
public class MyColor implements Serializable{

	/**
	 * 
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	ArrayList<Object> color;
	
	/**
	 * 
	 */
	public MyColor()
	{
		this.color = new ArrayList<Object>();
	}

	/**
	 * @return
	 */
	public ArrayList<Object> getColor() {
		return color;
	}

	/**
	 * @param color
	 */
	public void setColor(ArrayList<Object> color) {
		this.color = color;
	}
}
