package ch.hsr.uint1.whitespace.library.client.swing.gui;

import javax.swing.UIManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hsr.uint1.whitespace.library.client.swing.gui.views.GadgetMaster;

@Component
public class SwingApplication {

	@Autowired
	private GadgetMaster masterFrame;
	
	public void startApp() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		masterFrame.startGUI();
	}

}
