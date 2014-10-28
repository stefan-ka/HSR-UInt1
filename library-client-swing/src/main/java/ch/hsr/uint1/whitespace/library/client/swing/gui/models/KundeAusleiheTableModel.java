package ch.hsr.uint1.whitespace.library.client.swing.gui.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

public class KundeAusleiheTableModel extends AbstractTableModel implements Observer {

	private static final long serialVersionUID = 5188709439526606073L;
	private final String[] columns = { "reservations.table.gadgetName", "reservations.table.darfAusleihen", "reservations.table.waitingList", "reservations.table.loeschenButton",
			"reservations.table.jaButton", "reservations.table.neinButton" };

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
