package ch.hsr.uint1.whitespace.library.client.swing.gui.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.uint1.whitespace.library.client.swing.data.MessageData;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Customer;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Reservation;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.ApplicationMessages;

public class ReservationenTableModel extends AbstractTableModel implements Observer {

	private static final long serialVersionUID = 8691397583313500085L;

	private static final String[] COLUMNS = { "reservations.table.gadgetName", "reservations.table.waitingList", "reservations.table.darfAusleihen", "reservations.table.delete" };
	private Library library;
	private List<Reservation> reservations = new ArrayList<>();
	private static final Class<?>[] COLUMN_CLASSES = new Class<?>[] { String.class, String.class, String.class, String.class };

	private Customer selectedCustomer;

	public ReservationenTableModel(Library library) {
		this.library = library;
		loadReservations();
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
		if (reservations == null)
			return 0;
		return reservations.size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return COLUMN_CLASSES[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Reservation reservation = reservations.get(rowIndex);
		Gadget gadget = library.getGadget(reservation.getGadgetId());
		switch (columnIndex) {
		case 0:
			return gadget.getName();
		case 1:
			List<Reservation> reservations4Gadget = library.getReservationFor(gadget, true);
			return reservations4Gadget.indexOf(reservation) + 1;
		case 2:
			if (library.canLent(gadget, selectedCustomer))
				return ApplicationMessages.getText("reservations.table.jaButton");
			else
				return ApplicationMessages.getText("reservations.table.neinButton");
		case 3:
			return ApplicationMessages.getText("reservations.table.loeschenButton");
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		Reservation reservation = reservations.get(rowIndex);
		Gadget gadget = library.getGadget(reservation.getGadgetId());
		switch (columnIndex) {
		case 2:
			return library.canLent(gadget, selectedCustomer);
		case 3:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		loadReservations();
		MessageData data = (MessageData) arg;
		if (data.getData() instanceof Reservation) {
			Reservation reservation = (Reservation) data.getData();
			int pos = reservations.indexOf(reservation);
			fireTableRowsUpdated(pos, pos);
			if (pos < reservations.size()) {
				fireTableRowsInserted(pos, pos);
			}
		}
	}

	public void setSelectedCustomer(Customer customer) {
		this.selectedCustomer = customer;
		loadReservations();
		fireTableDataChanged();
	}

	private void loadReservations() {
		if (selectedCustomer != null) {
			reservations = library.getReservationFor(selectedCustomer, true);
		} else {
			reservations = new ArrayList<>();
		}
	}

	public Reservation getReservationAt(int index) {
		return this.reservations.get(index);
	}
	
	public String[] getColumns() {
		return COLUMNS;
	}

}
