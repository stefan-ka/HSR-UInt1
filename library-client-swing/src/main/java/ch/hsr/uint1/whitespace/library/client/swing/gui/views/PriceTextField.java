package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import javax.swing.JTextField;

public class PriceTextField extends JTextField {

	private static final long serialVersionUID = 261016545366967158L;

	public PriceTextField() {
		setKeyListerner();
		setFocusListener();
	}

	private void setFocusListener() {
		addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				try {

					DecimalFormat format = new DecimalFormat("0.000");
					setText(format.format(Double.parseDouble(getText())));
				} catch (NumberFormatException exception) {
					setText("0.000");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void setKeyListerner() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char character = e.getKeyChar();
				if (((character < '0') || (character > '9')) && (character != '\b')) {
					e.consume();
				}
			}
		});
	}
}
