package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.FishingPlace;
import it.uniroma3.siw.model.FishingRod;
import it.uniroma3.siw.repository.FishingRodRepository;

@Service
public class FishingRodService {

	@Autowired
	private FishingRodRepository fishingRodRepository;
	
	public Iterable<FishingRod> getAllRod(){
		return fishingRodRepository.findAll();
	}
	
	public FishingRod getRodById(Long id) {
		return fishingRodRepository.findById(id).get();
	}
	
	public FishingRod save(FishingRod rod) {
		return fishingRodRepository.save(rod);
	}
	
	public void deleteRod(Long id) {
		fishingRodRepository.deleteById(id);
	 }
	
	public List<FishingRod> findFishingRodNotAssociatedWithFishingPlace(FishingPlace place) {
        return fishingRodRepository.findByFishingPlaceNotContains(place); 
    }
	
	public boolean existsByNameAndType(String name, String type) {
		return fishingRodRepository.existsByNameAndType(name, type);
	}
}	
