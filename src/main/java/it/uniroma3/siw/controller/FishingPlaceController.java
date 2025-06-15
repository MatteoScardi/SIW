package it.uniroma3.siw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.FishingPlace;
import it.uniroma3.siw.service.FishService;
import it.uniroma3.siw.service.FishingPlaceService;
import it.uniroma3.siw.service.FishingRodService;
import it.uniroma3.siw.validator.FishingPlaceValidator;

@Controller
public class FishingPlaceController {

    @Autowired FishingPlaceService fishingPlaceService;
    @Autowired FishService fishService;
    @Autowired FishingRodService fishingRodService;
    @Autowired FishingPlaceValidator fishingPlaceValidator;

    
    
    @GetMapping("/formNewPlace")
    public String formNewPlace(Model model) {
        model.addAttribute("place", new FishingPlace());
        return "formNewPlace.html";
    }

    
    
    @PostMapping("/place")
    public String newPlace(@Valid @ModelAttribute("place") FishingPlace place, BindingResult bindingResult, Model model) {
    	
    	this.fishingPlaceValidator.validate(place, bindingResult);
    	
    	if (bindingResult.hasErrors()) { 		// sono emersi errori nel binding
    		return "formNewPlace.html";
    	} else {
    		 this.fishingPlaceService.save(place);
    		 model.addAttribute("place", place);
    		 return "redirect:/place/" + place.getId();
    	}
    }

    
    
    @GetMapping("/place/{id}")
    public String getPlace(@PathVariable("id") Long id, Model model) {
        FishingPlace place = this.fishingPlaceService.getPlaceById(id);

        model.addAttribute("place", place);
        model.addAttribute("allFish", this.fishService.findFishNotAssociatedWithPlace(place));
        model.addAttribute("allRods", this.fishingRodService.findFishingRodNotAssociatedWithFishingPlace(place));

        return "place.html";
    }
   
    

    @GetMapping("/places")
    public String showPlace(Model model) {
        model.addAttribute("places", this.fishingPlaceService.getAllPlace());
        return "places.html";
    }

    
    
    @PostMapping("/place/{id}/delete")
    public String deletePlace(@PathVariable("id") Long id) {
        fishingPlaceService.deletePlace(id);
        return "redirect:/places";
    }

    
    
    @GetMapping("/formEditPlace/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        FishingPlace place = fishingPlaceService.getPlaceById(id);
        if (place != null) {
            model.addAttribute("place", place);
            return "formEditPlace";
        } else {
            return "error/placeNotFound";
        }
    }

    
    
    @PostMapping("/place/{id}/edit")
    public String updatePlace(@PathVariable("id") Long id, @ModelAttribute("place") FishingPlace updatedPlace, Model model) {
        FishingPlace existingPlace = fishingPlaceService.getPlaceById(id);
        if (existingPlace != null) {
            updatedPlace.setId(id);
            FishingPlace savedPlace = fishingPlaceService.save(updatedPlace);
            return "redirect:/place/" + savedPlace.getId();
        } else {
            return "error/placeNotFound";
        }
    }

    
    
    // Funzioni per gestrire fish to place
    @PostMapping("/place/{placeId}/addFish")
    public String addFishToPlace(@PathVariable("placeId") Long placeId, @RequestParam("fishToAdd") List<Long> fishIds) {
        this.fishingPlaceService.addFishToPlace(placeId, fishIds);
        return "redirect:/place/" + placeId;
    }

    @PostMapping("/place/{placeId}/removeFish")
    public String removeFishFromPlace(@PathVariable("placeId") Long placeId, @RequestParam("fishToRemove") Long fishId) {
        this.fishingPlaceService.removeFishFromPlace(placeId, fishId);
        return "redirect:/place/" + placeId;
    }

    
 // Funzioni per gestrire rod to place
    @PostMapping("/place/{placeId}/addFishingRod")
    public String addFishingRodToPlace(@PathVariable("placeId") Long placeId, @RequestParam("rodToAdd") List<Long> rodIds) {
        this.fishingPlaceService.addFishingRodToPlace(placeId, rodIds);
        return "redirect:/place/" + placeId;
    }

    @PostMapping("/place/{placeId}/removeFishingRod")
    public String removeFishingRodFromPlace(@PathVariable("placeId") Long placeId, @RequestParam("rodToRemove") Long rodId) {
        this.fishingPlaceService.removeFishingRodFromPlace(placeId, rodId);
        return "redirect:/place/" + placeId;
    }

}