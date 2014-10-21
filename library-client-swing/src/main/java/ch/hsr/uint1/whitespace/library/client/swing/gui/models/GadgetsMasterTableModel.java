package ch.hsr.uint1.whitespace.library.client.swing.gui.models;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.uint1.whitespace.library.client.swing.data.MessageData;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Loan;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.MessageResolver;

public class GadgetsMasterTableModel extends AbstractTableModel implements Observer {

	private static final long serialVersionUID = 7702702384849578628L;
	private Library library;
	private MessageResolver messageResolver;
	private final String[] columns = { "master.gadgets.jTable.gadgetId", "master.gadgets.jTable.gadgetName", "master.gadgets.jTable.gadgetHersteller",
			"master.gadgets.jTable.gadgetPreis", "master.gadgets.jTable.gadgetZustand", "master.gadgets.jTable.gadgetVerfügbarAb", "master.gadgets.jTable.gadgetAusgeliehenAn" };

	public GadgetsMasterTableModel(Library library, MessageResolver messageResolver) {
		this.library = library;
		this.messageResolver = messageResolver;
		library.addObserver(this);
	}

	@Override
	public int getRowCount() {
		return columns.length;
	}

	@Override
	public int getColumnCount() {
		return library.getGadgets().size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return messageResolver.getText(columns[columnIndex]);
	}

	// TODO continue
	@Override
	public String getValueAt(int rowIndex, int columnIndex) {
		List<Gadget> gadgetList = this.library.getGadgets();
		Gadget localGadget = gadgetList.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return localGadget.getInventoryNumber();
		case 1:
			return localGadget.getName();
		case 2:
			return localGadget.getManufacturer();
		case 3:
			return new Double(localGadget.getPrice()).toString();
		case 4:
			return localGadget.getCondition().toString();
		case 5:
			return getDisponibility(library.getLoansFor(localGadget, true));
		case 6:
			return getCustomerFromGadget(library.getLoansFor(localGadget, true));
		default:
			return null;
		}
	}

	private String getCustomerFromGadget(List<Loan> loansFor) {
		String customerName = "";
		for (Loan loan : loansFor) {
			customerName = loan.getCustomerId();
		}
		return customerName;
	}

	private String getDisponibility(List<Loan> loansFor) {
		String date = "";
		for (Loan loan : loansFor) {
			date = loan.getReturnDate().toString();
		}
		return date;
	}

	@Override
	public void update(Observable o, Object arg) {
		MessageData data = (MessageData) arg;
		if (data.getData() instanceof Gadget) {
			Gadget gadget = (Gadget) data.getData();
			int pos = library.getGadgets().indexOf(gadget);
			fireTableRowsUpdated(pos, pos);
		}
	}

}
