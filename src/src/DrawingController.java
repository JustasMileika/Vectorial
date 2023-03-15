package src;

import java.awt.Point;

import java.io.File;

import Actions.*;
import ENUMS.ShapeType;
import Exceptions.*;
import GUIComponents.Drawing;
import IO.SaveToFileTask;
import Shapes.Shape;
import Shapes.ShapeFactory;


/**
 * @author justa
 *
 */
public class DrawingController {

	/**
	 * 
	 */
	private Drawing drawing;
	/**
	 * 
	 */
	RestoreController restoreController;
	/**
	 * 
	 */
	private Selection selection;
	/**
	 * 
	 */
	ShapeFactory shapeFactory;

	/**
	 * @param drawing
	 */
	public DrawingController(Drawing drawing) {
		this.drawing = drawing;
		this.restoreController = new RestoreController();
		this.selection = new Selection();
		this.shapeFactory = new ShapeFactory(new Point(100, 100), 50);
	}
	
	/**
	 * @param width
	 */
	public void changeOutline(int width)
	{
		for(Shape shape : selection.getShapes())
		{
			shape.setOutlineWidth(width);
		}
		drawing.repaint();
	}
	
	/**
	 * 
	 */
	public void selectAll()
	{
		for(Shape shape : drawing.getShapes())
		{
			shape.setIsSelected(true);
		}
	}
	
	/**
	 * @param drawing
	 */
	public void setDrawing(Drawing drawing) {
		this.drawing = drawing;
	}

	/**
	 * @param file
	 */
	public void save(File file)
	{
		Thread write = new Thread(new SaveToFileTask(file, drawing));
		write.start();

		//ioController.saveDrawing(file, drawing);
	}
	
	/**
	 * 
	 */
	public void load()
	{
		drawing.clear();
		
		
	}
	
	/**
	 * 
	 */
	public void lowerShapes()
	{
		
		Action action = new LowerShape(drawing, selection);
		action.perform();
		
		restoreController.addAction(action);
		drawing.repaint();
	}
	
	/**
	 * 
	 */
	public void raiseShapes()
	{
		
		Action action = new RaiseShape(drawing, selection);
		action.perform();
		
		restoreController.addAction(action);
		drawing.repaint();
	}
	
	/**
	 * 
	 */
	public void copyShapes()
	{
		
		Action action = new CopyingShape(drawing, selection);
		action.perform();
		
		restoreController.addAction(action);
		drawing.repaint();
	}
	
	/**
	 * 
	 */
	public void groupShapes()
	{
		Action action = new GroupingShapes(drawing, selection);
		action.perform();
		selection.getShapes().clear();
		restoreController.addAction(action);
		drawing.repaint();
	}
	
	/**
	 * @param shape
	 */
	public void selectShape(Shape shape)
	{
		shape.setIsSelected(true);
		selection.add(shape);
	}
	
	/**
	 * 
	 */
	public void rotateShape()
	{
		Action action = new RotatingAction(drawing, selection);
		action.perform();
		
		restoreController.addAction(action);
		drawing.repaint();
	}
	
	/**
	 * @param percentage
	 */
	public void contractShape(int percentage)
	{
		Action action = new ContractingShape(drawing, selection, percentage);
		action.perform();
		
		restoreController.addAction(action);
		drawing.repaint();
	}
	
	/**
	 * @param percentage
	 */
	public void expandShape(int percentage)
	{
		Action action = new ExpandingShape(drawing, selection, percentage);
		action.perform();
		
		restoreController.addAction(action);
		drawing.repaint();
	}

	/**
	 * @param shape
	 */
	public void addShape(ShapeType shape) {
		
		Action action = new ShapeAddition(drawing, shapeFactory.getShape(shape));
		
		action.perform();
		
		restoreController.addAction(action);
		drawing.repaint();
	}

	/**
	 * @param c
	 */
	public void colorSelectedShapes(MyColor c) {
		
		Action action = new ColoringShape(drawing, selection, c);
		action.perform();
		restoreController.addAction(action);
		drawing.repaint();
	}
	
	/**
	 * @param c
	 */
	public void fillSelectedShapes(MyColor c) {
		
		Action action = new FillingShape(drawing, selection, c);
		action.perform();
		restoreController.addAction(action);
		drawing.repaint();
	}

	/**
	 * 
	 */
	public void deleteSelectedShapes() {
		Action action = new ShapeDeletion(drawing, selection);
		action.perform();
		restoreController.addAction(action);
		drawing.repaint();
	}

	/**
	 * @return
	 */
	public Drawing getDrawing() {
		return drawing;
	}

	/**
	 * @return
	 */
	public Selection getSelection() {
		return selection;
	}

	/**
	 * @param x
	 * @param y
	 */
	public void moveSelectedShapes(int x, int y) {
		
		Action action = new ShapeMoving(drawing, selection, x, y);
		//action.perform();
		restoreController.addAction(action);
		drawing.repaint();
	}

	/**
	 * 
	 */
	public void redo() {
		
		try {
			this.restoreController.redo();
		} catch (RestoreException e) {
			System.out.println(e.getMessage());
		}
		drawing.repaint();
	}



	/**
	 * 
	 */
	public void undo() {
		try {
			this.restoreController.undo();
		} catch (UndoStackEmptyException e) {
			System.out.println(e.getMessage());
		} catch (RestoreException e) {
			System.out.println(e.getMessage());
		}
		
		drawing.repaint();
	}
}
