package com.improving.sfgpetclinic.services.map;

import com.improving.sfgpetclinic.models.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;
    final Long mockOwnerId = 1L;
    final String mockLastName = "Smith";

    @BeforeEach
    void setUp() {
        //can use the actual implementation classes cuz just a HashMap implementation...effectively doing the dependency injection here
        //then add one made-up owner to the newly created map;
        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());
        Owner ownerToSave = Owner.builder().id(mockOwnerId).lastName(mockLastName).build();
        ownerServiceMap.save(ownerToSave);
    }

    @Test
    void findAll() {
        var owners = ownerServiceMap.findAll();
        assertEquals(1, owners.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(mockOwnerId);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(mockOwnerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        Owner owner = Owner.builder().id(2L).build();
        Owner savedOwner = ownerServiceMap.save(owner);
        assertEquals(2, ownerServiceMap.findAll().size());
        assertEquals(2L, savedOwner.getId());
    }

    @Test
    void saveNoId(){
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(mockOwnerId);
        assertEquals(mockOwnerId, owner.getId());

    }

    @Test
        // TODO: why does this test return NPE when using Owner builder? Builder creates owner where all properties are null...why?
    void findByLastName() {
//        Owner newOwner = new Owner();
//        newOwner.setLastName("Marbach");
//        ownerServiceMap.save(newOwner);

//        Owner returnedOwner = ownerServiceMap.findByLastName("Marbach");

        Owner returnedOwner = ownerServiceMap.findByLastName(mockLastName);
        assertNotNull(returnedOwner);
        assertEquals(mockLastName, returnedOwner.getLastName());

    }
}