package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.MessageResolver;
import ch.hsr.uint1.whitespace.library.client.swing.gui.models.AusleiheTableModel;
import ch.hsr.uint1.whitespace.library.client.swing.gui.models.GadgetsMasterTableModel;

@Component
public class GadgetMaster extends JFrame {

	private static final long serialVersionUID = -2060969695124152513L;

	private JPanel biblioContentPane;
	private JTextField suchenTxtEditGadgetTab;
	private JTabbedPane biblioTabbedPane;
	private JPanel gadgetTab;
	private JPanel ausleihenTab;
	private JButton gadgetErfassenBtn;
	private JButton gadgetEditBtn;
	private JTextField suchenTxtEditAusleiheTab;
	private JPanel kundePanelInAusleiheTab;
	private JPanel kundePanelBorder;
	private JLabel lblReservationen;
	private JLabel lblNeueReservation;
	private JButton btnReservation;
	private JScrollPane kundeReservationScrollPane;
	private JScrollPane gadgtesTableScrollPane;
	private JScrollPane ausleiheScrollPane;
	private JScrollPane kundeAusleiheScrollPane;
	private JTable ausleiheTable;
	private JTable kundeAusleiheTable;
	private JTable reservationenTable;
	private JTable gadgetsMasterTable;
	private JLabel lblKeineReservationMglich;
	private JLabel lblAusleihen;
	private JTextField textField;
	private JButton btnAusleihen;
	private JLabel lblKeineAusleiheMglich;
	private JLabel lbREservationId;
	private JLabel lblAusleiheId;
	private JTextField idReservationTxtField;
	private MessageResolver messageResolver;
	private Library library;
	private GadgetsMasterTableModel gadgetsMasterTableModel;
	TableRowSorter<TableModel> gadgetsSorter;

	@Autowired
	private ObjectFactory<GadgetDetail> gadgetDetailViewFactory;

	@Autowired
	public GadgetMaster(Library library, MessageResolver messageResolver) {
		this.library = library;
		this.messageResolver = messageResolver;
		initializeGUI();
	}

	private void initializeGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setName(messageResolver.getText("master.title"));
		setSize(new Dimension(972, 577));
		setTitle(messageResolver.getText("master.title"));
		setBounds(100, 100, 972, 577);
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
		biblioTabbedPane.addTab(messageResolver.getText("master.tab.gadgets"), null, gadgetTab, messageResolver.getText("master.tab.gadgets.tip"));
		biblioTabbedPane.setMnemonicAt(0, KeyEvent.VK_G);
		final GridBagLayout gbl_gadgetTab = new GridBagLayout();
		gbl_gadgetTab.columnWidths = new int[] { 0 };
		gbl_gadgetTab.rowHeights = new int[] { 0, 0, 0 };
		gbl_gadgetTab.columnWeights = new double[] { 1.0 };
		gbl_gadgetTab.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gadgetTab.setLayout(gbl_gadgetTab);

		ausleihenTab = new JPanel();
		ausleihenTab.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		biblioTabbedPane.addTab(messageResolver.getText("master.tab.loans"), null, ausleihenTab, messageResolver.getText("master.tab.loans.tip"));
		final GridBagLayout gbl_ausleihenTab = new GridBagLayout();
		gbl_ausleihenTab.columnWidths = new int[] { 456, 355, 0 };
		gbl_ausleihenTab.rowHeights = new int[] { 0, 0, 0 };
		gbl_ausleihenTab.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_ausleihenTab.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		ausleihenTab.setLayout(gbl_ausleihenTab);

