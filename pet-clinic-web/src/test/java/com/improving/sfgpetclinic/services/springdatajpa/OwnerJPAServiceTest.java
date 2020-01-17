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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJPAServiceTest {
    public static final String LAST_NAME = "Smith";
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks //this tells Mockito to inject the mocked objs above to create an instance of this service
    OwnerJPAService service;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByLastName() {
        //arrange
        Owner returnedOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
        when(ownerRepository.findByLastName(any())).thenReturn(returnedOwner);

        //act
        Owner smith = service.findByLastName(LAST_NAME);

        //assert
        assertEquals(LAST_NAME, smith.getLastName());
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}