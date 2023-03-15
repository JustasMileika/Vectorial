package src;

import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import Shapes.*;


/**
 * @author justa
 *
 */
public class MouseHandler extends MouseAdapter{
	
	/**
	 * 
	 */
	DrawingController drawingController;
	
	/**
	 * 
	 */
	Point startPoint;
	/**
	 * 
	 */
	boolean moving = false;
	/**
	 * 
	 */
	boolean editing = false;
	/**
	 * 
	 */
	Point mousePos;
	/**
	 * 
	 */
	JLabel position;
	
	/**
	 * @param drawingController
	 * @param position
	 */
	public MouseHandler(DrawingController drawingController, JLabel position)
	{
		this.drawingController = drawingController;
		this.position = position;
	}
	/**
	 *
	 */
	public void mousePressed(MouseEvent event)
	{

		
		
		startPoint = event.getPoint();
		
			boolean selected = false;
			
			if (((event.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) == 0)) {
				
				boolean includesSelected = false;
				for(Shape shape : drawingController.getSelection().getShapes())
				{
					if(shape.includes(event.getPoint()))
					{
						includesSelected = true;
					}
				}
				
				if(!includesSelected)
				{
					for(Shape shape : drawingController.getSelection().getShapes())
					{
						shape.setIsSelected(false);
					}
					drawingController.getSelection().getShapes().clear();
				}
				else
				{
					return;
				}
			
				Shape minSizeShape = null;
				Integer minSize = null;
				for(int i = drawingController.getDrawing().getShapes().size() - 1; i >= 0; --i)
				{
					Shape shape = drawingController.getDrawing().getShapes().get(i);
					if(shape.includes(event.getPoint()))	
					{
						if(minSize == null || shape.getSize() < minSize)
						{
							minSize = shape.getSize();
							minSizeShape = shape;
						}
						
					}
				}
				if(minSize != null)
				{
					selected = true;
					
					drawingController.selectShape(minSizeShape);
				}
			}
			else
			{
				for(Shape shape : drawingController.getDrawing().getShapes())
				{
					if(shape.includes(event.getPoint()) && !shape.getIsSelected())
					{
						selected = true;
						drawingController.selectShape(shape);
						break;
					}
				}
			}
			
			if(!selected && drawingController.getSelection().getShapes().size() == 0)
			{
				for(Shape shape : drawingController.getDrawing().getShapes())
				{
					shape.setIsSelected(false);
				}
				drawingController.getSelection().getShapes().clear();
				
			}
			
			drawingController.getDrawing().repaint();
		
	}
	
	/**
	 *
	 */
	public void mouseReleased(MouseEvent event)
	{
		Point curr = event.getPoint();
		double diffx = curr.getX() - startPoint.getX();
		double diffy = curr.getY() - startPoint.getY();

		if(moving)
		{
			
			drawingController.moveSelectedShapes((int)diffx, (int)diffy);
			
			moving = false;
		}
	}
	
	/**
	 *
	 */
	public void mouseMoved(MouseEvent m) {
		mousePos = m.getPoint();
		position.setText((int)mousePos.getX() + "px, " + (int)mousePos.getY() + "px");
	}
	
	
	/**
	 *
	 */
	public void mouseDragged(MouseEvent event)
	{
		position.setText((int)mousePos.getX() + "px, " + (int)mousePos.getY() + "px");
		Point delta = new Point(event.getX() - mousePos.x, event.getY() - mousePos.y);
		
		for(Shape s : drawingController.getDrawing().getShapes())
		{
			if(s.getIsSelected())
			{
				s.move(delta.x, delta.y);
				moving = true;
			}
		}
		
		mousePos = event.getPoint();
		position.setText((int)mousePos.getX() + "px, " + (int)mousePos.getY() + "px");
		drawingController.getDrawing().repaint();;
		
	}
}
