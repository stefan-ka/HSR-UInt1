package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.annotation.PostConstruct;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.hsr.uint1.whitespace.library.client.swing.domain.Customer;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Loan;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Reservation;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.ApplicationMessages;
import ch.hsr.uint1.whitespace.library.client.swing.gui.models.CustomerMasterTableModel;
import ch.hsr.uint1.whitespace.library.client.swing.gui.models.KundeAusleiheTableModel;
import ch.hsr.uint1.whitespace.library.client.swing.gui.models.ReservationenTableModel;

@Component
@Lazy
@Scope("prototype")
public class AusleihenTab extends JPanel {

	private static final long serialVersionUID = -438582919563580310L;

	private JTextField suchenTxtEditAusleiheTab;
	private JPanel kundePanelInAusleiheTab;
	private JPanel kundePanelBorder;
	private JLabel lblReservationen;
	private JLabel lblNeueReservation;
	private JButton btnReservation;
	private JButton btnAusleihen;
	private JScrollPane kundeReservationScrollPane;
	private JScrollPane ausleiheScrollPane;
	private JScrollPane kundeAusleiheScrollPane;
	private JTable ausleiheTable;
	private JTable kundeAusleiheTable;
	private JTable reservationenTable;
	private JLabel lblKeineReservationMglich;
	private JLabel lblAusleihen;
	private JTextField idLoanTxtField;
	private JLabel lblKeineAusleiheMglich;
	private JLabel lbREservationId;
	private JLabel lblAusleiheId;
	private JTextField idReservationTxtField;
	private CustomerMasterTableModel ausleiheTableModel;
	private ReservationenTableModel reservationenTableModel;
	private KundeAusleiheTableModel kundeAusleiheModel;
	private TableRowSorter<TableModel> customerSorter;

	private Customer selectedCustomer;

	@Autowired
	private Library library;

	@Autowired
	private SpringObjectFactory prototypeFactory;

	public AusleihenTab() {
		buildGUI();
	}

