package ch.hsr.uint1.whitespace.dl;

import java.util.EventListener;


public interface CrudListener<T> extends EventListener
{	  	
	 void changed(MessageData message);
}