package com.codingdojo.dojosninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.dojosninjas.models.Ninja;
import com.codingdojo.dojosninjas.repositories.NinjaRepository;

@Service
public class NinjaService {
	private final NinjaRepository ninjaRepository;

    public NinjaService(NinjaRepository ninjaRepository) {
        this.ninjaRepository = ninjaRepository;
    }
    // devuelve todos los ninjas
    public List<Ninja> allNinjas() {
        return ninjaRepository.findAll();
    }
    // crea un ninja
    public Ninja createNinja(Ninja b) {
        return ninjaRepository.save(b);
    }
    //actualiza un ninja
    public Ninja updateNinja(Ninja b) {
        return ninjaRepository.save(b);
    }
    // recupera un ninja
    public Ninja findNinja(Long id) {
        Optional<Ninja> optionalNinja = ninjaRepository.findById(id);
        if(optionalNinja.isPresent()) {
            return optionalNinja.get();
        } else {
            return null;
        }
    }
    
    public void deleteNinja(Long id) {
    	ninjaRepository.deleteById(id);
    }
}
