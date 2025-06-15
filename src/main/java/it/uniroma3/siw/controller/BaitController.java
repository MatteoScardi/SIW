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

import it.uniroma3.siw.model.Bait;
import it.uniroma3.siw.service.BaitService;
import it.uniroma3.siw.service.FishService;
import it.uniroma3.siw.validator.BaitValidator;

@Controller
public class BaitController {

    @Autowired FishService fishService;
    @Autowired BaitService baitService;
    @Autowired BaitValidator baitValidator;

    
    @GetMapping("/formNewBait")
    public String formNewBait(Model model) {
        model.addAttribute("bait", new Bait());
        return "formNewBait.html";
    }

    
    
    @PostMapping("/bait")
    public String newBait(@Valid @ModelAttribute("bait") Bait bait, BindingResult bindingResult, Model model) {
    	this.baitValidator.validate(bait, bindingResult);
    	
    	if (bindingResult.hasErrors()) { 		// sono emersi errori nel binding
    		return "formNewBait.html";
    	} else {
    		 this.baitService.save(bait);
    		 model.addAttribute("bait", bait);
    		 return "redirect:/bait/" + bait.getId();
    	}
    }

    
     
    @GetMapping("/bait/{id}")
    public String getBait(@PathVariable("id") Long id, Model model) {
    	
        Bait bait = this.baitService.getBaitById(id);
        model.addAttribute("bait", bait);
        model.addAttribute("allFish", this.fishService.findFishNotAssociatedWithBait(bait));

        return "bait.html";
    }

    
    
    @GetMapping("/baits")
    public String showBait(Model model) {
        model.addAttribute("baits", this.baitService.getAllBait());
        return "baits.html";
    }

    
    
    @PostMapping("/bait/{id}/delete")
    public String deleteBait(@PathVariable("id") Long id) {
        baitService.deleteBait(id);
        return "redirect:/baits";
    }

    
    
    @GetMapping("/formEditBait/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Bait bait = baitService.getBaitById(id);
        if (bait != null) {
            model.addAttribute("bait", bait);
            return "formEditBait";
        } else {
            return "error/baitNotFound";
        }
    }

    
    
    @PostMapping("/bait/{id}/edit")
    public String updateBait(@PathVariable("id") Long id, @ModelAttribute("bait") Bait updatedBait, Model model) {
        Bait existingBait = baitService.getBaitById(id);
        if (existingBait != null) {
            updatedBait.setId(id);
            Bait savedBait = baitService.save(updatedBait);
            return "redirect:/bait/" + savedBait.getId();
        } else {
            return "error/baitNotFound";
        }
    }

}