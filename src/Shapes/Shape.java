package Shapes;

import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

import Interfaces.Expandable;
import src.MyColor;

public abstract class Shape implements Expandable, Cloneable, Serializable{
	
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
	protected Point center;
	/**
	 * 
	 */
	protected MyColor outlineColor;
	/**
	 * 
	 */
	protected MyColor fillColor;
	/**
	 * 
	 */
	protected int outlineWidth;
	/**
	 * 
	 */
	protected boolean isSelected;
	/**
	 * 
	 */
	protected int rotation;
	
	
	
	/**
	 * @return
	 */
	public MyColor getOutlineColor() {
		return outlineColor;
	}

	/**
	 * @return
	 */
	public MyColor getFillColor() {
		return fillColor;
	}

	/**
	 * @param fillColor
	 */
	public void setFillColor(MyColor fillColor) {
		this.fillColor = fillColor;
	}

	/**
	 * @param outlineColor
	 */
	public void setOutlineColor(MyColor outlineColor) {
		this.outlineColor = outlineColor;
	}

	/**
	 * @return
	 */
	public int getOutlineWidth() {
		return outlineWidth;
	}

	/**
	 * @param outlineWidth
	 */
	public void setOutlineWidth(int outlineWidth) {
		this.outlineWidth = outlineWidth;
	}

	/**
	 * @param center
	 * @param outlineColor
	 * @param fillColor
	 * @param isSelected
	 * @param outlineWidth
	 */
	public Shape(Point center, MyColor outlineColor, MyColor fillColor, boolean isSelected, int outlineWidth)
	{
		this.center = center;
		this.outlineColor = outlineColor;
		this.fillColor = fillColor;
		this.isSelected = isSelected;
		this.outlineWidth = outlineWidth;
	}
	
	/**
	 * @param x
	 * @param y
	 */
	public void move(int x, int y) {
		center.x += x;
		center.y += y;
	}
	
	/**
	 * @param g
	 */
	public abstract void drawShape(Graphics g);
	/**
	 * @param g
	 */
	public abstract void drawSelectionIndicator(Graphics g);
	/**
	 * @param p
	 * @return
	 */
	public abstract boolean includes(Point p);
	/**
	 * @return
	 */
	public abstract int getSize();
	
	
	/**
	 *
	 */
	@Override
	public  Object clone() throws CloneNotSupportedException{
	
      Shape deepCopy = (Shape) super.clone();
      
      if(this.center != null)
    	  deepCopy.center = (Point) this.center.clone();
    		  
      return deepCopy;
   }
	

	/**
	 * @return
	 */
	public boolean getIsSelected() {
		return isSelected;
	}

	/**
	 * @param isSelected
	 */
	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
