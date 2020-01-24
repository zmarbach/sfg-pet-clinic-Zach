package com.improving.sfgpetclinic.services.springdatajpa;

import com.improving.sfgpetclinic.models.Owner;
import com.improving.sfgpetclinic.repositories.OwnerRepository;
import com.improving.sfgpetclinic.repositories.PetRepository;
import com.improving.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJPAServiceTest {
    //very important to test the SERVICE in isolation.
    // Mock all the dependencies and create fake data...then when dependency methods are called, return that fake data
    //just testing if SERVICE methods get to the right methods on object dependencies (Ex: ownerRepository)
    //use Mockito to tap into dependency objects methods and FORCE them to return what we want to test

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks //this tells Mockito to inject the mocked objs above to create an instance of this service
    OwnerJPAService service;

    public static final String LAST_NAME = "Smith";
    public static final Long ID = 1L;

    Owner returnedOwner;

    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(ID).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        //arrange
        when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);

        //act
        Owner smith = service.findByLastName(LAST_NAME);

        //assert
        assertEquals(LAST_NAME, smith.getLastName());
    }

    @Test
    void findAll() {
        //arrange
        Set<Owner> ownersSet = new HashSet<>();
        ownersSet.add(returnedOwner);
        ownersSet.add(Owner.builder().id(2L).lastName("Marbach").build());
        when(ownerRepository.findAll()).thenReturn(ownersSet);

        //act
        Set<Owner> testingSet = service.findAll();

        //assert
        assertNotNull(ownersSet);
        assertEquals(2, testingSet.size());
    }

    @Test
    void findById() {
        //arrange
        when(ownerRepository.findById(ID)).thenReturn(Optional.ofNullable(returnedOwner));

        //act
        Owner owner = service.findById(ID);

        //assert
        assertEquals(ID, owner.getId());
    }

    @Test
    void findByIdNotFound() {
        //arrange
        when(ownerRepository.findById(ID)).thenReturn(Optional.empty());

        //act
        Owner owner = service.findById(ID);

        //assert
        assertNull(owner);
    }

    @Test
    void findAllByLastNameLikeReturnsOne(){
        //arrange
        List<Owner> owners = new ArrayList<>();
        owners.add(returnedOwner);
        when(ownerRepository.findAllByLastNameLikeIgnoreCase(anyString())).thenReturn(owners);

        //act
        List<Owner> returnedOwnerSet = service.findAllByLastNameLikeIgnoreCase(LAST_NAME);

        //assert
        assertEquals(1, returnedOwnerSet.size());
    }

    @Test
    void findAllByLastNameLikeReturnsMany(){
        //arrange
        List<Owner> owners = new ArrayList<>();
        owners.add(returnedOwner);
        owners.add(Owner.builder().lastName("Smith").id(2L).build());
        when(ownerRepository.findAllByLastNameLikeIgnoreCase(anyString())).thenReturn(owners);

        //act
        List<Owner> returnedOwnerSet = service.findAllByLastNameLikeIgnoreCase(LAST_NAME);

        //assert
        assertEquals(2, returnedOwnerSet.size());
    }

    @Test
    void saveExisitingID() {
        //arrange
        Owner ownerToSave = Owner.builder().id(3L).build();
        when(ownerRepository.save(any())).thenReturn(returnedOwner);

        //act
        var owner = service.save(ownerToSave);

        //assert
        assertEquals(returnedOwner.getId(), owner.getId());
        assertNotNull(owner);
    }

    @Test
    void saveNoID() {
        //arrange
        when(ownerRepository.save(any())).thenReturn(returnedOwner);

        //act
        var owner = service.save(returnedOwner);

        //assert
        assertNotNull(owner.getId());
    }

    @Test
    void delete() {
        //arrange

        //act
        service.delete(returnedOwner);

        //assert
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        //arrange

        //act
        service.deleteById(ID);

        //assert
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }


}