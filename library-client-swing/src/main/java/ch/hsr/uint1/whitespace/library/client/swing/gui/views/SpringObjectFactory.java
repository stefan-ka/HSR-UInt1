package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringObjectFactory {

	@Autowired
	private ApplicationContext context;

	public Object createObject(Class<?> clazz) {
		return context.getBean(clazz);
	}

	/**
	 * Is needed for the GUI Window Builder, because we have no Spring-Context then... 
	 */
	public static Object createObject(Class<?> clazz, SpringObjectFactory factory) {
		try {
			if (factory == null) {
				return clazz.newInstance();
			} else {
				return factory.createObject(clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
