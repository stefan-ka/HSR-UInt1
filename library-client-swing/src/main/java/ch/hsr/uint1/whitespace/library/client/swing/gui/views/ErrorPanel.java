package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import java.awt.Color;
import java.util.List;

import javax.swing.JTextPane;

public class ErrorPanel extends JTextPane {

	private static final long serialVersionUID = 1280529319717719814L;

	public ErrorPanel() {
		super();
		setUp();
	}

	private void setUp() {
		setVisible(false);
		setForeground(Color.BLACK);
		setBackground(new Color(255, 255, 153));
		setEditable(false);
	}

	public void printErrorMessages(List<String> errorMessages) {
		String errorMessage = "";
		for (int i = 0; i < errorMessages.size(); i++) {
			if (i == 0) {
				errorMessage = errorMessages.get(i);
			} else {
				errorMessage = errorMessage + "\n" + errorMessages.get(i);
			}
			setText(errorMessage);
		}
	}
}
