package com.improving.sfgpetclinic.repositories;

import com.improving.sfgpetclinic.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
