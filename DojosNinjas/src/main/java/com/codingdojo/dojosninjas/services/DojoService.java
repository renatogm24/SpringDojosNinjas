package com.codingdojo.dojosninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.dojosninjas.models.Dojo;
import com.codingdojo.dojosninjas.repositories.DojoRepository;


@Service
public class DojoService {
	private final DojoRepository dojoRepository;

    public DojoService(DojoRepository dojoRepository) {
        this.dojoRepository = dojoRepository;
    }
    // devuelve todos los dojos
    public List<Dojo> allDojos() {
        return dojoRepository.findAll();
    }
    // crea un dojo
    public Dojo createDojo(Dojo b) {
        return dojoRepository.save(b);
    }
    //actualiza un dojo
    public Dojo updateDojo(Dojo b) {
        return dojoRepository.save(b);
    }
    // recupera un dojo
    public Dojo findDojo(Long id) {
        Optional<Dojo> optionalDojo = dojoRepository.findById(id);
        if(optionalDojo.isPresent()) {
            return optionalDojo.get();
        } else {
            return null;
        }
    }
    
    public void deleteDojo(Long id) {
    	dojoRepository.deleteById(id);
    }
}