		suchenTxtEditAusleiheTab = new JTextField();
		suchenTxtEditAusleiheTab.setToolTipText(messageResolver.getText("search.tooltip"));
		suchenTxtEditAusleiheTab.setText(messageResolver.getText("search.fieldText"));
		suchenTxtEditAusleiheTab.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(final FocusEvent e) {
				suchenTxtEditGadgetTab.setText("");
			}
		});

		final GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(2, 2, 5, 3);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		ausleihenTab.add(suchenTxtEditAusleiheTab, gbc_textField);
		suchenTxtEditAusleiheTab.setColumns(10);

		kundePanelBorder = new JPanel();
		kundePanelBorder.setName("");
		kundePanelBorder.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), messageResolver.getText("master.loans.customerData"), TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		final GridBagConstraints gbc_kundePanelBorder = new GridBagConstraints();
		gbc_kundePanelBorder.gridheight = 2;
		gbc_kundePanelBorder.fill = GridBagConstraints.BOTH;
		gbc_kundePanelBorder.gridx = 1;
		gbc_kundePanelBorder.gridy = 0;
		ausleihenTab.add(kundePanelBorder, gbc_kundePanelBorder);
		final GridBagLayout gbl_kundePanelBorder = new GridBagLayout();
		gbl_kundePanelBorder.columnWidths = new int[] { 355, 0 };
		gbl_kundePanelBorder.rowHeights = new int[] { 0, 0 };
		gbl_kundePanelBorder.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_kundePanelBorder.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		kundePanelBorder.setLayout(gbl_kundePanelBorder);

		kundePanelInAusleiheTab = new JPanel();
		final GridBagConstraints gbc_kundePanelInAusleiheTab = new GridBagConstraints();
		gbc_kundePanelInAusleiheTab.fill = GridBagConstraints.BOTH;
		gbc_kundePanelInAusleiheTab.gridx = 0;
		gbc_kundePanelInAusleiheTab.gridy = 0;
		kundePanelBorder.add(kundePanelInAusleiheTab, gbc_kundePanelInAusleiheTab);
		final GridBagLayout gbl_kundePanelInAusleiheTab = new GridBagLayout();
		gbl_kundePanelInAusleiheTab.columnWidths = new int[] { 60, 60, 124, 46, 69, 0 };
		gbl_kundePanelInAusleiheTab.rowHeights = new int[] { 0, 128, 0, 0, 0, 0, 122, 0, 0, 0 };
		gbl_kundePanelInAusleiheTab.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_kundePanelInAusleiheTab.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		kundePanelInAusleiheTab.setLayout(gbl_kundePanelInAusleiheTab);

		lblReservationen = new JLabel(messageResolver.getText("master.loans.reservations"));
		final GridBagConstraints gbc_lblReservationen = new GridBagConstraints();
		gbc_lblReservationen.gridwidth = 2;
		gbc_lblReservationen.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblReservationen.insets = new Insets(0, 3, 1, 5);
		gbc_lblReservationen.gridx = 0;
		gbc_lblReservationen.gridy = 0;
		kundePanelInAusleiheTab.add(lblReservationen, gbc_lblReservationen);

		kundeReservationScrollPane = new JScrollPane();
		final GridBagConstraints gbc_kundeReservationScrollPane = new GridBagConstraints();
		gbc_kundeReservationScrollPane.fill = GridBagConstraints.BOTH;
		gbc_kundeReservationScrollPane.gridwidth = 5;
		gbc_kundeReservationScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_kundeReservationScrollPane.gridx = 0;
		gbc_kundeReservationScrollPane.gridy = 1;
		kundePanelInAusleiheTab.add(kundeReservationScrollPane, gbc_kundeReservationScrollPane);

		reservationenTable = new JTable();
		kundeReservationScrollPane.setViewportView(reservationenTable);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;

		lblNeueReservation = new JLabel(messageResolver.getText("master.loans.reservations.newReservationLabel"));
		final GridBagConstraints gbc_lblNeueReservation = new GridBagConstraints();
		gbc_lblNeueReservation.gridwidth = 2;
		gbc_lblNeueReservation.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNeueReservation.insets = new Insets(2, 3, 5, 5);
		gbc_lblNeueReservation.gridx = 0;
		gbc_lblNeueReservation.gridy = 2;
		kundePanelInAusleiheTab.add(lblNeueReservation, gbc_lblNeueReservation);

		lbREservationId = new JLabel(messageResolver.getText("master.loans.reservations.idLabel"));
		final GridBagConstraints gbc_lbREservationId = new GridBagConstraints();
		gbc_lbREservationId.insets = new Insets(0, 0, 5, 5);
		gbc_lbREservationId.anchor = GridBagConstraints.EAST;
		gbc_lbREservationId.gridx = 0;
		gbc_lbREservationId.gridy = 3;
		kundePanelInAusleiheTab.add(lbREservationId, gbc_lbREservationId);

		idReservationTxtField = new JTextField();
		final GridBagConstraints gbc_idReservationTxtField = new GridBagConstraints();
		gbc_idReservationTxtField.gridwidth = 3;
		gbc_idReservationTxtField.insets = new Insets(0, 0, 5, 5);
		gbc_idReservationTxtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idReservationTxtField.gridx = 1;
		gbc_idReservationTxtField.gridy = 3;
		kundePanelInAusleiheTab.add(idReservationTxtField, gbc_idReservationTxtField);
		idReservationTxtField.setColumns(10);

		btnReservation = new JButton(messageResolver.getText("master.loans.reservations.reservationButton"));
		final GridBagConstraints gbc_btnReservation = new GridBagConstraints();
		gbc_btnReservation.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReservation.insets = new Insets(0, 0, 5, 0);
		gbc_btnReservation.gridx = 4;
		gbc_btnReservation.gridy = 3;
		kundePanelInAusleiheTab.add(btnReservation, gbc_btnReservation);

		lblKeineReservationMglich = new JLabel(messageResolver.getText("master.loans.reservations.NoReservationPossible"));
		final GridBagConstraints gbc_lblKeineReservationMglich = new GridBagConstraints();
		gbc_lblKeineReservationMglich.gridwidth = 3;
		gbc_lblKeineReservationMglich.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblKeineReservationMglich.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeineReservationMglich.gridx = 1;
		gbc_lblKeineReservationMglich.gridy = 4;
		kundePanelInAusleiheTab.add(lblKeineReservationMglich, gbc_lblKeineReservationMglich);

		lblAusleihen = new JLabel(messageResolver.getText("master.loans.loansLabel"));
		final GridBagConstraints gbc_lblAusleihen = new GridBagConstraints();
		gbc_lblAusleihen.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAusleihen.gridwidth = 4;
		gbc_lblAusleihen.insets = new Insets(20, 3, 5, 5);
		gbc_lblAusleihen.gridx = 0;
		gbc_lblAusleihen.gridy = 5;
		kundePanelInAusleiheTab.add(lblAusleihen, gbc_lblAusleihen);

		kundeAusleiheScrollPane = new JScrollPane();
		final GridBagConstraints gbc_kundeAusleiheScrollPane = new GridBagConstraints();
		gbc_kundeAusleiheScrollPane.fill = GridBagConstraints.BOTH;
		gbc_kundeAusleiheScrollPane.gridwidth = 5;
		gbc_kundeAusleiheScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_kundeAusleiheScrollPane.gridx = 0;
		gbc_kundeAusleiheScrollPane.gridy = 6;
		kundePanelInAusleiheTab.add(kundeAusleiheScrollPane, gbc_kundeAusleiheScrollPane);

		kundeAusleiheTable = new JTable();
		kundeAusleiheScrollPane.setViewportView(kundeAusleiheTable);

		lblAusleiheId = new JLabel(messageResolver.getText("master.loans.idLabel"));
		final GridBagConstraints gbc_lblAusleiheId = new GridBagConstraints();
		gbc_lblAusleiheId.insets = new Insets(0, 0, 5, 5);
		gbc_lblAusleiheId.anchor = GridBagConstraints.EAST;
		gbc_lblAusleiheId.gridx = 0;
		gbc_lblAusleiheId.gridy = 7;
		kundePanelInAusleiheTab.add(lblAusleiheId, gbc_lblAusleiheId);

		textField = new JTextField();
		final GridBagConstraints gbc_textField1 = new GridBagConstraints();
		gbc_textField1.gridwidth = 3;
		gbc_textField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField1.insets = new Insets(0, 0, 5, 5);
		gbc_textField1.gridx = 1;
		gbc_textField1.gridy = 7;
		kundePanelInAusleiheTab.add(textField, gbc_textField1);
		textField.setColumns(10);

		btnAusleihen = new JButton(messageResolver.getText("master.loans.loanButton"));
		btnAusleihen.setPreferredSize(new Dimension(117, 29));
		btnAusleihen.setMinimumSize(new Dimension(117, 29));
		btnAusleihen.setMaximumSize(new Dimension(117, 29));
		final GridBagConstraints gbc_btnAusleihen = new GridBagConstraints();
		gbc_btnAusleihen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAusleihen.insets = new Insets(0, 0, 5, 0);
		gbc_btnAusleihen.gridx = 4;
		gbc_btnAusleihen.gridy = 7;
		kundePanelInAusleiheTab.add(btnAusleihen, gbc_btnAusleihen);

		lblKeineAusleiheMglich = new JLabel(messageResolver.getText("master.loans.noLoanPossible"));
		final GridBagConstraints gbc_lblKeineAusleiheMglich = new GridBagConstraints();
		gbc_lblKeineAusleiheMglich.gridwidth = 3;
		gbc_lblKeineAusleiheMglich.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblKeineAusleiheMglich.insets = new Insets(0, 0, 0, 5);
		gbc_lblKeineAusleiheMglich.gridx = 1;
		gbc_lblKeineAusleiheMglich.gridy = 8;
		kundePanelInAusleiheTab.add(lblKeineAusleiheMglich, gbc_lblKeineAusleiheMglich);

		ausleiheScrollPane = new JScrollPane();
		final GridBagConstraints gbc_ausleiheScrollPane = new GridBagConstraints();
		gbc_ausleiheScrollPane.fill = GridBagConstraints.BOTH;
		gbc_ausleiheScrollPane.insets = new Insets(0, 5, 0, 3);
		gbc_ausleiheScrollPane.gridx = 0;
		gbc_ausleiheScrollPane.gridy = 1;
		ausleihenTab.add(ausleiheScrollPane, gbc_ausleiheScrollPane);

		ausleiheTable = new JTable();
		ausleiheScrollPane.setViewportView(ausleiheTable);
		ausleiheTable.setModel(new AusleiheTableModel(library, messageResolver));

		suchenTxtEditGadgetTab = new JTextField();
		suchenTxtEditGadgetTab.setToolTipText(messageResolver.getText("search.tooltip"));
		suchenTxtEditGadgetTab.setText(messageResolver.getText("search.fieldText"));
		suchenTxtEditGadgetTab.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(final FocusEvent e) {
				suchenTxtEditGadgetTab.setText("");
			}
		});
		suchenTxtEditGadgetTab.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				gadgetsSorter.setRowFilter(RowFilter.regexFilter("(?i)" + suchenTxtEditGadgetTab.getText(), 0, 1, 2, 3, 4, 5, 6));
				super.keyReleased(e);
			}
		});

		GridBagConstraints gbc_suchenTxtEditGadgetTab = new GridBagConstraints();
		gbc_suchenTxtEditGadgetTab.fill = GridBagConstraints.HORIZONTAL;
		gbc_suchenTxtEditGadgetTab.insets = new Insets(2, 2, 5, 5);
		gbc_suchenTxtEditGadgetTab.gridx = 0;
		gbc_suchenTxtEditGadgetTab.gridy = 0;
		gadgetTab.add(suchenTxtEditGadgetTab, gbc_suchenTxtEditGadgetTab);
		suchenTxtEditGadgetTab.setColumns(10);

		gadgetErfassenBtn = new JButton(messageResolver.getText("master.gadgets.addGadgetButton"));
		gadgetErfassenBtn.setToolTipText(messageResolver.getText("master.gadgets.addGadgetButton.tooltip"));
		gadgetErfassenBtn.addActionListener(e -> editGadget(new Gadget(""), true));
		final GridBagConstraints gbc_gadgetErfassenBtn = new GridBagConstraints();
		gbc_gadgetErfassenBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_gadgetErfassenBtn.insets = new Insets(2, 0, 5, 5);
		gbc_gadgetErfassenBtn.gridx = 1;
		gbc_gadgetErfassenBtn.gridy = 0;
		gadgetTab.add(gadgetErfassenBtn, gbc_gadgetErfassenBtn);

		gadgetEditBtn = new JButton(messageResolver.getText("master.gadgets.editGadgetButton"));
		gadgetEditBtn.setToolTipText(messageResolver.getText("master.gadgets.editGadgetButton.tooltip"));
		gadgetEditBtn.setMinimumSize(new Dimension(145, 29));
		gadgetEditBtn.setMaximumSize(new Dimension(145, 29));
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
		gadgetTab.add(gadgetEditBtn, gbc_gadgetEditBtn);

		gadgtesTableScrollPane = new JScrollPane();
		GridBagConstraints gbc_gadgtesTableScrollPane = new GridBagConstraints();
		gbc_gadgtesTableScrollPane.fill = GridBagConstraints.BOTH;
		gbc_gadgtesTableScrollPane.gridwidth = 3;
		gbc_gadgtesTableScrollPane.insets = new Insets(0, 5, 0, 3);
		gbc_gadgtesTableScrollPane.gridx = 0;
		gbc_gadgtesTableScrollPane.gridy = 1;
		gadgetTab.add(gadgtesTableScrollPane, gbc_gadgtesTableScrollPane);
		gadgetsMasterTableModel = new GadgetsMasterTableModel(library, messageResolver);
		gadgetsMasterTable = new JTable(gadgetsMasterTableModel);
		gadgtesTableScrollPane.setViewportView(gadgetsMasterTable);
		gadgetsMasterTable.setAutoCreateRowSorter(true);
		gadgetsSorter = new TableRowSorter<TableModel>(gadgetsMasterTableModel);
		gadgetsMasterTable.setRowSorter(gadgetsSorter);
	}

	public void showGUI() {
		this.setVisible(true);
	}

	private void editGadget(Gadget gadget, boolean isNewGadget) {
		GadgetDetail detailFrame = gadgetDetailViewFactory.getObject();
		detailFrame.startGUI(gadget, isNewGadget);
	}

	private boolean isInteger(String text) {
		try {
			Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
