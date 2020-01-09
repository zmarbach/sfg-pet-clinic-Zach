package com.improving.sfgpetclinic.services;

import com.improving.sfgpetclinic.models.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);
    Pet save (Pet pet);
    Set<Pet> findAll();
}
