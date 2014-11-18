package ch.hsr.uint1.whitespace.library.client.swing.gui.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.uint1.whitespace.library.client.swing.data.MessageData;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Customer;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Loan;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.ApplicationMessages;
import ch.hsr.uint1.whitespace.library.client.swing.gui.views.ButtonColumn;

public class KundeAusleiheTableModel extends AbstractTableModel implements Observer {

	private static final long serialVersionUID = -8955101395555275350L;

	private static final String[] COLUMNS = { "reservations.table.gadgetName", "ausleihenTable.ausgeliehenAm", "ausleihenTable.zueruckBis", "ausleihenTable.faellig",
			"ausleihenTable.reserviert", "ausleihenTable.ruecknahme" };

	private Library library;
	private List<Loan> loans;
	private static final Class<?>[] COLUMN_CLASSES = new Class<?>[] { String.class, String.class, String.class, Boolean.class, Boolean.class, ButtonColumn.class };
	private SimpleDateFormat sd;

	private Customer selectedCustomer;

	public KundeAusleiheTableModel(Library library) {
		this.sd = new SimpleDateFormat("dd.MM.YYYY");
		this.library = library;
		loadLoans();
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
	public int getRowCount() {
		if (loans == null)
			return 0;
		return loans.size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Loan loan = loans.get(rowIndex);
		Gadget gadget = library.getGadget(loan.getGadgetId());
		boolean hasReservation = library.getReservationFor(gadget, true).size() > 0;
		switch (columnIndex) {
		case 0:
			return gadget.getName();
		case 1:
			return sd.format(loan.getPickupDate());
		case 2:
			return sd.format(loan.overDueDate());
		case 3:
			return loan.isOverdue();
		case 4:
			return hasReservation;
		case 5:
			return ApplicationMessages.getText("ausleihenTable.rueckgabeButton");
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 5)
			return true;
		return false;
	}

	@Override
	public void update(Observable o, Object arg) {
		loadLoans();
		MessageData data = (MessageData) arg;
		if (data.getData() instanceof Loan) {
			Loan loan = (Loan) data.getData();
			int pos = loans.indexOf(loan);
			fireTableRowsUpdated(pos, pos);
			if (pos < loans.size()) {
				fireTableRowsInserted(pos, pos);
			}
		}
	}

	public void setSelectedCustomer(Customer customer) {
		this.selectedCustomer = customer;
		loadLoans();
		fireTableDataChanged();
	}

	private void loadLoans() {
		if (selectedCustomer != null) {
			loans = library.getLoansFor(selectedCustomer, true);
		} else {
			loans = new ArrayList<>();
		}
	}

	public Loan getLoanAt(int index) {
		return this.loans.get(index);
	}
	
	public String[] getColumns() {
		return COLUMNS;
	}
}
