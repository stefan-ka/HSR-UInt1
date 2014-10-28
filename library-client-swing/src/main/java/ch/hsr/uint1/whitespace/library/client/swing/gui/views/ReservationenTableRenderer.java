package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ReservationenTableRenderer extends JPanel implements TableCellRenderer {

	private static final long serialVersionUID = -1637363162403937231L;
	private JButton button;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		button = new JButton(value.toString());
		return this;
	}

}
