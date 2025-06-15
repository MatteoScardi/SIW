package it.uniroma3.siw.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.FishingPlace;
import it.uniroma3.siw.service.FishingPlaceService;

@Component
public class FishingPlaceValidator implements Validator{

	@Autowired
	FishingPlaceService fishingPlaceService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return FishingPlace.class.equals(aClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FishingPlace fishingPlace = (FishingPlace) target;
		
		if (fishingPlace.getName() != null && fishingPlace.getType() != null && fishingPlaceService.existsByNameAndType(fishingPlace.getName(), fishingPlace.getType())) {
				errors.reject("place.duplicate");
		}

	}
}
