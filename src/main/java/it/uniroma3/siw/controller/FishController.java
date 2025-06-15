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

import it.uniroma3.siw.model.Fish;
import it.uniroma3.siw.service.BaitService;
import it.uniroma3.siw.service.FishService;
import it.uniroma3.siw.service.FishingPlaceService;
import it.uniroma3.siw.validator.FishValidator;

@Controller
public class FishController {

    @Autowired FishService fishService;
    @Autowired BaitService baitService;
    @Autowired FishingPlaceService fishingPlaceService;
    @Autowired FishValidator fishValidator;

    
    
    @GetMapping("/")
    public String home() {
        return "home.html";
    }

    
    
    @GetMapping("/formNewFish")
    public String formNewFish(Model model) {
        model.addAttribute("fish", new Fish());
        return "formNewFish.html";
    }

    
    
    @PostMapping("/fish")
    public String newFish(@Valid @ModelAttribute("fish") Fish fish, BindingResult bindingResult, Model model) {
    	
    	this.fishValidator.validate(fish, bindingResult);
    	
    	if (bindingResult.hasErrors()) { 		
    		return "formNewFish.html";
    	} else {
    		 this.fishService.save(fish);
    		 model.addAttribute("fish", fish);
    		 return "redirect:/fish/" + fish.getId();
    	}
    }

    
    
    @GetMapping("/fish/{id}")
    public String getFish(@PathVariable("id") Long id, Model model) {
        Fish fish = this.fishService.getFishById(id);
        
        model.addAttribute("fish", fish);
        model.addAttribute("allBait", this.baitService.findBaitNotAssociatedWithFish(fish));
        model.addAttribute("allFishingPlace", this.fishingPlaceService.findFishingPlaceNotAssociatedWithFish(fish));

        return "fish.html";
    }


    
    @GetMapping("/fishes")
    public String showFish(Model model) {
        model.addAttribute("fishes", this.fishService.getAllFish());
        return "fishes.html";
    }

    
    
    @PostMapping("/fish/{id}/delete")
    public String deleteFish(@PathVariable("id") Long id) {
        fishService.deleteFish(id);
        return "redirect:/fishes";
    }

    
    
    @GetMapping("/formEditFish/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Fish fish = fishService.getFishById(id);
        if (fish != null) {
            model.addAttribute("fish", fish);
            return "formEditFish";
        } else {
            return "error/fishNotFound";
        }
    }

    
    
    @PostMapping("/fish/{id}/edit")
    public String updateFish(@PathVariable("id") Long id, @ModelAttribute("fish") Fish updatedFish, Model model) {
        Fish existingFish = fishService.getFishById(id);
        if (existingFish != null) {
            updatedFish.setId(id);
            Fish savedFish = fishService.save(updatedFish);
            return "redirect:/fish/" + savedFish.getId();
        } else {
            return "error/fishNotFound";
        }
    }

    
    
    //Funzioni per gestire bait to fish
    @PostMapping("/fish/{fishId}/addBait")
    public String addBaitToFish(@PathVariable("fishId") Long fishId, @RequestParam("baitToAdd") List<Long> baitIds) {
        this.fishService.addBaitToFish(fishId, baitIds);
        return "redirect:/fish/" + fishId;
    }


    
    @PostMapping("/fish/{fishId}/removeBait")
    public String removeBaitFromFish(@PathVariable("fishId") Long fishId, @RequestParam("baitToRemove") Long baitId) {
        this.fishService.removeBaitFromFish(fishId, baitId);
        return "redirect:/fish/" + fishId;
    }


}