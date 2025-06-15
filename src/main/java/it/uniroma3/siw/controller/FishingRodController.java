package it.uniroma3.siw.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.FishingRod;
import it.uniroma3.siw.service.FishingPlaceService;
import it.uniroma3.siw.service.FishingRodService;
import it.uniroma3.siw.validator.FishingRodValidator;

@Controller
public class FishingRodController {
	
	@Autowired FishingRodService fishingRodService;
	@Autowired FishingPlaceService fishingPlaceService;
	@Autowired FishingRodValidator fishingRodValidator;
	
	
	
	@GetMapping("/formNewRod")
	public String formNewRod(Model model) {
		model.addAttribute("rod", new FishingRod());
		return "formNewRod.html";
	}
	
	
	
	@PostMapping("/rod")
	public String newFishingRod(@Valid @ModelAttribute("rod") FishingRod rod, BindingResult bindingResult, Model model) {
    	
    	this.fishingRodValidator.validate(rod, bindingResult);
    	
    	if (bindingResult.hasErrors()) { 		// sono emersi errori nel binding
    		return "formNewRod.html";
    	} else {
    		 this.fishingRodService.save(rod);
    		 model.addAttribute("rod", rod);
    		 return "redirect:/rod/" + rod.getId();
    	}
	}
	
	
	
	@GetMapping("/rod/{id}")		
	public String getFishingRod(@PathVariable("id") Long id, Model model) {
		FishingRod rod = this.fishingRodService.getRodById(id);

        model.addAttribute("rod", rod);
        model.addAttribute("allPlace", this.fishingPlaceService.findFishingPlaceNotAssociatedWithRod(rod));

        return "rod.html";
	}
	
	
	
	@GetMapping("/rods")
	public String showFishingRod(Model model) {
		model.addAttribute("rods", this.fishingRodService.getAllRod());
		return "rods.html";
	}
	
	
	
	@PostMapping("/rod/{id}/delete")
    public String deleteRod(@PathVariable("id") Long id) {
		fishingRodService.deleteRod(id);
        return "redirect:/rods";
    }
	
	
	
	@GetMapping("/formEditRod/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        FishingRod rod = fishingRodService.getRodById(id);
        if (rod != null) {
            model.addAttribute("rod", rod);
            return "formEditRod";
        } else {
            return "error/rodNotFound";
        }
    }

	
	
    @PostMapping("/rod/{id}/edit")
    public String updateRod(@PathVariable("id") Long id, @ModelAttribute("rod") FishingRod updatedRod, Model model) {
        FishingRod existingRod = fishingRodService.getRodById(id);
        if (existingRod != null) {
        	updatedRod.setId(id);
            FishingRod savedRod = fishingRodService.save(updatedRod);
            return "redirect:/rod/" + savedRod.getId();
        } else {
            return "error/rodNotFound";
        }
    }
}
