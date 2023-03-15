package GUIComponents;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import src.DrawingController;

/**
 * @author justa
 *
 */
public class MenuListener implements ActionListener {

	/**
	 * 
	 */
	DrawingController controller;
	/**
	 * 
	 */
	JFileChooser fileDialog;

	/**
	 * @param c
	 */
	public MenuListener(DrawingController c) {
		this.controller = c;
	}

	/**
	 *
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (cmd.equals("Quit")) {
			System.exit(0);
		}

		else if (cmd.equals("Undo")) {
			controller.undo();
		}

		else if (cmd.equals("Redo")) {
			controller.redo();
		}

		else if (cmd.equals("Select all")) {
			controller.selectAll();

		}

		else if (cmd.equals("Delete")) {
			controller.deleteSelectedShapes();
		}

		else {
			JOptionPane.showMessageDialog(null, "Not implemented.",
					"Not implemented", JOptionPane.ERROR_MESSAGE);
		}
	}
}
