package ch.hsr.uint1.whitespace.library.client.swing.dl;

import java.util.EventListener;

public interface CrudListener<T> extends EventListener {
	void changed(MessageData message);
}