package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional; // Importa Transactional

@Service
public class UserService {

    @Autowired UserRepository userRepository;

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional // Aggiungi @Transactional per operazioni di scrittura
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}