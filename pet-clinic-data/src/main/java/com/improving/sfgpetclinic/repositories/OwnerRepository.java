package com.improving.sfgpetclinic.repositories;

import com.improving.sfgpetclinic.models.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
