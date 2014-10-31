package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.ApplicationMessages;

public class ReservationenTableRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

	private static final long serialVersionUID = -1637363162403937231L;
	private JButton button;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		if (column == 2) {
			button = new JButton(ApplicationMessages.getText("reservations.table.neinButton"));
			button.setEnabled(false);
			return button;
		}
		if (column == 3) {
			button = new JButton(ApplicationMessages.getText("reservations.table.loeschenButton"));
			return button;
		}
		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
	
}
