package ch.hsr.uint1.whitespace.library.client.swing.gui;

import java.awt.event.WindowListener;

import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hsr.uint1.whitespace.library.client.swing.gui.views.GadgetMaster;

@Component
public class SwingApplication {

	@Autowired
	private GadgetMaster masterFrame;

	public void startApp() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				masterFrame.showGUI();
			}
		});
	}

	public void addCloseApplicationListener(WindowListener windowListener) {
		masterFrame.addWindowListener(windowListener);
	}

}
