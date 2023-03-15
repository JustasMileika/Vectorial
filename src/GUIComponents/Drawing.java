package GUIComponents;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import Shapes.*;

/**
 * @author justa
 *
 */
public class Drawing extends JPanel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private List<Shape> shapes;
	
	
	/**
	 * 
	 */
	public Drawing()
	{
		shapes = new ArrayList<Shape>();
	}
	
	/**
	 * 
	 */
	public void clear()
	{
		shapes.clear();
	}
	
	/**
	 * @param shape
	 */
	public void addShape(Shape shape)
	{
		shapes.add(shape);
	}
	
	/**
	 * @param shape
	 */
	public void removeShape(Shape shape)
	{
		shapes.remove(shape);
	}
	
	/**
	 * @return
	 */
	public List<Shape> getShapes() {
		return shapes;
	}

	/**
	 * @param shape
	 */
	public void copyShape(Shape shape)
	{
		try {
			this.addShape((Shape)shape.clone());
		}
		catch(Exception CloneNotSupportedException)
		{
			System.out.println("Cannot clone");
		}
	}
	
	/**
	 * @return
	 */
	public int getShapeCount()
	{
		return shapes.size();
	}
	

	/**
	 *
	 */
	@Override
	public String toString()
	{
		String s = "";
		for(Shape shape : shapes)
		{
			s += shape.toString() + ", ";
		}
		if(s.length() > 0)
		{
			return s.substring(0, s.length() - 2);
		}
		else
			return "EMPTY";
	}
	

	/**
	 *
	 */
	@Override
     public void paintComponent(Graphics g)
     {
    	 super.paintComponent(g);
	     Graphics2D g2d = (Graphics2D) g;
	   	
	   	 
	   	 for(Shape shape : shapes)
	   	 {
	   		 shape.drawShape(g2d);
	   	 }
	   	 for(Shape shape : shapes)
	   	 {
	   		 if(shape.getIsSelected())
	   		 {
	   			 shape.drawSelectionIndicator(g2d);
	   		 }
	   	 }
     }
}
