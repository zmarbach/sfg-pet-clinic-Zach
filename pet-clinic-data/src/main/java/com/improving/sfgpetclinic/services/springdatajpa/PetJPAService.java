package com.improving.sfgpetclinic.services.springdatajpa;

import com.improving.sfgpetclinic.models.Pet;
import com.improving.sfgpetclinic.repositories.PetRepository;
import com.improving.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetJPAService implements PetService {
    private final PetRepository petRepository;

    public PetJPAService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long aLong) {

        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet obj) {
        return petRepository.save(obj);
    }

    @Override
    public void delete(Pet obj) {
        petRepository.delete(obj);
    }

    @Override
    public void deleteById(Long aLong) {
        petRepository.deleteById(aLong);
    }
}
