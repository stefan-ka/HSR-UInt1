package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.ApplicationMessages;

@Component
public class GadgetMaster extends JFrame {

	private static final long serialVersionUID = -2060969695124152513L;

	private JPanel biblioContentPane;
	private JTabbedPane biblioTabbedPane;
	private GadgetTab gadgetTab;
	private AusleihenTab ausleihenTab;

	@Autowired
	private Library library;
	private SpringObjectFactory prototypeFactory;

	@Autowired
	public GadgetMaster(SpringObjectFactory prototypeFactory) {
		this.prototypeFactory = prototypeFactory;
		buildGUI();
	}

	private void buildGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setName(ApplicationMessages.getText("master.title"));
		setSize(new Dimension(972, 577));
		setTitle(ApplicationMessages.getText("master.title"));
		setBounds(100, 100, 972, 577);
		biblioContentPane = new JPanel();
		biblioContentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(biblioContentPane);
		GridBagLayout gbl_biblioContentPane = new GridBagLayout();
		gbl_biblioContentPane.columnWidths = new int[] { 0, 0 };
		gbl_biblioContentPane.rowHeights = new int[] { 0, 0 };
		gbl_biblioContentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_biblioContentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		biblioContentPane.setLayout(gbl_biblioContentPane);

		biblioTabbedPane = new JTabbedPane(SwingConstants.TOP);
		biblioTabbedPane.setBorder(null);
		biblioTabbedPane.setPreferredSize(new Dimension(0, 0));
		final GridBagConstraints gbc_biblioTabbedPane = new GridBagConstraints();
		gbc_biblioTabbedPane.fill = GridBagConstraints.BOTH;
		gbc_biblioTabbedPane.gridx = 0;
		gbc_biblioTabbedPane.gridy = 0;
		biblioContentPane.add(biblioTabbedPane, gbc_biblioTabbedPane);

		gadgetTab = (GadgetTab) SpringObjectFactory.createObject(GadgetTab.class, prototypeFactory);
		ausleihenTab = (AusleihenTab) SpringObjectFactory.createObject(AusleihenTab.class, prototypeFactory);

		biblioTabbedPane.addTab(ApplicationMessages.getText("master.tab.gadgets"), null, gadgetTab, ApplicationMessages.getText("master.tab.gadgets.tip"));
		biblioTabbedPane.addTab(ApplicationMessages.getText("master.tab.loans"), null, ausleihenTab, ApplicationMessages.getText("master.tab.loans.tip"));
		biblioTabbedPane.setMnemonicAt(0, KeyEvent.VK_G);
		biblioTabbedPane.setMnemonicAt(1, KeyEvent.VK_A);
	}

	public void showGUI() {
		this.setVisible(true);
	}

}
