package ch.hsr.uint1.whitespace.library.client.swing.gui.models;

import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import ch.hsr.uint1.whitespace.library.client.swing.data.MessageData;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;

public class GadgetListModel extends AbstractListModel<Gadget> implements Observer {

	private static final long serialVersionUID = 2629450915101761009L;

	private Library library;

	public GadgetListModel(Library library) {
		this.library = library;
		library.addObserver(this);
	}

	@Override
	public int getSize() {
		return library.getGadgets().size();
	}

	@Override
	public Gadget getElementAt(int index) {
		return library.getGadgets().get(index);
	}

	@Override
	public void update(Observable observable, Object object) {
		MessageData data = (MessageData) object;
		if (data.getData() instanceof Gadget) {
			Gadget gadget = (Gadget) data.getData();
			int pos = library.getGadgets().indexOf(gadget);
			fireContentsChanged(this, pos, pos);
		}
	}

}