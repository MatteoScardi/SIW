package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Fish;
import it.uniroma3.siw.model.FishingPlace;
import it.uniroma3.siw.model.FishingRod;

public interface FishingPlaceRepository extends CrudRepository<FishingPlace, Long>{
	
	List<FishingPlace> findByFishNotContains(Fish fish);
	List<FishingPlace> findByFishingRodNotContains(FishingRod rod);
	
	boolean existsByNameAndType(String name, String type);
}
