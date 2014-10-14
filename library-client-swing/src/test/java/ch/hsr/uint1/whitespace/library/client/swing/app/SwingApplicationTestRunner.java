package ch.hsr.uint1.whitespace.library.client.swing.app;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import ch.hsr.uint1.whitespace.library.client.swing.dl.LocalLibrary;

public class SwingApplicationTestRunner {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		SwingApplication app = new SwingApplication(new LocalLibrary());
		app.showMasterGUI();
	}

}
