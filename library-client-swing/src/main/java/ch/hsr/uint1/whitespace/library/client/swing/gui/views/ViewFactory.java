package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import org.springframework.stereotype.Component;

@Component
public abstract class ViewFactory {

	public abstract GadgetDetail createGadgetDetail();

}
