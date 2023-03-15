package Shapes;
import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import src.MyColor;

/**
 * @author justa
 *
 */
public class Circle extends Shape{
	
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
	protected int radius;

	/**
	 * @param center
	 * @param outlineColor
	 * @param fillColor
	 * @param isSelected
	 * @param radius
	 * @param outlineWidth
	 */
	public Circle(Point center, MyColor outlineColor, MyColor fillColor, boolean isSelected, int radius, int outlineWidth)
	{
		super(center, outlineColor, fillColor, isSelected, outlineWidth);
		this.radius = radius;
	}

	/**
	 *
	 */
	@Override
	public void drawShape(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Color save = g2d.getColor();
		Stroke saveStroke = g2d.getStroke();
		
		
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
		
		g2d.setStroke(new BasicStroke(outlineWidth));
		g2d.setColor((Color)fill);
		g2d.fillOval(center.x - radius, center.y - radius, radius * 2, radius * 2);
		
		g2d.setColor((Color)outline);
		g2d.drawOval(center.x - radius, center.y - radius, radius * 2, radius * 2);
		g2d.setColor(save);
		g2d.setStroke(saveStroke);
		
	}

	/**
	 *
	 */
	@Override
	public void drawSelectionIndicator(Graphics g) {
		
		Color color = g.getColor();
		((Graphics2D) g).setStroke(new BasicStroke((float) 2.0));
		g.setColor(new Color(255, 0, 255));

		int selectionRadius = this.radius + 5;
		g.drawArc(center.x - selectionRadius, center.y - selectionRadius, selectionRadius * 2, selectionRadius * 2, 30, 30);
		g.drawArc(center.x - selectionRadius, center.y - selectionRadius, selectionRadius * 2, selectionRadius * 2, 120, 30);
		g.drawArc(center.x - selectionRadius, center.y - selectionRadius, selectionRadius * 2, selectionRadius * 2, 210, 30);
		g.drawArc(center.x - selectionRadius, center.y - selectionRadius, selectionRadius * 2, selectionRadius * 2, 300, 30);
		
		
		g.setColor(color);
		
	}

	/**
	 *
	 */
	@Override
	public boolean includes(Point p) {
		
		return Math.pow((p.getX() - center.getX()), 2) + Math.pow((p.getY() - center.getY()), 2) <= radius * radius;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "circ" + radius;
	}

	/**
	 *
	 */
	@Override
	public void expand(int percentage) {
		this.radius *= (100 + percentage) * 1.0 / 100;
		
	}

	/**
	 *
	 */
	@Override
	public void contract(int percentage) {
		this.radius /= ((100 + percentage) * 1.0 / 100);
		
	}

	/**
	 *
	 */
	@Override
	public int getSize() {
		return (int)(radius * radius * Math.PI);
	}
}

