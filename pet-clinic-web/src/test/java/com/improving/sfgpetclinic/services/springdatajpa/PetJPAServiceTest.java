package com.improving.sfgpetclinic.services.springdatajpa;

import com.improving.sfgpetclinic.models.Pet;
import com.improving.sfgpetclinic.repositories.PetRepository;
import com.improving.sfgpetclinic.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetJPAServiceTest {
    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetJPAService petJPAService;

    Pet samplePet;
    private final Long sampleId = 1L;
    private final String sampleName = "Riley";

    @BeforeEach
    void setUp() {
        samplePet = new Pet();
        samplePet.setId(sampleId);
        samplePet.setName(sampleName);
    }

    @Test
    void findAll() {
        //arrange
        Set<Pet> pets = new HashSet<>();
        pets.add(samplePet);
        when(petRepository.findAll()).thenReturn(pets);

        //act
        Set<Pet> returnedPets = petJPAService.findAll();

        //assert
        assertEquals(1, returnedPets.size());
        verify(petRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        //arrange
        when(petRepository.findById(anyLong())).thenReturn(Optional.ofNullable(samplePet));

        //act
        Pet returnedPet = petJPAService.findById(sampleId);

        //assert
        assertEquals(samplePet, returnedPet);
        assertEquals(sampleId, returnedPet.getId());
        assertNotNull(returnedPet);
        verify(petRepository).findById(anyLong());


    }

    @Test
    void save() {
        //arrange
        when(petRepository.save(any())).thenReturn(samplePet);
        //act
        var returnedPet = petJPAService.save(samplePet);

        //assert
        assertNotNull(returnedPet);
        assertEquals(sampleId, returnedPet.getId());
    }

    @Test
    void delete() {
        //act
        petJPAService.delete(samplePet);
        //assert
        verify(petRepository).delete(any());
    }

    @Test
    void deleteById() {
        //act
        petJPAService.deleteById(sampleId);
        //assert
        verify(petRepository).deleteById(anyLong());
    }
}