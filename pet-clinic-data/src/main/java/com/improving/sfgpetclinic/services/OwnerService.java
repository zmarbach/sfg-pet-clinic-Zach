package com.improving.sfgpetclinic.services;

import com.improving.sfgpetclinic.models.Owner;

import java.util.List;
import java.util.Set;


public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLikeIgnoreCase(String lastName);


}
