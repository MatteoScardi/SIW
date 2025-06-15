package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Fish;
import it.uniroma3.siw.service.FishService;

@Component
public class FishValidator implements Validator{
	
	@Autowired
	FishService fishService;
	
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Fish.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Fish fish = (Fish) target;
		
		if (fish.getName()!= null && fish.getScientificName()!=null && fishService.existsByNameAndScientificName(fish.getName(), fish.getScientificName())) {
			errors.reject("fish.duplicate");
		}
		
	}

}
