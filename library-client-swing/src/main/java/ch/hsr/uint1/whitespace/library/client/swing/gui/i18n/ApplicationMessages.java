package ch.hsr.uint1.whitespace.library.client.swing.gui.i18n;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ApplicationMessages {

	private ApplicationMessages() {
		// do not instantiate
	}

	private static Locale CURRENT_LOCALE = Locale.GERMAN;
	private static final String BUNDLE_NAME = "messages";
	private static ResourceBundle RESOURCE_BUNDLE = loadBundle();
	private static final List<LocaleChangedListener> LOCALE_LISTENERS = new ArrayList<>();

	private static ResourceBundle loadBundle() {
		return ResourceBundle.getBundle(BUNDLE_NAME, CURRENT_LOCALE);
	}

	public static String getText(String key) {
		try {
			ResourceBundle bundle = Beans.isDesignTime() ? loadBundle() : RESOURCE_BUNDLE;
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			return "!" + key + "!";
		}
	}

	public static Locale getCurrentLocale() {
		return CURRENT_LOCALE;
	}

	public static void setCurrentLocale(Locale locale) {
		CURRENT_LOCALE = locale;
		RESOURCE_BUNDLE = loadBundle();
		for (LocaleChangedListener listener : LOCALE_LISTENERS) {
			listener.localeChanged();
		}
	}

	public static void addLocaleChangedListener(LocaleChangedListener listener) {
		LOCALE_LISTENERS.add(listener);
	}
}
