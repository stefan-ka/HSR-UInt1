package ch.hsr.uint1.whitespace.library.client.swing.app;

import ch.hsr.uint1.whitespace.library.client.swing.bl.Library;
import ch.hsr.uint1.whitespace.library.client.swing.dl.LibraryData;
import ch.hsr.uint1.whitespace.library.client.swing.views.GadgetMaster;

public class SwingApplication {

	private Library library;
	private GadgetMaster masterFrame;

	public SwingApplication(LibraryData libraryData) {
		this.library = new Library(libraryData);
	}

	public void showMasterGUI() {
		this.masterFrame = new GadgetMaster(library);
		this.masterFrame.setVisible(true);
	}

}
