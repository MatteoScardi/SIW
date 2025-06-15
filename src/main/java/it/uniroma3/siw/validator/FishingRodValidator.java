package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.FishingRod;
import it.uniroma3.siw.service.FishingRodService;

@Component
public class FishingRodValidator implements Validator{

	@Autowired
	FishingRodService fishingRodService;
	
	
	@Override
	public boolean supports(Class<?> aClass) {
		return FishingRod.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FishingRod fishingRod = (FishingRod) target;
		
		if (fishingRod.getName() != null && fishingRod.getType() != null && fishingRodService.existsByNameAndType(fishingRod.getName(), fishingRod.getType())) {
			errors.reject("rod.duplicate");
		}
		
	}

}
