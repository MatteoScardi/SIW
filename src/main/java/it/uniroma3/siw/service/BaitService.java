package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Bait;
import it.uniroma3.siw.model.Fish;
import it.uniroma3.siw.repository.BaitRepository;

@Service
public class BaitService {
	
	@Autowired
	private BaitRepository baitRepository;

	    
    
	public Iterable<Bait> getAllBait(){
		return baitRepository.findAll();
	}
	
	public Bait getBaitById(Long id) {
		return baitRepository.findById(id).get();
	}
	
	public Bait save(Bait bait) {
		return baitRepository.save(bait);
	}
	
	public void deleteBait(Long id) {
		baitRepository.deleteById(id);
	 }
	
	public List<Bait> findBaitNotAssociatedWithFish(Fish fish) {
	    return baitRepository.findByFishNotContains(fish);
	}

	
	public boolean existsByNameAndType(String name, String type) {
		return baitRepository.existsByNameAndType(name, type);
	}
}
