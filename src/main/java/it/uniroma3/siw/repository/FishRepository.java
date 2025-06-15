package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Bait;
import it.uniroma3.siw.model.Fish;
import it.uniroma3.siw.model.FishingPlace;

public interface FishRepository extends CrudRepository<Fish, Long>{
	
	List<Fish> findByBaitNotContains(Bait bait);
	List<Fish> findByFishingPlaceNotContains(FishingPlace fishingPlace);
	
	boolean existsByNameAndScientificName(String name, String scientificName);
}
