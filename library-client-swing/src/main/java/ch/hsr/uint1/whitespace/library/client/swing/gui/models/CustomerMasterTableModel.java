package ch.hsr.uint1.whitespace.library.client.swing.gui.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.uint1.whitespace.library.client.swing.data.MessageData;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Customer;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Loan;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Reservation;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.ApplicationMessages;

public class CustomerMasterTableModel extends AbstractTableModel implements Observer {

	private static final long serialVersionUID = 3833258736173498908L;

	private Library library;
	private List<Customer> customers;

	private static final String[] COLUMNS = { "master.loans.jTable.customerId", "master.loans.jTable.customerName", "master.loans.jTable.customerReservations",
			"master.loans.jTable.customerLoans", "master.loans.jTable.customerHasOverdues" };

	private static final Class<?>[] COLUMN_CLASSES = new Class<?>[] { String.class, String.class, String.class, String.class, Boolean.class };

	public CustomerMasterTableModel(Library library) {
		this.library = library;
		loadCustomers();
		library.addObserver(this);
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return ApplicationMessages.getText(COLUMNS[columnIndex]);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}

	@Override
	public int getRowCount() {
		if (library == null)
			return 0;
		return customers.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Customer localCustomer = customers.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return localCustomer.getStudentNumber();
		case 1:
			return localCustomer.getName();
		case 2:
			return getReservationsString(library.getReservationFor(localCustomer, true));
		case 3:
			return getLoansString(library.getLoansFor(localCustomer, true));
		case 4:
			return library.hasOverdue(localCustomer);
		default:
			return null;
		}
	}

	private String getReservationsString(List<Reservation> reservations) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < reservations.size(); i++) {
			String gadgetId = reservations.get(i).getGadgetId();
			sb.append(library.getGadget(gadgetId));
			if (i < reservations.size() - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	private String getLoansString(List<Loan> loans) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < loans.size(); i++) {
			String gadgetId = loans.get(i).getGadgetId();
			sb.append(library.getGadget(gadgetId));
			if (i < loans.size() - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	@Override
	public void update(Observable o, Object arg) {
		MessageData data = (MessageData) arg;
		Customer customer = null;
		if (data.getData() instanceof Customer) {
			customer = (Customer) data.getData();
		} else if (data.getData() instanceof Reservation) {
			Reservation reservation = (Reservation) data.getData();
			customer = library.getCustomer(reservation.getCustomerId());
		} else if (data.getData() instanceof Loan) {
			Loan loan = (Loan) data.getData();
			customer = library.getCustomer(loan.getCustomerId());
		}
		if (customer != null) {
			int pos = customers.indexOf(customer);
			loadCustomers();
			if (pos > customers.size()) {
				fireTableRowsInserted(pos, pos);
			} else {
				fireTableRowsUpdated(pos, pos);
			}
		}
	}

	public Customer getCustomerAt(int index) {
		if (index >= 0)
			return customers.get(index);
		return null;
	}

	public String[] getColumns() {
		return COLUMNS;
	}

	private void loadCustomers() {
		customers = new ArrayList<Customer>(library.getCustomers());
	}

}
