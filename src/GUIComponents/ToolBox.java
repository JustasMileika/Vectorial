package GUIComponents;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import ENUMS.ShapeType;
import IO.ReadFromFileTask;
import IO.SaveToFileTask;
import src.DrawingController;
import src.MyColor;

/**
 * @author justa
 *
 */
public class ToolBox {
	
	/**
	 * 
	 */
	MyFrame frame;
	/**
	 * 
	 */
	DrawingController drawingController;
	
	/**
	 * 
	 */
	JComboBox<String> outlineColorBox;
	/**
	 * 
	 */
	JComboBox<String> fillColorBox;
	
	/**
	 * 
	 */
	protected JButton circleButton;
	/**
	 * 
	 */
	protected JButton rectangleButton;
	/**
	 * 
	 */
	protected JButton triangleButton;
	
	/**
	 * 
	 */
	protected JButton deleteButton;
	/**
	 * 
	 */
	protected JButton rotateButton;
	
	/**
	 * 
	 */
	protected JButton expandButton;
	/**
	 * 
	 */
	protected JButton contractButton;
	
	/**
	 * 
	 */
	protected JButton groupButton;
	
	/**
	 * 
	 */
	protected JButton copyButton;
	
	/**
	 * 
	 */
	protected JMenuBar menuBar;
	
	
	
	
	/**
	 * 
	 */
	final String[] outlineColors = {"CHANGE OUTLINE COLOR", "WHITE", "BLACK", "RED", "GREEN", "BLUE", "YELLOW", "TRANSPERENT"};
	/**
	 * 
	 */
	final String[] fillColors = {"CHANGE FILL COLOR", "WHITE", "BLACK", "RED", "GREEN", "BLUE", "YELLOW", "TRANSPERENT"};
	
