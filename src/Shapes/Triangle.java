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
public class Triangle extends Shape implements Rotatable{

	/**
	 * 
	 */
	/**
	 * 
	 */
	int height;
	/**
	 * 
	 */
	int base;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param center
	 * @param outlineColor
	 * @param fillColor
	 * @param isSelected
	 * @param height
	 * @param base
	 * @param rotation
	 * @param outlineWidth
	 */
	public Triangle(Point center, MyColor outlineColor, MyColor fillColor, boolean isSelected, int height, int base, int rotation, int outlineWidth) {
		super(center, outlineColor, fillColor, isSelected, outlineWidth);
		this.base = base;
		this.height = height;
		this.rotation = rotation;
	}

	/**
	 *
	 */
	@Override
	public  Object clone(){
	
      Triangle deepCopy = null;
	try {
		deepCopy = (Triangle) super.clone();
	} catch (CloneNotSupportedException e) {
		e.printStackTrace();
	}
 
      return deepCopy;
   }
	
	/**
	 *
	 */
	@Override
	public void expand(int percentage) {
		this.base = (int)((double)this.base * (((100 + percentage) * 1.0) / 100));
		this.height = (int)((double)this.height * (((100 + percentage) * 1.0) / 100));
		
	}

	/**
	 *
	 */
	@Override
	public void contract(int percentage) {
		this.base = (int)((double)this.base / (((100 + percentage) * 1.0) / 100));
		this.height = (int)((double)this.height / (((100 + percentage) * 1.0) / 100));
		
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
		return rotation;
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
		g.fillPolygon(new int[] {(int)(super.center.getX()), (int)(super.center.getX() - base / 2), (int)(super.center.getX() + base / 2)},
				new int[] {(int)(super.center.getY() - height/2), (int)(center.y + height / 2), (int)(center.y + height / 2)}, 3);
		g2d.setColor((Color)outline);
		g.drawPolygon(new int[] {(int)(super.center.getX()), (int)(super.center.getX() - base / 2), (int)(super.center.getX() + base / 2)},
				new int[] {(int)(super.center.getY() - height/2), (int)(center.y + height / 2), (int)(center.y + height / 2)}, 3);
		
		g2d.setTransform(reset);
		g2d.setColor(save);
		g2d.setStroke(saveStroke);
		
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
		
		int len = base / 5;
		int off = 10;
		
		System.out.println(len);
		
		AffineTransform reset = g2d.getTransform();
		g2d.rotate(Math.toRadians(rotation), center.x, center.y);

		Point top = new Point((int)(super.center.getX()), (int)(super.center.getY() - height/2));
		Point bottomLeft = new Point((int)(super.center.getX() - base / 2), top.y + height);
		Point bottomRight = new Point((int)(super.center.getX() + base / 2), top.y + height);
		
		g.drawPolyline(new int[] {(int)(top.getX() - len / 2), (int)(top.getX()), (int)(top.getX() + len / 2)},
				new int[] {(int)(top.y + len / 4), (int)(top.getY() - len/4), (int)(top.y + len / 4)}, 3);
		
		len = height / 6;
		
		g.drawPolyline(new int[] {(int)(bottomLeft.getX() + len / 2), (int)(bottomLeft.getX() - off/ 1.5), (int)(bottomLeft.getX() + len / 2.5)},
				new int[] {(int)(bottomLeft.y) + off / 3, (int)(bottomLeft.getY() + off / 3), (int)(bottomLeft.y - len / 1.2)}, 3);
		
		g.drawPolyline(new int[] {(int)(bottomRight.getX() - len / 2), (int)(bottomRight.getX() + off/ 1.5), (int)(bottomRight.getX() - len / 2.5)},
				new int[] {(int)(bottomRight.y) + off / 3, (int)(bottomRight.getY() + off / 3), (int)(bottomRight.y - len / 1.2)}, 3);

		g.setColor(color);
		((Graphics2D) g).setStroke(saveStroke);
		g2d.setTransform(reset);
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
	public boolean includes(Point p) {
		Point top = new Point((int)(super.center.getX()), (int)(super.center.getY() - height/2));
		Point bottomLeft = new Point((int)(super.center.getX() - base / 2), top.y + height);
		Point bottomRight = new Point((int)(super.center.getX() + base / 2), top.y + height);
		
		Point topLeftRotated = rotate(top.x, top.y, center.x, center.y, rotation);
		Point bottomLeftRotated = rotate(bottomLeft.x, bottomLeft.y, center.x, center.y, rotation);
		Point bottomRightRotated = rotate(bottomRight.x, bottomRight.y, center.x, center.y, rotation);
		
		Polygon pol = new Polygon();
		pol.addPoint(topLeftRotated.x, topLeftRotated.y);
		pol.addPoint(bottomRightRotated.x, bottomRightRotated.y);
		pol.addPoint(bottomLeftRotated.x, bottomLeftRotated.y);
		
		
		
		return pol.contains(p);
	}

	/**
	 *
	 */
	@Override
	public int getSize() {
		
		return (int)(base * base / 2);
	}

}
