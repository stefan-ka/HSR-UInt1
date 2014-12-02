package ch.hsr.uint1.whitespace.library.client.swing.domain.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;

@Component
public class GadgetValidator implements Validator {

	@Autowired
	private Library library;

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
		if (gadget.getPrice() < 0) {
			errors.rejectValue("price", "gadget.price.notNegative");
		}
		if (gadget.getPrice() == 0.00) {
			errors.rejectValue("price", "gadget.price.notNull");
		}
		for (Gadget otherGadget : library.getGadgets()) {
			if (gadget.getInventoryNumber().equals(otherGadget.getInventoryNumber())) {
				continue;
			}
			if (gadget.getName().equals(otherGadget.getName())) {
				errors.rejectValue("name", "gadget.name.alreadyexists");
				break;
			}
		}
	}

}
