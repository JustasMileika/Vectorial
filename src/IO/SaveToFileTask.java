package IO;
import java.io.File;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import GUIComponents.Drawing;

/**
 * 
 * @author justa
 * 
 *
 */

/**
 * @author justa
 *
 */
public class SaveToFileTask implements Runnable{

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
	 * @param drawing
	 */
	/**
	 * @param file
	 * @param drawing
	 */
	public SaveToFileTask(File file, Drawing drawing)
	{
		this.file = file;
		this.drawing = drawing;
	}
	/**
	 *
	 */
	@Override
	public void run() {

		try {
			FileOutputStream out = new FileOutputStream(file);
			ObjectOutputStream s =
			new ObjectOutputStream(out);
			s.writeObject(drawing);
			s.flush();
			s.close();
			out.flush();
			out.close();
		}
		catch (Exception e)
		{
			System.out.println("Could not write to file");
		}
	
	}

}
