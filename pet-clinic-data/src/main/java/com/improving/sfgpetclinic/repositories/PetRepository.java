package com.improving.sfgpetclinic.repositories;

import com.improving.sfgpetclinic.models.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
