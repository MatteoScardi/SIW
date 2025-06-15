package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.FishingPlace;
import it.uniroma3.siw.model.FishingRod;

public interface FishingRodRepository extends CrudRepository<FishingRod, Long> {
    
	List<FishingRod> findByFishingPlaceNotContains(FishingPlace fishingPlace); 
	
	boolean existsByNameAndType(String name, String type);
}