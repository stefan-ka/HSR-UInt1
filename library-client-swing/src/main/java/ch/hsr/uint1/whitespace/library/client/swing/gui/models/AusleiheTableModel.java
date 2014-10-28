package ch.hsr.uint1.whitespace.library.client.swing.gui.models;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.uint1.whitespace.library.client.swing.data.MessageData;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Customer;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Loan;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Reservation;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.MessageResolver;

public class AusleiheTableModel extends AbstractTableModel implements Observer {
	private static final long serialVersionUID = -3574669918538671539L;

	private Library library;
	private MessageResolver messageResolver;
	private List<Customer> customers;

	private final String[] columns = { "master.loans.jTable.customerId", "master.loans.jTable.customerName", "master.loans.jTable.customerReservations",
			"master.loans.jTable.customerLoans", "master.loans.jTable.customerHasOverdues" };

	private final Class<?>[] columnClasses = new Class<?>[] { String.class, String.class, String.class, String.class, Boolean.class };

	public AusleiheTableModel(Library library, MessageResolver messageResolver) {
		this.library = library;
		this.messageResolver = messageResolver;
		customers = library.getCustomers();
		library.addObserver(this);
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return messageResolver.getText(columns[columnIndex]);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClasses[columnIndex];
	}

	@Override
	public int getRowCount() {
		return library.getCustomers().size();
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
			return printReservedGadgets(library.getReservatonFor(localCustomer, true));
		case 3:
			return printLoanedGadgets(library.getLoansFor(localCustomer, true));
		case 4:
			return library.hasOverdue(localCustomer);
		default:
			return null;
		}
	}

	private String printLoanedGadgets(List<Loan> loansFor) {
		String loanedGadgetsNames = "";
		Iterator<Loan> loansIterator = loansFor.iterator();
		while (loansIterator.hasNext()) {
			Loan currentLoan = loansIterator.next();
			Gadget gadget = library.getGadget(currentLoan.getGadgetId());
			if (loansIterator.hasNext()) {
				loanedGadgetsNames = gadget.getName() + ",";
			}
			loanedGadgetsNames = gadget.getName();
		}
		return loanedGadgetsNames;
	}

	private String printReservedGadgets(List<Reservation> reservatonFor) {
		String reservations = "";
		Iterator<Reservation> reservationsIterator = reservatonFor.iterator();
		while (reservationsIterator.hasNext()) {
			Reservation current = reservationsIterator.next();
			Gadget gadget = library.getGadget(current.getGadgetId());
			if (reservationsIterator.hasNext()) {
				reservations = gadget.getName() + ",";
			}
			reservations = gadget.getName();
		}
		return reservations;
	}

	@Override
	public void update(Observable o, Object arg) {
		MessageData data = (MessageData) arg;
		if (data.getData() instanceof Customer) {
			Customer customer = (Customer) data.getData();
			int pos = library.getCustomers().indexOf(customer);
			fireTableRowsUpdated(pos, pos);
		}
	}

	public Customer getCustomerAt(int index) {
		return customers.get(index);
	}
}
