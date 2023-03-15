package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import GUIComponents.Drawing;
import Shapes.Shape;
import src.DrawingController;

/**
 * @author justa
 *
 */
public class ReadFromFileTask implements Runnable{

	/**
	 * 
	 */
	DrawingController drawingController;
	/**
	 * 
	 */
	File file;
	/**
	 * 
	 */
	Drawing drawing;
	
	/**
	 * @param file
	 * @param drawingController
	 */
	public ReadFromFileTask(File file, DrawingController drawingController)
	{
		this.drawingController = drawingController;
		this.file = file;
	}
	/**
	 *
	 */
	@Override
	public void run() {

		try {
			FileInputStream out = new FileInputStream(file);
			ObjectInputStream s = new ObjectInputStream(out);
			drawing = (Drawing) s.readObject();
			s.close();
			out.close();
		}
		catch (Exception e)
		{
			drawing = null;
			JOptionPane.showMessageDialog(null, "Cannot read from file");
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				if(drawing != null)
				{
					drawingController.getDrawing().getShapes().clear();
					for(Shape s : drawing.getShapes())
					{
						drawingController.getDrawing().addShape(s);
					}
					
					drawingController.getDrawing().repaint();
				}
			}
		});
		
	}
	
	/**
	 * @return
	 */
	public Drawing getDrawing()
	{
		return this.drawing;
	}

}
