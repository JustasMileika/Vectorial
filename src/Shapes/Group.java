package Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import Interfaces.Rotatable;
import src.MyColor;
import src.Selection;

/**
 * @author justa
 *
 */
public class Group extends Shape implements Rotatable{

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
	Selection selection;
	/**
	 * 
	 */
	int rotation;
	
	/**
	 * @param shapes
	 * @param rotation
	 */
	public Group(Selection shapes, int rotation)
	{
		super(null, null, null, false, 2);
		this.selection = shapes.clone();
		this.rotation = rotation;
	}
	
	/**
	 * @param selection
	 */
	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	/**
	 * @return
	 */
	public Selection getSelection() {
		return selection;
	}

	/**
	 *
	 */
	@Override
	public  Object clone(){
		
	      Group deepCopy = null;
		try {
			deepCopy = (Group) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	      deepCopy.selection = (Selection)((Group) deepCopy).selection.clone();
	      ((Group)deepCopy).getSelection().getShapes().clear();
			for(Shape shape : ((Group)this).getSelection().getShapes())
			{
				
				try {
					((Group)deepCopy).getSelection().getShapes().add((Shape)shape.clone());
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	      
	      return deepCopy;
	   }
	
	/**
	 *
	 */
	public boolean includes(Point p) {
		for(Shape shape : this.selection.getShapes())
		{
			if(shape.includes(p))
				return true;
		}
		return false;
	}
	
	/**
	 *
	 */
	public void setIsSelected(boolean isSelected)
	{
		for(Shape shape : this.selection.getShapes())
		{
			shape.setIsSelected(isSelected);
		}
	}
	
	/**
	 *
	 */
	public boolean getIsSelected()
	{
		for(Shape shape : this.selection.getShapes())
		{
			if(shape.getIsSelected())
				return true;
		}
		return false;
	}
	
	/**
	 * @param shape
	 */
	public void addShape(Shape shape)
	{
		this.selection.getShapes().add(shape);
	}
	
	/**
	 * @param shape
	 */
	public void removeShape(Shape shape)
	{
		this.selection.getShapes().remove(shape);
	}
	
	/**
	 *
	 */
	public void move(int x, int y)
	{
		for(Shape shape : this.selection.getShapes())
		{
			shape.move(x, y);
		}
	}

	
	/**
	 *
	 */
	public void drawShape(Graphics g) {

		for(Shape shape : this.selection.getShapes())
		{
			shape.drawShape(g);
		}
	}
	
	/**
	 *
	 */
	public void drawSelectionIndicator(Graphics g) {
		
		for(Shape shape : this.selection.getShapes())
		{
			shape.drawSelectionIndicator(g);
		}
	}
	
	/**
	 *
	 */
	@Override
	public void setOutlineColor(MyColor outlineColor) {
		if(outlineColor.getColor().get(0) instanceof Color)
		{
			for(int i = 0; i < selection.getShapes().size(); ++i)
			{
				MyColor color = new MyColor();
				color.getColor().add(outlineColor.getColor().get(0));
				selection.getShapes().get(i).setOutlineColor(color);
			}
		}
		
		for(int i = 0; i < outlineColor.getColor().size(); ++i)
		{
			if(outlineColor.getColor().get(i) instanceof Color)
			{
				MyColor color = new MyColor();
				color.getColor().add(outlineColor.getColor().get(i));
				
				selection.getShapes().get(i).setOutlineColor((MyColor)color);
			}
			else
			{
				selection.getShapes().get(i).setOutlineColor((MyColor)outlineColor.getColor().get(i));
			}
			
		}
	}
	
	/**
	 *
	 */
	@Override
	public MyColor getOutlineColor() {
		MyColor color = new MyColor();
		for(Shape s : selection.getShapes())
		{
			color.getColor().add(s.getOutlineColor());
		}
		return color;
	}
	
	/**
	 *
	 */
	@Override
	public void setFillColor(MyColor fillerColor) {
		
		if(fillerColor.getColor().get(0) instanceof Color)
		{
			for(int i = 0; i < selection.getShapes().size(); ++i)
			{
				MyColor color = new MyColor();
				color.getColor().add(fillerColor.getColor().get(0));
				selection.getShapes().get(i).setFillColor(color);
			}
				
		}
		
		for(int i = 0; i < fillerColor.getColor().size(); ++i)
		{
			if(fillerColor.getColor().get(i) instanceof Color)
			{
				MyColor color = new MyColor();
				color.getColor().add(fillerColor.getColor().get(i));
				
				selection.getShapes().get(i).setFillColor((MyColor)color);
			}
			else
			{
				selection.getShapes().get(i).setFillColor((MyColor)fillerColor.getColor().get(i));
			}
			
		}
	}
	
	/**
	 *
	 */
	@Override
	public MyColor getFillColor() {
		MyColor color = new MyColor();
		for(Shape s : selection.getShapes())
		{
			color.getColor().add(s.getFillColor());
		}
		return color;
	}

	/**
	 *
	 */
	@Override
	public void expand(int percentage) {
		for(Shape shape : this.selection.getShapes())
		{
			shape.expand(percentage);
		}
		
	}

	/**
	 *
	 */
	@Override
	public void contract(int percentage) {
		for(Shape shape : this.selection.getShapes())
		{
			shape.contract(percentage);
		}
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		String group = "";
		for(Shape s : selection.getShapes())
		{
			group += s + "|||";
		}
		return group;
	}

	/**
	 *
	 */
	@Override
	public int getSize() {
		return Integer.MAX_VALUE;
	}

	/**
	 *
	 */
	@Override
	public void setRotation(int rotation) {
		this.rotation = rotation;
		
	}

	/**
	 *
	 */
	@Override
	public int getRotation() {
		return this.rotation;
	}

	/**
	 *
	 */
	@Override
	public void rotate(int rotation) {
		for(Shape s : selection.getShapes())
		{
			if(s instanceof Rotatable)
			{
				((Rotatable) s).rotate(rotation);
			}
				
		}
	}
}
