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
import ch.hsr.uint1.whitespace.library.client.swing.gui.views.ButtonColumn;

public class ReservationenTableModel extends AbstractTableModel implements Observer {

	private static final long serialVersionUID = 5922343552351790810L;
	private final String[] columns = { "reservations.table.gadgetName", "reservations.table.waitingList", "reservations.table.darfAusleihen", "reservations.table.delete" };
	private Library library;
	private List<Reservation> reservations;
	private final Class<?>[] columnClasses = new Class<?>[] { String.class, String.class, ButtonColumn.class, ButtonColumn.class };

	private Customer selectedCustomer;

	public ReservationenTableModel(Library library) {
		this.library = library;
		loadReservations();
		library.addObserver(this);
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return ApplicationMessages.getText(columns[columnIndex]);
	}

	@Override
	public int getRowCount() {
		return reservations.size();
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return columnClasses[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Reservation reservation = reservations.get(rowIndex);
		Gadget gadget = library.getGadget(reservation.getGadgetId());
		switch (columnIndex) {
		case 0:
			return gadget.getName();
		case 1:
			return library.getReservationFor(gadget, true).size();
		case 2:
			return ApplicationMessages.getText("reservations.table.neinButton");
		case 3:
			return ApplicationMessages.getText("reservations.table.loeschenButton");
		default:
			return null;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 2 || columnIndex == 3)
			return true;
		return super.isCellEditable(rowIndex, columnIndex);
	}

	@Override
	public void update(Observable o, Object arg) {
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
	}

	private void loadReservations() {
		if (selectedCustomer != null) {
			reservations = library.getReservationFor(selectedCustomer, true);
		} else {
			reservations = new ArrayList<>();
		}
		fireTableDataChanged();
	}

}
