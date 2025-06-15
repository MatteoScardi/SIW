package it.uniroma3.siw.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CatchLog {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
