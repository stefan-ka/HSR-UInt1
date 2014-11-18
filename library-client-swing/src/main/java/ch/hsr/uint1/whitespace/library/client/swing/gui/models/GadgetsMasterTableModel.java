package ch.hsr.uint1.whitespace.library.client.swing.gui.models;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.table.AbstractTableModel;

import ch.hsr.uint1.whitespace.library.client.swing.data.MessageData;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Loan;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.ApplicationMessages;

public class GadgetsMasterTableModel extends AbstractTableModel implements Observer {

	private static final long serialVersionUID = -2835644537611279457L;

	private Library library;
	private List<Gadget> gadgetList;
	private static final String[] COLUMNS = { "master.gadgets.jTable.gadgetId", "master.gadgets.jTable.gadgetName", "master.gadgets.jTable.gadgetHersteller",
			"master.gadgets.jTable.gadgetPreis", "master.gadgets.jTable.gadgetZustand", "master.gadgets.jTable.gadgetVerfügbarAb", "master.gadgets.jTable.gadgetAusgeliehenAn" };

	private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");

	public GadgetsMasterTableModel(Library library) {
		this.library = library;
		gadgetList = library.getGadgets();
		library.addObserver(this);
	}

	@Override
	public int getRowCount() {
		if (library == null)
			return 0;
		return library.getGadgets().size();
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
			return ApplicationMessages.getText(localGadget.getCondition().toString());
		case 5:
			return getDisponibility(library.getLoansFor(localGadget, true));
		case 6:
			return getCustomerNameFromGadget(library.getLoansFor(localGadget, true));
		default:
			return null;
		}
	}

	private String getCustomerNameFromGadget(List<Loan> loansFor) {
		String customerName = "";
		for (Loan loan : loansFor) {
			customerName = loan.getCustomerId();
			customerName = library.getCustomer(customerName).getName();
		}
		return customerName;
	}

	private String getDisponibility(List<Loan> loansFor) {
		String date = "-";
		if (loansFor.size() == 0)
			date = ApplicationMessages.getText("master.gadgetsAvailableNow");
		else
			date = sdf.format(loansFor.get(0).overDueDate());
		return date;
	}

	@Override
	public void update(Observable o, Object arg) {
		MessageData data = (MessageData) arg;
		if (data.getData() instanceof Gadget) {
			Gadget gadget = (Gadget) data.getData();
			int pos = library.getGadgets().indexOf(gadget);
			if (pos < gadgetList.size()) {
				fireTableRowsInserted(pos, pos);
			} else {
				fireTableRowsUpdated(pos, pos);
			}
		}
	}

	public Gadget getGadgetAt(int index) {
		return this.gadgetList.get(index);
	}
	
	public String[] getColumns() {
		return COLUMNS;
	}

}
