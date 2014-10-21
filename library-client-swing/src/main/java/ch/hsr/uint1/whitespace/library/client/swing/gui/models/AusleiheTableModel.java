package ch.hsr.uint1.whitespace.library.client.swing.gui.models;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.uint1.whitespace.library.client.swing.data.MessageData;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Customer;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Loan;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Reservation;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.MessageResolver;

public class AusleiheTableModel extends AbstractTableModel implements Observer {
	private static final long serialVersionUID = -3574669918538671539L;

	private Library library;
	private MessageResolver messageResolver;

	private final String[] columns = { "master.loans.jTable.customerId", "master.loans.jTable.customerName", "master.loans.jTable.customerReservations",
			"master.loans.jTable.customerLoans", "master.loans.jTable.customerHasOverdues" };

	public AusleiheTableModel(Library library, MessageResolver messageResolver) {
		this.library = library;
		this.messageResolver = messageResolver;
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
	public int getRowCount() {
		return library.getCustomers().size();
	}

	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		List<Customer> customers = library.getCustomers();
		Customer localCustomer = customers.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return localCustomer.getStudentNumber();
		case 1:
			return localCustomer.getName();
		case 2:
			return printReservations(library.getReservatonFor(localCustomer, true));
		case 3:
			return printLoans(library.getLoansFor(localCustomer, true));
		case 4: // TODO This is crap, will be removed as soon as possible
			return printIsOverdued(library.hasOverdue(localCustomer));
		default:
			return null;
		}
	}

	private String printIsOverdued(boolean hasOverdue) {
		return hasOverdue ? "true" : "false";
	}

	// TODO return the loan name, and not the id
	private String printLoans(List<Loan> loansFor) {
		String loansAsString = "";
		Iterator<Loan> loansIterator = loansFor.iterator();
		while (loansIterator.hasNext()) {
			Loan current = loansIterator.next();
			if (loansIterator.hasNext()) {
				loansAsString = current.getLoanId() + ",";
			}
			loansAsString = current.getGadgetId();
		}
		return loansAsString;
	}

	private String printReservations(List<Reservation> reservatonFor) {
		String reservations = "";
		Iterator<Reservation> reservationsIterator = reservatonFor.iterator();
		while (reservationsIterator.hasNext()) {
			Reservation current = reservationsIterator.next();
			if (reservationsIterator.hasNext()) {
				reservations = current.getGadgetId() + ",";
			}
			reservations = current.getGadgetId();
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
}
