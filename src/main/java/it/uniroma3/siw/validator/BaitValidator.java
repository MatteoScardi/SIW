package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Bait;
import it.uniroma3.siw.service.BaitService;

@Component
public class BaitValidator implements Validator{
	
	@Autowired
	BaitService baitService;
	
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Bait.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Bait bait = (Bait) target;
		
		if (bait.getName()!= null && bait.getType()!=null &&  baitService.existsByNameAndType(bait.getName(), bait.getType())) {
			errors.reject("bait.duplicate");
		}
		
	}

}
