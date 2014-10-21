package ch.hsr.uint1.whitespace.library.client.swing.domain.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;

@Component
public class GadgetValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Gadget.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Gadget gadget = (Gadget) target;
		ValidationUtils.rejectIfEmpty(errors, "name", "gadget.name.empty");
		ValidationUtils.rejectIfEmpty(errors, "manufacturer", "gadget.manufacturer.empty");
		ValidationUtils.rejectIfEmpty(errors, "price", "gadget.price.empty");
		if(gadget.getPrice() < 0) {
			errors.rejectValue("price", "gadget.price.notNegative");
		}
	}

}
