package ch.hsr.uint1.whitespace.library.client.swing.app;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import ch.hsr.uint1.whitespace.library.client.swing.dl.ProxyLibrary;

public class SwingApplicationRunner {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		SwingApplication app = new SwingApplication(new ProxyLibrary());
		app.showMasterGUI();
	}

}
