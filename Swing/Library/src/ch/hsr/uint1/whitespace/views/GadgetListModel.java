package ch.hsr.uint1.whitespace.views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import ch.hsr.uint1.whitespace.bl.Gadget;
import ch.hsr.uint1.whitespace.bl.Library;

public class GadgetListModel extends AbstractListModel<Gadget> implements Observer {

	private static final long serialVersionUID = 2629450915101761009L;

	private Library library;

	public GadgetListModel(Library library) {
		this.library = library;
		library.addObserver(this);
	}

	public void propagateUpdate(int pos) {
		fireContentsChanged(this, pos, pos);
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
		if (observable instanceof Gadget) {
			int pos = library.getGadgets().indexOf(observable);
			fireContentsChanged(this, pos, pos);
		}
		if (observable instanceof Library) {
			fireContentsChanged(this, 0, getSize());
		}
	}

}
