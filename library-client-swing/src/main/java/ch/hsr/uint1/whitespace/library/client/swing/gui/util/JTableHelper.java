package ch.hsr.uint1.whitespace.library.client.swing.gui.util;

import javax.swing.table.TableColumnModel;

import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.ApplicationMessages;

public class JTableHelper {

	public static void updateColumnHeaderText(TableColumnModel columnModel, String[] textKeys) {
		for (int i = 0; i < textKeys.length; i++) {
			columnModel.getColumn(i).setHeaderValue(ApplicationMessages.getText(textKeys[i]));
		}
	}

}