	/**
	 * @param frame
	 * @param drawingController
	 */
	public ToolBox(MyFrame frame, DrawingController drawingController)
	{
		
		this.frame = frame;
		this.drawingController = drawingController;
		
		JLabel sliderLabel = new JLabel();
		sliderLabel.setText("Outline width: " + 0);
		
		
		
		JSlider slider = new JSlider(0, 20, 1);
		slider.setPaintLabels(true);
		slider.setPaintTrack(true);
		slider.setPaintTicks(true);
		
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		frame.getContentPane().add(slider);
        
		slider.setBounds(20, 490, 200, 60);
		
		frame.getContentPane().add(sliderLabel);
		sliderLabel.setBounds(75, 450, 200, 50);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
            	drawingController.changeOutline(slider.getValue());
                sliderLabel.setText("Outline width: " + slider.getValue());
            }
        });
        slider.setValue(1);
        
        Color transperent = new Color(1f,0f,0f,0f );
		
		outlineColorBox = new JComboBox<String>(outlineColors);
		outlineColorBox.setBounds(30, 650, 180, 34);
		outlineColorBox.setSelectedIndex(0);
		 
		outlineColorBox.addActionListener(new ActionListener() {
			
			String name;
			
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(name);
				Color picked = null;
				
				String chosenColor = outlineColorBox.getSelectedItem().toString();
				
				if(chosenColor.equals("RED"))
				{
					picked = Color.red;
				}
				else if(chosenColor.equals("BLACK"))
				{
					picked = Color.black;
				}
				else if(chosenColor.equals("YELLOW"))
				{
					picked = Color.yellow;
				}
				else if(chosenColor.equals("BLUE"))
				{
					picked = Color.blue;
				}
				else if(chosenColor.equals("GREEN"))
				{
					picked = Color.green;
				}
				else if(chosenColor.equals("WHITE"))
				{
					picked = Color.white;
				}
				else if(chosenColor.equals("TRANSPERENT"))
				{
					picked = transperent;
				}
				else
				{
					return;
				}
				
				MyColor color = new MyColor();
				color.getColor().add(picked);
				
				drawingController.colorSelectedShapes(color);
			}
		});
		
		
		
		 frame.getContentPane().add(outlineColorBox);
		 
		 fillColorBox = new JComboBox<String>(fillColors);
		 fillColorBox.setBounds(30, 700, 180, 34);
		 fillColorBox.setSelectedIndex(0);
			 
		 fillColorBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color picked = null;
					
					String chosenColor = fillColorBox.getSelectedItem().toString();
					
					if(chosenColor.equals("RED"))
					{
						picked = Color.red;
					}
					else if(chosenColor.equals("BLACK"))
					{
						picked = Color.black;
					}
					else if(chosenColor.equals("YELLOW"))
					{
						picked = Color.yellow;
					}
					else if(chosenColor.equals("BLUE"))
					{
						picked = Color.blue;
					}
					else if(chosenColor.equals("GREEN"))
					{
						picked = Color.green;
					}
					else if(chosenColor.equals("WHITE"))
					{
						picked = Color.white;
					}
					else if(chosenColor.equals("TRANSPERENT"))
					{
						picked = transperent;
					}
					else
					{
						return;
					}
					
					MyColor color = new MyColor();
					color.getColor().add(picked);
					
					drawingController.fillSelectedShapes(color);

				}
			});
			
			
			
		 frame.getContentPane().add(fillColorBox);
		
		this.menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu layerMenu = new JMenu("Layers");
		
		
		JMenuItem bringInFront = new JMenuItem("Bring in front");
		JMenuItem bringToBack = new JMenuItem("Bring to back");
		
		
		JMenuItem open = new JMenuItem("Open");
		JMenuItem save = new JMenuItem("Save");
		
		JMenuItem redo = new JMenuItem("Redo");
		JMenuItem undo = new JMenuItem("Undo");
		
		bringInFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				drawingController.raiseShapes();
			}
		});
		
		bringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				drawingController.lowerShapes();
			}
		});
		
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				JFileChooser fileDialog = new JFileChooser();
				fileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				//fileDialog.setSelectedFile(new File("new.vctr"));
				FileFilter filter = new FileNameExtensionFilter("Vector Graphic files", "vctr");
				fileDialog.addChoosableFileFilter(filter);
				fileDialog.setFileFilter(filter);

				fileDialog.showOpenDialog(null);

				File file = fileDialog.getSelectedFile();
				
				if(file != null)
				{
					ReadFromFileTask read = new ReadFromFileTask(file, drawingController);
					
					Thread reading = new Thread(read);
					reading.start();
				}
			}
		});
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				JFileChooser fileDialog = new JFileChooser();
				fileDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
				
				fileDialog.setSelectedFile(new File("new.vctr"));
				FileFilter filter = new FileNameExtensionFilter("Vector Graphic files",
						"vctr");
				fileDialog.addChoosableFileFilter(filter);
				fileDialog.setFileFilter(filter);

				fileDialog.showSaveDialog(null);

				File file = fileDialog.getSelectedFile();
				
				if(file != null)
				{
					Thread writing = new Thread(new SaveToFileTask(file, drawingController.getDrawing()));
					writing.start();
				}
			}
		});
		
		redo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				drawingController.redo();
			}
		});
		
		undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev)
			{
				drawingController.undo();
			}
		});
		
		
		layerMenu.add(bringInFront);
		layerMenu.add(bringToBack);

		fileMenu.add(open);
		fileMenu.add(save);
		
		editMenu.add(redo);
		editMenu.add(undo);
		
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(layerMenu);
		
		frame.setJMenuBar(menuBar);
			 
		this.copyButton = new JButton("Copy");
		this.groupButton = new JButton("Group");
		this.circleButton = new JButton("Circle");
		this.rectangleButton = new JButton("Rectangle");
		this.triangleButton = new JButton("Triagle");
		this.deleteButton = new JButton("Delete");
		this.rotateButton = new JButton("Rotate");
		this.expandButton = new JButton("Expand");
		this.contractButton = new JButton("Contract");

		this.circleButton.setBounds(45, 50, 150, 40);
		this.rectangleButton.setBounds(45, 100, 150, 40);
		this.triangleButton.setBounds(45, 150, 150, 40);
		
		this.groupButton.setBounds(45, 200, 150, 40);
		
		
		
		this.expandButton.setBounds(45, 300, 150, 40);
		this.contractButton.setBounds(45, 350, 150, 40);
		
		
		this.deleteButton.setBounds(45, 600, 150, 40);
		
		
		this.rotateButton.setBounds(45, 400, 150, 40);
		
		this.copyButton.setBounds(45, 550, 150, 40);
		

		frame.getContentPane().add(this.copyButton);
		frame.getContentPane().add(this.groupButton);
		frame.getContentPane().add(this.circleButton);
		frame.getContentPane().add(this.rectangleButton);
		frame.getContentPane().add(this.triangleButton);
		frame.getContentPane().add(this.deleteButton);

		frame.getContentPane().add(rotateButton);
		frame.getContentPane().add(expandButton);
		frame.getContentPane().add(contractButton);
		
		this.copyButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				drawingController.copyShapes();
				
			}
		});
		
		this.groupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				drawingController.groupShapes();
				
			}
		});
		
		this.contractButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				drawingController.contractShape(10);
				
			}
		});
		
		this.expandButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				drawingController.expandShape(10);
				
				/*Action action = new ExpandingShape(frame.panel, frame.panel.selection, 10);
				frame.panel.restoreController.addAction(action);
				action.perform();
				frame.panel.repaint();*/
			}
		});
		
		this.rotateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				drawingController.rotateShape();
				
				/*Action action = new RotatingAction(frame.panel, frame.panel.selection);
				frame.panel.restoreController.addAction(action);
				action.perform();
				frame.panel.repaint();*/
			}
		});

		this.circleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				drawingController.addShape(ShapeType.CIRCLE);
			}
		});

		this.rectangleButton.addActionListener(new ActionListener() {    
			public void actionPerformed (ActionEvent e) { 
				
				drawingController.addShape(ShapeType.RECTANGLE);
			}    
		});
		
		this.triangleButton.addActionListener(new ActionListener() {    
			public void actionPerformed (ActionEvent e) { 
				
				drawingController.addShape(ShapeType.TRIANGLE);
			}    
		});
		
		


		this.deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) { 
				
				drawingController.deleteSelectedShapes();
			}
		});
		
	}
}
