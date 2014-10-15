package ch.hsr.uint1.whitespace.library.client.swing.app;

import javax.swing.UIManager;

import ch.hsr.uint1.whitespace.library.client.swing.bl.Library;
import ch.hsr.uint1.whitespace.library.client.swing.dl.LibraryData;
import ch.hsr.uint1.whitespace.library.client.swing.views.GadgetMaster;

public class SwingApplication {

    private final Library library;
    private GadgetMaster masterFrame;

    public SwingApplication(final LibraryData libraryData) {
        library = new Library(libraryData);
    }

    public void showMasterGUI() {

        try {
            // Set System L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (final Exception e) {
            e.printStackTrace();
        }

        masterFrame = new GadgetMaster(library);
        masterFrame.setVisible(true);
    }

}
