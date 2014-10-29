package ch.hsr.uint1.whitespace.library.client.swing.gui.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

@Component
public class ErrorMessageResolver {

	@Autowired
	private MessageSource messageSource;

	public String getErrorMessage(FieldError fieldError) {
		return messageSource.getMessage(fieldError, ApplicationMessages.DEFAULT_LOCALE);
	}

}
