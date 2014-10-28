package ch.hsr.uint1.whitespace.library.client.swing.gui.models;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import ch.hsr.uint1.whitespace.library.client.swing.data.MessageData;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Reservation;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.MessageResolver;

public class ReservationenTableModel extends AbstractTableModel implements Observer {

	private static final long serialVersionUID = 5922343552351790810L;
	private final String[] columns = { "reservations.table.gadgetName", "reservations.table.darfAusleihen", "reservations.table.waitingList", "reservations.table.delete" };
	private MessageResolver messageResolver;
	private Library library;
	private List<Reservation> reservations;
	private final Class<?>[] columnClasses = new Class<?>[] { String.class, String.class, JButton.class, JButton.class };

	public ReservationenTableModel(Library library, MessageResolver messageResolver) {
		this.library = library;
		this.messageResolver = messageResolver;
		library.addObserver(this);
	}

	public Reservation getReservationAt(int index) {
		return reservations.get(index);
	}

	public void setReservationen(List<Reservation> reservations) {
		this.reservations = reservations;
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
			return library.getReservatonFor(gadget, true).size();
		case 2:
			// TODO Create Ja/Nein Button in the table
			return null;
		case 3:
			// TODO Create Loeschen Button in the table
			return null;
		default:
			return null;
		}
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

}
