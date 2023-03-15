package GUIComponents;


import java.awt.Color;

import javax.swing.*;
import src.DrawingController;
import src.MouseHandler;

/**
 * @author justa
 *
 */
public class MyFrame extends JFrame{
	
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	protected Drawing drawing;
	/**
	 * 
	 */
	protected ToolBox toolBox;
	/**
	 * 
	 */
	private DrawingController drawingController;
	 
	/**
	 * 
	 */
	public MyFrame()
	{
		super();
		
		JLabel position = new JLabel();
		position.setText(0 + "px, " + 0 + "px");
		
		this.drawing = new Drawing();
		this.drawing.setBackground(Color.WHITE);
	    this.drawing.setBounds(234, 40, 1200, 735);
	    this.drawingController = new DrawingController(drawing);
	    MouseHandler mouseHandler = new MouseHandler(drawingController, position);
	    this.drawing.addMouseListener(mouseHandler);
		this.drawing.addMouseMotionListener(mouseHandler);
	    
	    
		position.setBounds(1300, 10, 100, 20);
	    
		this.getContentPane().add(position);
	    
		
		this.getContentPane().add(drawing);
	    
		
		this.setBounds(100, 100, 1500, 850);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.getContentPane().setLayout(null);
	   
	    
	    this.toolBox = new ToolBox(this, drawingController);
	    
		
	}
}