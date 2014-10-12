package ch.hsr.uint1.whitespace.app;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import ch.hsr.uint1.whitespace.bl.Library;
import ch.hsr.uint1.whitespace.dl.LibraryData;
import ch.hsr.uint1.whitespace.dl.LocalLibrary;
import ch.hsr.uint1.whitespace.views.GadgetMaster;

public class SwingApplication {

	private Library library;
	private LibraryData libraryData;
	private GadgetMaster masterFrame;

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		SwingApplication app = new SwingApplication();
		app.showMasterGUI();
	}

	public SwingApplication() {
		initializeData();
	}

	private void initializeData() {
		this.libraryData = new LocalLibrary();
		this.library = new Library(libraryData);
	}

	public void showMasterGUI() {
		this.masterFrame = new GadgetMaster(library);
		this.masterFrame.setVisible(true);
	}

}
