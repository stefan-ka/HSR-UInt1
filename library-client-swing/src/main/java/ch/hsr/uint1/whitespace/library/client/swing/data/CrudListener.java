package ch.hsr.uint1.whitespace.library.client.swing.data;

import java.util.EventListener;

public interface CrudListener<T> extends EventListener {
	void changed(MessageData message);
}