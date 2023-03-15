package Shapes;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;

import Interfaces.Rotatable;
import src.MyColor;

/**
 * @author justa
 *
 */
public class Rectangle extends Shape implements Rotatable{

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
	protected int width;
	/**
	 * 
	 */
	protected int height;
	/**
	 * 
	 */
	int rotation;
	
	/**
	 *
	 */
	public int getRotation() {
		return rotation;
	}

	/**
	 *
	 */
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
	
	/**
	 *
	 */
	@Override
	public  Object clone(){
	
      Rectangle deepCopy = null;
	try {
		deepCopy = (Rectangle) super.clone();
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      return deepCopy;
   }

	/**
	 * @param center
	 * @param outlineColor
	 * @param fillColor
	 * @param isSelected
	 * @param width
	 * @param height
	 * @param rotation
	 * @param outlineWidth
	 */
	public Rectangle(Point center, MyColor outlineColor, MyColor fillColor, boolean isSelected, int width, int height, int rotation, int outlineWidth)
	{
		super(center, outlineColor, fillColor, isSelected, outlineWidth);
		this.width = width;
		this.height = height;
		this.rotation = rotation;
	}
	
	/**
	 *
	 */
	@Override
	public void drawShape(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Color save = g2d.getColor();
		Stroke saveStroke = g2d.getStroke();
		
		g2d.setStroke(new BasicStroke(outlineWidth));
		
		AffineTransform reset = g2d.getTransform();
		g2d.rotate(Math.toRadians(rotation), center.x, center.y);
		
		Object fill = fillColor.getColor().get(0);
		while(!(fill instanceof Color))
		{
			fill = ((MyColor) fill).getColor().get(0);
		}
		
		Object outline = outlineColor.getColor().get(0);
		while(!(outline instanceof Color))
		{
			outline = ((MyColor) outline).getColor().get(0);
		}
		
		g2d.setColor((Color)fill);
		g2d.fillRect((int)(super.center.getX() - width/2), (int)(super.center.getY() - height/2), width, height);
		g2d.setColor((Color)outline);
		g2d.drawRect((int)(super.center.getX() - width/2), (int)(super.center.getY() - height/2), width, height);
		
		g2d.setTransform(reset);
		g2d.setColor(save);
		g2d.setStroke(saveStroke);
	}
	
	/**
	 * @param x
	 * @param y
	 * @param ox
	 * @param oy
	 * @param a
	 * @return
	 */
	public Point rotate(int x, int y, int ox, int oy, int a)
	{
		return new Point((int)((x-ox)*Math.cos(Math.toRadians(a)) - (y-oy)*Math.sin(Math.toRadians(a)) + ox),
	             (int)((x-ox)*Math.sin(Math.toRadians(a)) + (y-oy)*Math.cos(Math.toRadians(a)) + oy));
	}
	 

	/**
	 *
	 */
	@Override
	public void drawSelectionIndicator(Graphics g) {
		
		Graphics2D g2d = ((Graphics2D) g);
		Stroke saveStroke = ((Graphics2D) g).getStroke();
		Color color = g.getColor();
		((Graphics2D) g).setStroke(new BasicStroke((float) 2.0));
		g.setColor(new Color(255, 0, 255));

		int len = Math.max(width, height) / 10;
		int off = 5;
		
		AffineTransform reset = g2d.getTransform();
		g2d.rotate(Math.toRadians(rotation), center.x, center.y);

		Point p1 = new Point((int)(super.center.getX() - width/2), (int)(super.center.getY() - height/2));
		Point p2 = new Point(p1.x + width, p1.y + height);

		g.drawPolyline(
				new int[] { p1.x - off, p1.x - off, p1.x - off + len },
				new int[] { p1.y - off + len, p1.y - off, p1.y - off }, 3);

		g.drawPolyline(
				new int[] { p2.x + off - len, p2.x + off, p2.x + off },
				new int[] { p2.y + off, p2.y + off, p2.y + off - len }, 3);

		g.drawPolyline(
				new int[] { p2.x + off - len, p2.x + off, p2.x + off },
				new int[] { p1.y - off, p1.y - off, p1.y - off + len }, 3);

		g.drawPolyline(
				new int[] { p1.x - off, p1.x - off, p1.x - off + len },
				new int[] { p2.y + off - len, p2.y + off, p2.y + off }, 3);
		
		g.setColor(color);
		((Graphics2D) g).setStroke(saveStroke);
		g2d.setTransform(reset);
	}

	/**
	 *
	 */
	@Override
	public boolean includes(Point p) {
		
		
		Point topLeft = new Point((int)(super.center.getX() - width/2), (int)(super.center.getY() - height/2));
		Point topRight = new Point(topLeft.x + width, topLeft.y);
		Point bottomLeft = new Point(topLeft.x, topLeft.y + height);
		Point bottomRight = new Point(bottomLeft.x + width, bottomLeft.y);
		
		Point topLeftRotated = rotate(topLeft.x, topLeft.y, center.x, center.y, rotation);
		Point topRightRotated = rotate(topRight.x, topRight.y, center.x, center.y, rotation);
		Point bottomLeftRotated = rotate(bottomLeft.x, bottomLeft.y, center.x, center.y, rotation);
		Point bottomRightRotated = rotate(bottomRight.x, bottomRight.y, center.x, center.y, rotation);
		
		Polygon pol = new Polygon();
		pol.addPoint(topLeftRotated.x, topLeftRotated.y);
		pol.addPoint(topRightRotated.x, topRightRotated.y);
		pol.addPoint(bottomRightRotated.x, bottomRightRotated.y);
		pol.addPoint(bottomLeftRotated.x, bottomLeftRotated.y);
		
		
		
		return pol.contains(p);
	} 

	/**
	 *
	 */
	@Override
	public void rotate(int rotation) {
		this.rotation += rotation;
		if(rotation > 360)
			rotation -= 360;
	}

	/**
	 *
	 */
	@Override
	public void expand(int percentage) {
		this.width = (int)((double)this.width * (((100 + percentage) * 1.0) / 100));
		this.height = (int)((double)this.height * (((100 + percentage) * 1.0) / 100));
	}

	/**
	 *
	 */
	@Override
	public void contract(int percentage) {
		this.width = (int)((double)this.width / (((100 + percentage) * 1.0) / 100));
		this.height = (int)((double)this.height / (((100 + percentage) * 1.0) / 100));
	}

	/**
	 *
	 */
	@Override
	public int getSize() {
		return width * height;
	}
}
