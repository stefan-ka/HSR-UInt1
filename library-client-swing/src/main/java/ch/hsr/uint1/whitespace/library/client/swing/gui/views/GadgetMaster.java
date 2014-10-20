package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.gui.models.GadgetListModel;

@Component
public class GadgetMaster extends JFrame {

	private static final long serialVersionUID = -2060969695124152513L;

	private JPanel biblioContentPane;
	private JTextField suchenTxtEdit;
	private JTabbedPane biblioTabbedPane;
	private JPanel gadgetTab;
	private JPanel ausleihenTab;
	private JButton gadgetErfassenBtn;
	private JButton gadgetEditBtn;
	private JList<Gadget> gadgetsList;

	@Autowired
	private Library library;

	public void startGUI() {
		setName("Gadget Bibliothek");
		setMinimumSize(new Dimension(500, 230));
		setSize(new Dimension(730, 515));
		setTitle("Gadget Bibliothek");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 513);
		biblioContentPane = new JPanel();
		biblioContentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(biblioContentPane);
		final GridBagLayout gbl_biblioContentPane = new GridBagLayout();
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

		gadgetTab = new JPanel();
		gadgetTab.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		biblioTabbedPane.addTab("Gadgets", null, gadgetTab, "Gadgets Liste");
		biblioTabbedPane.setMnemonicAt(0, KeyEvent.VK_G);
		final GridBagLayout gbl_gadgetTab = new GridBagLayout();
		gbl_gadgetTab.columnWidths = new int[] { 0, 0, 0 };
		gbl_gadgetTab.rowHeights = new int[] { 0, 0, 0 };
		gbl_gadgetTab.columnWeights = new double[] { 1.0, 1.0, 0.0 };
		gbl_gadgetTab.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gadgetTab.setLayout(gbl_gadgetTab);

		ausleihenTab = new JPanel();
		ausleihenTab.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		biblioTabbedPane.addTab("Ausleihen & Rückgabe", null, ausleihenTab, "Hier können Sie Ausleihen und Rückgaben erfassen");
		biblioTabbedPane.setMnemonicAt(1, KeyEvent.VK_A);

		suchenTxtEdit = new JTextField();
		suchenTxtEdit.setToolTipText("Suchen");
		suchenTxtEdit.setText("Suchen...");
		suchenTxtEdit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(final FocusEvent e) {
				suchenTxtEdit.setText("");
			}
		});
		final GridBagConstraints gbc_suchenTxtEdit = new GridBagConstraints();
		gbc_suchenTxtEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_suchenTxtEdit.gridwidth = 13;
		gbc_suchenTxtEdit.insets = new Insets(2, 2, 5, 5);
		gbc_suchenTxtEdit.gridx = 0;
		gbc_suchenTxtEdit.gridy = 0;
		gadgetTab.add(suchenTxtEdit, gbc_suchenTxtEdit);
		suchenTxtEdit.setColumns(10);

		gadgetErfassenBtn = new JButton("Gadget erfassen");
		gadgetErfassenBtn.setToolTipText("Clicken Sie hier, um einen Gadget zu erfassen");
		gadgetErfassenBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editGadget(new Gadget(""), true);
			}
		});
		final GridBagConstraints gbc_gadgetErfassenBtn = new GridBagConstraints();
		gbc_gadgetErfassenBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_gadgetErfassenBtn.insets = new Insets(2, 0, 5, 0);
		gbc_gadgetErfassenBtn.gridx = 13;
		gbc_gadgetErfassenBtn.gridy = 0;
		gadgetTab.add(gadgetErfassenBtn, gbc_gadgetErfassenBtn);

		gadgetEditBtn = new JButton("Gadget editieren");
		gadgetEditBtn.setToolTipText("Clicken Sie hier, um einen Gadget zu editieren");
		gadgetEditBtn.setMinimumSize(new Dimension(145, 29));
		gadgetEditBtn.setMaximumSize(new Dimension(145, 29));
		gadgetEditBtn.setName("Gadget editieren");
		gadgetEditBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gadget gadgetSelected = gadgetsList.getSelectedValue();
				if (gadgetSelected != null) {
					editGadget(gadgetSelected, false);
				}
			}
		});
		final GridBagConstraints gbc_gadgetEditBtn = new GridBagConstraints();
		gbc_gadgetEditBtn.insets = new Insets(2, 0, 5, 0);
		gbc_gadgetEditBtn.fill = GridBagConstraints.BOTH;
		gbc_gadgetEditBtn.gridx = 14;
		gbc_gadgetEditBtn.gridy = 0;
		gadgetTab.add(gadgetEditBtn, gbc_gadgetEditBtn);

		gadgetsList = new JList<Gadget>();
		gadgetsList.setModel(new GadgetListModel(library));
		final GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridwidth = 15;
		gbc_list.insets = new Insets(0, 5, 2, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		gadgetTab.add(gadgetsList, gbc_list);
		this.setVisible(true);
	}

	private void editGadget(Gadget gadget, boolean isNewGadget) {
		GadgetDetail detailFrame = new GadgetDetail(library, gadget, isNewGadget);
		detailFrame.setVisible(true);
	}

}