package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget.Condition;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.ApplicationMessages;

public class GadgetConditionComboRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1768053450529331776L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (value instanceof Gadget.Condition) {
			Condition condition = (Condition) value;
			label.setText(ApplicationMessages.getText(condition.toString()));
		}
		return label;
	}

}
