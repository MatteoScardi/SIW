package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.repository.CredentialsRepository;
import org.springframework.transaction.annotation.Transactional; // Importa Transactional

@Service
public class CredentialsService {

    @Autowired
    CredentialsRepository credentialsRepository;

    public Credentials getCredentials(Long id) {
        return credentialsRepository.findById(id).orElse(null);
    }
    public Credentials getCredentials(String username) {
        return credentialsRepository.findByUsername(username).orElse(null);
    }

    @Transactional // Aggiungi @Transactional per operazioni di scrittura
    public Credentials saveCredentials(Credentials credentials) {
        return credentialsRepository.save(credentials);
    }
}