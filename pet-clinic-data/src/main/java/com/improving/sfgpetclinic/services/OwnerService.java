package com.improving.sfgpetclinic.services;

import com.improving.sfgpetclinic.models.Owner;


public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);


}
