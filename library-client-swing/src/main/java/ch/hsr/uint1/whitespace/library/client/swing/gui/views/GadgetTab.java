package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.ApplicationMessages;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.LocaleChangedListener;
import ch.hsr.uint1.whitespace.library.client.swing.gui.models.GadgetsMasterTableModel;

@Component
@Lazy
@Scope("prototype")
public class GadgetTab extends JPanel implements LocaleChangedListener {

	private static final long serialVersionUID = -2216366094301767935L;

	@Autowired
	private Library library;

	@Autowired
	private SpringObjectFactory prototypeFactory;

	private JTextField suchenTxtEditGadgetTab;
	private TableRowSorter<TableModel> gadgetsSorter;
	private JButton gadgetErfassenBtn;
	private JButton gadgetEditBtn;
	private JScrollPane gadgtesTableScrollPane;
	private JTable gadgetsMasterTable;
	private GadgetsMasterTableModel gadgetsMasterTableModel;

	public GadgetTab() {
		buildGUI();
	}

	private void buildGUI() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagLayout gbl_gadgetTab = new GridBagLayout();
		gbl_gadgetTab.columnWidths = new int[] { 0 };
		gbl_gadgetTab.rowHeights = new int[] { 0, 0, 0 };
		gbl_gadgetTab.columnWeights = new double[] { 1.0 };
		gbl_gadgetTab.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gbl_gadgetTab);

		GridBagConstraints gbc_suchenTxtEditGadgetTab = new GridBagConstraints();
		gbc_suchenTxtEditGadgetTab.fill = GridBagConstraints.HORIZONTAL;
		gbc_suchenTxtEditGadgetTab.insets = new Insets(2, 2, 5, 5);
		gbc_suchenTxtEditGadgetTab.gridx = 0;
		gbc_suchenTxtEditGadgetTab.gridy = 0;

		suchenTxtEditGadgetTab = new JTextField();
		suchenTxtEditGadgetTab.setToolTipText(ApplicationMessages.getText("search.tooltip"));
		suchenTxtEditGadgetTab.setText(ApplicationMessages.getText("search.fieldText"));
		suchenTxtEditGadgetTab.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(final FocusEvent e) {
				if (ApplicationMessages.getText("search.fieldText").equals(suchenTxtEditGadgetTab.getText())) {
					suchenTxtEditGadgetTab.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if ("".equals(suchenTxtEditGadgetTab.getText())) {
					suchenTxtEditGadgetTab.setText(ApplicationMessages.getText("search.fieldText"));
				}
			}
		});
		suchenTxtEditGadgetTab.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				gadgetsSorter.setRowFilter(RowFilter.regexFilter("(?i)" + suchenTxtEditGadgetTab.getText(), 0, 1, 2, 3, 4, 5, 6));
				super.keyReleased(e);
			}
		});

		add(suchenTxtEditGadgetTab, gbc_suchenTxtEditGadgetTab);
		suchenTxtEditGadgetTab.setColumns(10);

		gadgetErfassenBtn = new JButton(ApplicationMessages.getText("master.gadgets.addGadgetButton"));
		gadgetErfassenBtn.setToolTipText(ApplicationMessages.getText("master.gadgets.addGadgetButton.tooltip"));
		gadgetErfassenBtn.addActionListener(e -> editGadget(new Gadget(""), true));
		GridBagConstraints gbc_gadgetErfassenBtn = new GridBagConstraints();
		gbc_gadgetErfassenBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_gadgetErfassenBtn.insets = new Insets(2, 0, 5, 5);
		gbc_gadgetErfassenBtn.gridx = 1;
		gbc_gadgetErfassenBtn.gridy = 0;
		add(gadgetErfassenBtn, gbc_gadgetErfassenBtn);

		gadgetEditBtn = new JButton(ApplicationMessages.getText("master.gadgets.editGadgetButton"));
		gadgetEditBtn.setToolTipText(ApplicationMessages.getText("master.gadgets.editGadgetButton.tooltip"));
		gadgetEditBtn.setMinimumSize(new Dimension(145, 29));
		gadgetEditBtn.setMaximumSize(new Dimension(145, 29));
		gadgetEditBtn.setEnabled(false);
		gadgetEditBtn.addActionListener(e -> {
			Gadget gadgetSelected = gadgetsMasterTableModel.getGadgetAt(gadgetsMasterTable.convertRowIndexToModel(gadgetsMasterTable.getSelectedRow()));
			if (gadgetSelected != null) {
				editGadget(gadgetSelected, false);
			}
		});
		GridBagConstraints gbc_gadgetEditBtn = new GridBagConstraints();
		gbc_gadgetEditBtn.insets = new Insets(2, 0, 5, 0);
		gbc_gadgetEditBtn.fill = GridBagConstraints.BOTH;
		gbc_gadgetEditBtn.gridx = 2;
		gbc_gadgetEditBtn.gridy = 0;
		add(gadgetEditBtn, gbc_gadgetEditBtn);

		gadgtesTableScrollPane = new JScrollPane();
		GridBagConstraints gbc_gadgtesTableScrollPane = new GridBagConstraints();
		gbc_gadgtesTableScrollPane.fill = GridBagConstraints.BOTH;
		gbc_gadgtesTableScrollPane.gridwidth = 3;
		gbc_gadgtesTableScrollPane.insets = new Insets(0, 5, 0, 3);
		gbc_gadgtesTableScrollPane.gridx = 0;
		gbc_gadgtesTableScrollPane.gridy = 1;
		add(gadgtesTableScrollPane, gbc_gadgtesTableScrollPane);

		gadgetsMasterTable = new JTable();
		gadgetsMasterTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		gadgtesTableScrollPane.setViewportView(gadgetsMasterTable);
		ListSelectionModel listSelectionModel = gadgetsMasterTable.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent listSelectionEvent) {
				gadgetEditBtn.setEnabled(gadgetsMasterTable.getSelectedRow() >= 0);
			}
		});
	}

	@PostConstruct
	private void initialize() {
		gadgetsMasterTableModel = new GadgetsMasterTableModel(library);
		gadgetsMasterTable.setModel(gadgetsMasterTableModel);
		gadgetsMasterTable.setAutoCreateRowSorter(true);
		gadgetsSorter = new TableRowSorter<TableModel>(gadgetsMasterTableModel);
		gadgetsMasterTable.setRowSorter(gadgetsSorter);
	}

	private void editGadget(Gadget gadget, boolean isNewGadget) {
		GadgetDetail detailFrame = (GadgetDetail) SpringObjectFactory.createObject(GadgetDetail.class, prototypeFactory);
		ApplicationMessages.addLocaleChangedListener(detailFrame);
		detailFrame.startGUI(gadget, isNewGadget);
	}

	@Override
	public void localeChanged() {
		suchenTxtEditGadgetTab.setToolTipText(ApplicationMessages.getText("search.tooltip"));
		suchenTxtEditGadgetTab.setText(ApplicationMessages.getText("search.fieldText"));
		gadgetErfassenBtn.setText(ApplicationMessages.getText("master.gadgets.addGadgetButton"));
		gadgetErfassenBtn.setToolTipText(ApplicationMessages.getText("master.gadgets.addGadgetButton.tooltip"));
		gadgetEditBtn.setText(ApplicationMessages.getText("master.gadgets.editGadgetButton"));
		gadgetEditBtn.setToolTipText(ApplicationMessages.getText("master.gadgets.editGadgetButton.tooltip"));
	}

}
