package com.improving.sfgpetclinic.repositories;

import com.improving.sfgpetclinic.models.Vet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface VetRepository extends CrudRepository<Vet, Long> {
}
