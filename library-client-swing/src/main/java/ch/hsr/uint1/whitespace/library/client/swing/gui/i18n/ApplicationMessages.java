package ch.hsr.uint1.whitespace.library.client.swing.gui.i18n;

import java.beans.Beans;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ApplicationMessages {

	private ApplicationMessages() {
		// do not instantiate
	}

	// TODO implement language switcher on GUI..?
	public static final Locale DEFAULT_LOCALE = Locale.GERMAN;
	private static final String BUNDLE_NAME = "messages";
	private static final ResourceBundle RESOURCE_BUNDLE = loadBundle();

	private static ResourceBundle loadBundle() {
		return ResourceBundle.getBundle(BUNDLE_NAME, DEFAULT_LOCALE);
	}

	public static String getText(String key) {
		try {
			ResourceBundle bundle = Beans.isDesignTime() ? loadBundle() : RESOURCE_BUNDLE;
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return "!" + key + "!";
		}
	}
}
