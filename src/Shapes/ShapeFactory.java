package Shapes;

import java.awt.Color;

import java.awt.Point;

import ENUMS.ShapeType;
import src.MyColor;

/**
 * @author justa
 *
 */
public class ShapeFactory {
	
	/**
	 * 
	 */
	Point defaultCenter;
	/**
	 * 
	 */
	int defaultLength;
	
	/**
	 * @param defaultCenter
	 * @param defaultLength
	 */
	public ShapeFactory(Point defaultCenter, int defaultLength)
	{
		this.defaultCenter = defaultCenter;
		this.defaultLength = defaultLength;
	}

	/**
	 * @param shape
	 * @return
	 */
	public Shape getShape(ShapeType shape)
	{
		Color c=new Color(1f,0f,0f,0f );
		
		MyColor outlineColor = new MyColor();
		outlineColor.getColor().add(Color.black);
		MyColor fillColor = new MyColor();
		fillColor.getColor().add(c);
		switch (shape)
		{
		
		case RECTANGLE :
			return new Rectangle((Point)defaultCenter.clone(), outlineColor, fillColor, false, defaultLength, defaultLength * 2, 0, 2);
		case CIRCLE :
			return new Circle((Point)defaultCenter.clone(), outlineColor, fillColor, false, defaultLength, 2);
		case SQUARE :
			return new Rectangle((Point)defaultCenter.clone(), outlineColor, fillColor, false, defaultLength, defaultLength, 0, 2);
		case TRIANGLE :
			return new Triangle((Point)defaultCenter.clone(), outlineColor, fillColor, false, 2 * (int)(defaultLength / (Math.sqrt(3))), 2 * defaultLength, 0, 2);
		default :
			return null;
		}
	}
}