	private void buildGUI() {
		setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		GridBagLayout gbl_ausleihenTab = new GridBagLayout();
		gbl_ausleihenTab.columnWidths = new int[] { 456, 355, 0 };
		gbl_ausleihenTab.rowHeights = new int[] { 0, 0, 0 };
		gbl_ausleihenTab.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_ausleihenTab.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		setLayout(gbl_ausleihenTab);

		suchenTxtEditAusleiheTab = new JTextField();
		suchenTxtEditAusleiheTab.setToolTipText(ApplicationMessages.getText("search.tooltip"));
		suchenTxtEditAusleiheTab.setText(ApplicationMessages.getText("search.fieldText"));
		suchenTxtEditAusleiheTab.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(final FocusEvent e) {
				if (ApplicationMessages.getText("search.fieldText").equals(suchenTxtEditAusleiheTab.getText())) {
					suchenTxtEditAusleiheTab.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if ("".equals(suchenTxtEditAusleiheTab.getText())) {
					suchenTxtEditAusleiheTab.setText(ApplicationMessages.getText("search.fieldText"));
				}
			}
		});
		suchenTxtEditAusleiheTab.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				customerSorter.setRowFilter(RowFilter.regexFilter("(?i)" + suchenTxtEditAusleiheTab.getText(), 0, 1, 2, 3));
				super.keyReleased(e);
			}
		});

		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(2, 2, 5, 3);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		add(suchenTxtEditAusleiheTab, gbc_textField);
		suchenTxtEditAusleiheTab.setColumns(10);

		kundePanelBorder = new JPanel();
		kundePanelBorder.setName("");
		kundePanelBorder.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), ApplicationMessages.getText("master.loans.customerData"),
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagConstraints gbc_kundePanelBorder = new GridBagConstraints();
		gbc_kundePanelBorder.gridheight = 2;
		gbc_kundePanelBorder.fill = GridBagConstraints.BOTH;
		gbc_kundePanelBorder.gridx = 1;
		gbc_kundePanelBorder.gridy = 0;
		add(kundePanelBorder, gbc_kundePanelBorder);
		GridBagLayout gbl_kundePanelBorder = new GridBagLayout();
		gbl_kundePanelBorder.columnWidths = new int[] { 355, 0 };
		gbl_kundePanelBorder.rowHeights = new int[] { 0, 0 };
		gbl_kundePanelBorder.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_kundePanelBorder.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		kundePanelBorder.setLayout(gbl_kundePanelBorder);

		kundePanelInAusleiheTab = new JPanel();
		GridBagConstraints gbc_kundePanelInAusleiheTab = new GridBagConstraints();
		gbc_kundePanelInAusleiheTab.fill = GridBagConstraints.BOTH;
		gbc_kundePanelInAusleiheTab.gridx = 0;
		gbc_kundePanelInAusleiheTab.gridy = 0;
		kundePanelBorder.add(kundePanelInAusleiheTab, gbc_kundePanelInAusleiheTab);
		GridBagLayout gbl_kundePanelInAusleiheTab = new GridBagLayout();
		gbl_kundePanelInAusleiheTab.columnWidths = new int[] { 60, 60, 124, 46, 69, 0 };
		gbl_kundePanelInAusleiheTab.rowHeights = new int[] { 0, 128, 0, 0, 0, 0, 122, 0, 0, 0 };
		gbl_kundePanelInAusleiheTab.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_kundePanelInAusleiheTab.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		kundePanelInAusleiheTab.setLayout(gbl_kundePanelInAusleiheTab);

		lblReservationen = new JLabel(ApplicationMessages.getText("master.loans.reservations"));
		GridBagConstraints gbc_lblReservationen = new GridBagConstraints();
		gbc_lblReservationen.gridwidth = 2;
		gbc_lblReservationen.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblReservationen.insets = new Insets(0, 3, 1, 5);
		gbc_lblReservationen.gridx = 0;
		gbc_lblReservationen.gridy = 0;
		kundePanelInAusleiheTab.add(lblReservationen, gbc_lblReservationen);

		kundeReservationScrollPane = new JScrollPane();
		GridBagConstraints gbc_kundeReservationScrollPane = new GridBagConstraints();
		gbc_kundeReservationScrollPane.fill = GridBagConstraints.BOTH;
		gbc_kundeReservationScrollPane.gridwidth = 5;
		gbc_kundeReservationScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_kundeReservationScrollPane.gridx = 0;
		gbc_kundeReservationScrollPane.gridy = 1;
		kundePanelInAusleiheTab.add(kundeReservationScrollPane, gbc_kundeReservationScrollPane);

		reservationenTable = new JTable();
		reservationenTable.setFillsViewportHeight(true);
		kundeReservationScrollPane.setViewportView(reservationenTable);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 3;

		lblNeueReservation = new JLabel(ApplicationMessages.getText("master.loans.reservations.newReservationLabel"));
		GridBagConstraints gbc_lblNeueReservation = new GridBagConstraints();
		gbc_lblNeueReservation.gridwidth = 2;
		gbc_lblNeueReservation.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNeueReservation.insets = new Insets(2, 3, 5, 5);
		gbc_lblNeueReservation.gridx = 0;
		gbc_lblNeueReservation.gridy = 2;
		kundePanelInAusleiheTab.add(lblNeueReservation, gbc_lblNeueReservation);

		lbREservationId = new JLabel(ApplicationMessages.getText("master.loans.reservations.idLabel"));
		GridBagConstraints gbc_lbREservationId = new GridBagConstraints();
		gbc_lbREservationId.insets = new Insets(0, 0, 5, 5);
		gbc_lbREservationId.anchor = GridBagConstraints.EAST;
		gbc_lbREservationId.gridx = 0;
		gbc_lbREservationId.gridy = 3;
		kundePanelInAusleiheTab.add(lbREservationId, gbc_lbREservationId);

		idReservationTxtField = new JTextField();
		GridBagConstraints gbc_idReservationTxtField = new GridBagConstraints();
		gbc_idReservationTxtField.gridwidth = 3;
		gbc_idReservationTxtField.insets = new Insets(0, 0, 5, 5);
		gbc_idReservationTxtField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idReservationTxtField.gridx = 1;
		gbc_idReservationTxtField.gridy = 3;
		kundePanelInAusleiheTab.add(idReservationTxtField, gbc_idReservationTxtField);
		idReservationTxtField.setColumns(10);

		btnReservation = new JButton(ApplicationMessages.getText("master.loans.reservations.reservationButton"));
		GridBagConstraints gbc_btnReservation = new GridBagConstraints();
		gbc_btnReservation.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnReservation.insets = new Insets(0, 0, 5, 0);
		gbc_btnReservation.gridx = 4;
		gbc_btnReservation.gridy = 3;
		kundePanelInAusleiheTab.add(btnReservation, gbc_btnReservation);
		btnReservation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				doReservation(idReservationTxtField.getText());
				idReservationTxtField.setText("");
			}
		});

		lblKeineReservationMglich = new JLabel(ApplicationMessages.getText("master.loans.reservations.NoReservationPossible"));
		GridBagConstraints gbc_lblKeineReservationMglich = new GridBagConstraints();
		gbc_lblKeineReservationMglich.gridwidth = 3;
		gbc_lblKeineReservationMglich.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblKeineReservationMglich.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeineReservationMglich.gridx = 1;
		gbc_lblKeineReservationMglich.gridy = 4;
		kundePanelInAusleiheTab.add(lblKeineReservationMglich, gbc_lblKeineReservationMglich);

		lblAusleihen = new JLabel(ApplicationMessages.getText("master.loans.loansLabel"));
		GridBagConstraints gbc_lblAusleihen = new GridBagConstraints();
		gbc_lblAusleihen.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAusleihen.gridwidth = 4;
		gbc_lblAusleihen.insets = new Insets(20, 3, 5, 5);
		gbc_lblAusleihen.gridx = 0;
		gbc_lblAusleihen.gridy = 5;
		kundePanelInAusleiheTab.add(lblAusleihen, gbc_lblAusleihen);

		kundeAusleiheScrollPane = new JScrollPane();
		GridBagConstraints gbc_kundeAusleiheScrollPane = new GridBagConstraints();
		gbc_kundeAusleiheScrollPane.fill = GridBagConstraints.BOTH;
		gbc_kundeAusleiheScrollPane.gridwidth = 5;
		gbc_kundeAusleiheScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_kundeAusleiheScrollPane.gridx = 0;
		gbc_kundeAusleiheScrollPane.gridy = 6;
		kundePanelInAusleiheTab.add(kundeAusleiheScrollPane, gbc_kundeAusleiheScrollPane);

		kundeAusleiheTable = new JTable();
		kundeAusleiheTable.setFillsViewportHeight(true);
		kundeAusleiheScrollPane.setViewportView(kundeAusleiheTable);

		lblAusleiheId = new JLabel(ApplicationMessages.getText("master.loans.idLabel"));
		GridBagConstraints gbc_lblAusleiheId = new GridBagConstraints();
		gbc_lblAusleiheId.insets = new Insets(0, 0, 5, 5);
		gbc_lblAusleiheId.anchor = GridBagConstraints.EAST;
		gbc_lblAusleiheId.gridx = 0;
		gbc_lblAusleiheId.gridy = 7;
		kundePanelInAusleiheTab.add(lblAusleiheId, gbc_lblAusleiheId);

		idLoanTxtField = new JTextField();
		GridBagConstraints gbc_textField1 = new GridBagConstraints();
		gbc_textField1.gridwidth = 3;
		gbc_textField1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField1.insets = new Insets(0, 0, 5, 5);
		gbc_textField1.gridx = 1;
		gbc_textField1.gridy = 7;
		kundePanelInAusleiheTab.add(idLoanTxtField, gbc_textField1);
		idLoanTxtField.setColumns(10);

		btnAusleihen = new JButton(ApplicationMessages.getText("master.loans.loanButton"));
		btnAusleihen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				doAusleihe(idLoanTxtField.getText());
				idLoanTxtField.setText("");
			}
		});
		GridBagConstraints gbc_btnAusleihen = new GridBagConstraints();
		gbc_btnAusleihen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAusleihen.insets = new Insets(0, 0, 5, 0);
		gbc_btnAusleihen.gridx = 4;
		gbc_btnAusleihen.gridy = 7;
		kundePanelInAusleiheTab.add(btnAusleihen, gbc_btnAusleihen);

		lblKeineAusleiheMglich = new JLabel(ApplicationMessages.getText("master.loans.noLoanPossible"));
		GridBagConstraints gbc_lblKeineAusleiheMglich = new GridBagConstraints();
		gbc_lblKeineAusleiheMglich.gridwidth = 3;
		gbc_lblKeineAusleiheMglich.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblKeineAusleiheMglich.insets = new Insets(0, 0, 0, 5);
		gbc_lblKeineAusleiheMglich.gridx = 1;
		gbc_lblKeineAusleiheMglich.gridy = 8;
		kundePanelInAusleiheTab.add(lblKeineAusleiheMglich, gbc_lblKeineAusleiheMglich);

		ausleiheScrollPane = new JScrollPane();
		GridBagConstraints gbc_ausleiheScrollPane = new GridBagConstraints();
		gbc_ausleiheScrollPane.fill = GridBagConstraints.BOTH;
		gbc_ausleiheScrollPane.insets = new Insets(0, 5, 0, 3);
		gbc_ausleiheScrollPane.gridx = 0;
		gbc_ausleiheScrollPane.gridy = 1;
		add(ausleiheScrollPane, gbc_ausleiheScrollPane);

		ausleiheTable = new JTable();
		ausleiheTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ausleiheScrollPane.setViewportView(ausleiheTable);
		ausleiheTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedCustomer = ausleiheTableModel.getCustomerAt(ausleiheTable.convertRowIndexToModel(ausleiheTable.getSelectedRow()));
				upateSelectedCustomer(selectedCustomer);
			}
		});
	}

	@SuppressWarnings("serial")
	@PostConstruct
	private void initialize() {
		ausleiheTableModel = new CustomerMasterTableModel(library);
		ausleiheTable.setModel(ausleiheTableModel);
		reservationenTableModel = new ReservationenTableModel(library);
		reservationenTable.setModel(reservationenTableModel);
		kundeAusleiheModel = new KundeAusleiheTableModel(library);
		kundeAusleiheTable.setModel(kundeAusleiheModel);
		ausleiheTable.setAutoCreateRowSorter(true);
		customerSorter = new TableRowSorter<TableModel>(ausleiheTableModel);
		ausleiheTable.setRowSorter(customerSorter);

		upateSelectedCustomer(null);

		new ButtonColumn(reservationenTable, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Ausleihen Button pressed ...");
			}
		}, 2);
		new ButtonColumn(reservationenTable, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Reservation reservationSelected = reservationenTableModel.getReservationAt(reservationenTable.convertRowIndexToModel(reservationenTable.getSelectedRow()));
				deleteReservation(reservationSelected);
			}
		}, 3);
		new ButtonColumn(kundeAusleiheTable, new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Loan loan = kundeAusleiheModel.getLoanAt(kundeAusleiheTable.convertRowIndexToModel(kundeAusleiheTable.getSelectedRow()));
				doRueckgabe(loan);
			}
		}, 5);
		reservationenTable.setAutoCreateColumnsFromModel(false);
		kundeAusleiheTable.setAutoCreateColumnsFromModel(false);
	}

	private void upateSelectedCustomer(Customer customer) {
		reservationenTableModel.setSelectedCustomer(customer);
		kundeAusleiheModel.setSelectedCustomer(customer);
		initializeComponents(customer);
	}

	private void initializeComponents(Customer customer) {
		if (customer == null) {
			reservationenTable.setEnabled(false);
			kundeAusleiheTable.setEnabled(false);
			lblKeineReservationMglich.setVisible(false);
			lblKeineAusleiheMglich.setVisible(false);
			idReservationTxtField.setEnabled(false);
			idLoanTxtField.setEnabled(false);
			btnReservation.setEnabled(false);
			btnAusleihen.setEnabled(false);
		} else {
			reservationenTable.setEnabled(true);
			kundeAusleiheTable.setEnabled(true);
			if (!library.hasOverdue(customer)) {
				idReservationTxtField.setEnabled(true);
				idLoanTxtField.setEnabled(true);
				btnReservation.setEnabled(true);
				btnAusleihen.setEnabled(true);
			} else {
				lblKeineReservationMglich.setVisible(true);
				lblKeineAusleiheMglich.setVisible(true);
			}
		}
	}

	private void doReservation(String gagdetId) {
		Gadget gadget = library.getGadget(gagdetId);
		if (gadget == null) {
			// TODO: show that gadget does not exist ...
		}
		if (!library.canReservation(gadget, selectedCustomer)) {
			// TODO: show that gadget cannot be reserved
		}
		library.addReservation(gadget, selectedCustomer);
	}

	private void doAusleihe(String gadgetId) {
		Gadget gadget = library.getGadget(gadgetId);
		if (gadget == null) {
			// TODO: show that gadget does not exist ...
		}
		if (!library.canLent(gadget, selectedCustomer)) {
			// TODO: show that customer cannot make this Ausleihe
		}
		library.addLoan(gadget, selectedCustomer);
	}

	private void deleteReservation(Reservation reservation) {
		reservation.setFinished(true);
		library.updateReservation(reservation);
	}

	private void doRueckgabe(Loan loan) {
		loan.returnCopy();
		library.updateLoan(loan);
	}
}
