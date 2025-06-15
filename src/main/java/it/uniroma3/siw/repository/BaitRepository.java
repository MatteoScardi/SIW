package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Bait;
import it.uniroma3.siw.model.Fish;

public interface BaitRepository extends CrudRepository<Bait, Long>{
	
	List<Bait> findByFishNotContains(Fish fish);
	
	boolean existsByNameAndType(String name, String type);
}
