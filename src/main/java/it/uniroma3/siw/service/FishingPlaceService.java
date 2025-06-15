package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Fish;
import it.uniroma3.siw.model.FishingPlace;
import it.uniroma3.siw.model.FishingRod;
import it.uniroma3.siw.repository.FishRepository;
import it.uniroma3.siw.repository.FishingPlaceRepository;
import it.uniroma3.siw.repository.FishingRodRepository;

@Service
public class FishingPlaceService {

    @Autowired private FishingPlaceRepository fishingPlaceRepository;
    @Autowired private FishRepository fishRepository;
    @Autowired private FishingRodRepository fishingRodRepository;

    public Iterable<FishingPlace> getAllPlace(){
        return fishingPlaceRepository.findAll();
    }

    public FishingPlace getPlaceById(Long id) {
        return fishingPlaceRepository.findById(id).orElse(null);
    }

    public FishingPlace save(FishingPlace place) {
        return fishingPlaceRepository.save(place);
    }

    public void deletePlace(Long id) {
        fishingPlaceRepository.deleteById(id);
    }

    public List<FishingPlace> findFishingPlaceNotAssociatedWithFish(Fish fish) {
        return fishingPlaceRepository.findByFishNotContains(fish);
    }
    
    public List<FishingPlace> findFishingPlaceNotAssociatedWithRod(FishingRod rod) {
		return fishingPlaceRepository.findByFishingRodNotContains(rod);
	}

    public boolean existsByNameAndType(String name, String type) {
		return fishingPlaceRepository.existsByNameAndType(name, type);
	}
    
    
    
    
    @Transactional
    public FishingPlace addFishToPlace(Long placeId, List<Long> fishIds) {
        FishingPlace place = fishingPlaceRepository.findById(placeId).orElse(null);
        if (place != null && fishIds != null && !fishIds.isEmpty()) {
            List<Fish> fishesToAdd = new ArrayList<>();
            for (Long fishId : fishIds) {
                Fish fish = fishRepository.findById(fishId).orElse(null);
                if (fish != null) {
                    fishesToAdd.add(fish);
                }
            }
            place.getFish().addAll(fishesToAdd);
            return fishingPlaceRepository.save(place);
        }
        return place;
    }

    @Transactional
    public FishingPlace removeFishFromPlace(Long placeId, Long fishId) {
        FishingPlace place = fishingPlaceRepository.findById(placeId).orElse(null);
        Fish fishToRemove = fishRepository.findById(fishId).orElse(null);
        if (place != null && fishToRemove != null) {
            place.getFish().remove(fishToRemove);
            return fishingPlaceRepository.save(place);
        }
        return place;
    }

    
    
    @Transactional
    public FishingPlace addFishingRodToPlace(Long placeId, List<Long> rodIds) {
        FishingPlace place = fishingPlaceRepository.findById(placeId).orElse(null);
        if (place != null && rodIds != null && !rodIds.isEmpty()) {
            List<FishingRod> rodsToAdd = new ArrayList<>();
            for (Long rodId : rodIds) {
                FishingRod rod = fishingRodRepository.findById(rodId).orElse(null);
                if (rod != null) {
                    rodsToAdd.add(rod);
                }
            }
            place.getFishingRod().addAll(rodsToAdd);
            return fishingPlaceRepository.save(place);
        }
        return place;
    }

    @Transactional
    public FishingPlace removeFishingRodFromPlace(Long placeId, Long rodId) {
        FishingPlace place = fishingPlaceRepository.findById(placeId).orElse(null);
        FishingRod rodToRemove = fishingRodRepository.findById(rodId).orElse(null);
        if (place != null && rodToRemove != null) {
            place.getFishingRod().remove(rodToRemove);
            return fishingPlaceRepository.save(place);
        }
        return place;
    }

}