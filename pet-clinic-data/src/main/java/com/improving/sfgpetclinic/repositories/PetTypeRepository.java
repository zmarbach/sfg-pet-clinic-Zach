package com.improving.sfgpetclinic.repositories;

import com.improving.sfgpetclinic.models.PetType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
