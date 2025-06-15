package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Bait;
import it.uniroma3.siw.model.Fish;
import it.uniroma3.siw.model.FishingPlace;
import it.uniroma3.siw.repository.BaitRepository;
import it.uniroma3.siw.repository.FishRepository;

@Service
public class FishService {

    @Autowired
    private FishRepository fishRepository;
    @Autowired
    private BaitRepository baitRepository;


    public Iterable<Fish> getAllFish(){
        return fishRepository.findAll();
    }

    public Fish getFishById(Long id) {
        return fishRepository.findById(id).orElse(null);
    }

    public Fish save(Fish fish) {
        return fishRepository.save(fish);
    }

    public void deleteFish(Long id) {
        fishRepository.deleteById(id);
    }
    
    public List<Fish> findFishNotAssociatedWithBait(Bait bait) {
	    return fishRepository.findByBaitNotContains(bait);
	}
    public List<Fish> findFishNotAssociatedWithPlace(FishingPlace place) {
	    return fishRepository.findByFishingPlaceNotContains(place);
	}
    
    public boolean existsByNameAndScientificName(String name, String scientificName) {
		return fishRepository.existsByNameAndScientificName(name, scientificName);
	}
    
    
    @Transactional
    public Fish addBaitToFish(Long fishId, List<Long> baitIds) {
        Fish fish = fishRepository.findById(fishId).orElse(null);
        if (fish != null && baitIds != null && !baitIds.isEmpty()) {
            List<Bait> baitsToAdd = new ArrayList<>();
            for (Long baitId : baitIds) {
                Bait bait = baitRepository.findById(baitId).orElse(null);
                if (bait != null) {
                    baitsToAdd.add(bait);
                }
            }
            fish.getBait().addAll(baitsToAdd);
            return fishRepository.save(fish);
        }
        return fish;
    }

    @Transactional
    public Fish removeBaitFromFish(Long fishId, Long baitId) {
        Fish fish = fishRepository.findById(fishId).orElse(null);
        Bait baitToRemove = baitRepository.findById(baitId).orElse(null);
        if (fish != null && baitToRemove != null) {
            fish.getBait().remove(baitToRemove);
            return fishRepository.save(fish);
        }
        return fish;
    }

}