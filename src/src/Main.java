package src;

import javax.swing.SwingUtilities;

import GUIComponents.*;

public class Main {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				MyFrame frame = new MyFrame();
				frame.setVisible(true);
			}
		});
	}
}
