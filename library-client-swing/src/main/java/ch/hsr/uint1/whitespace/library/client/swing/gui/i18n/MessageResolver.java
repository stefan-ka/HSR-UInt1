package ch.hsr.uint1.whitespace.library.client.swing.gui.i18n;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class MessageResolver {

	// TODO implement language switcher on GUI..?
	private static final Locale DEFAULT_LOCALE = Locale.GERMAN;

	@Autowired
	private MessageSource messageSource;

	public String getErrorMessage(FieldError fieldError) {
		return messageSource.getMessage(fieldError, DEFAULT_LOCALE);
	}

	public String getText(String code) {
		return getText(code, new Object[0]);
	}

	public String getText(String code, Object[] args) {
		return messageSource.getMessage(code, args, DEFAULT_LOCALE);
	}

}
