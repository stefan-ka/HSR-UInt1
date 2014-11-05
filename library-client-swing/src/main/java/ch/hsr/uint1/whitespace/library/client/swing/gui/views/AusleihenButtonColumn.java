package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import java.awt.Component;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTable;

import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.ApplicationMessages;

public class AusleihenButtonColumn extends ButtonColumn {

	private static final long serialVersionUID = -8139272599351439962L;

	public AusleihenButtonColumn(JTable table, Action action, int column) {
		super(table, action, column);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		JButton button = (JButton) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		button.setEnabled(ApplicationMessages.getText("reservations.table.jaButton").equals(value));
		return button;
	}

}
