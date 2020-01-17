package com.improving.sfgpetclinic.services.springdatajpa;

import com.improving.sfgpetclinic.models.Vet;
import com.improving.sfgpetclinic.repositories.VetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VetJPAServiceTest {
    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VetJPAService vetJPAService;

    Vet sampleVet;
    private final Long sampleId = 1L;

    @BeforeEach
    void setUp() {
        sampleVet = new Vet();
        sampleVet.setId(sampleId);
    }

    @Test
    void findAll() {
        //arrange
        Set<Vet> vets = new HashSet<>();
        vets.add(sampleVet);
        when(vetRepository.findAll()).thenReturn(vets);

        //act
        Set<Vet> returnedVets = vetJPAService.findAll();

        //assert
        assertEquals(1, returnedVets.size());

    }

    @Test
    void findById() {
        //arrange
        when(vetRepository.findById(anyLong())).thenReturn(Optional.of(sampleVet));

        //act
        var returnedVet = vetJPAService.findById(sampleId);

        //assert
        assertEquals(sampleId, returnedVet.getId());
        assertNotNull(returnedVet);
        verify(vetRepository).findById(anyLong());
    }

    @Test
    void findByIdNotFound() {
        //arrange
        when(vetRepository.findById(anyLong())).thenReturn(Optional.empty());

        //act
        var returnedVet = vetJPAService.findById(sampleId);

        //assert
        assertNull(returnedVet);
        verify(vetRepository).findById(anyLong());
    }

    @Test
    void save() {
        when(vetRepository.save(ArgumentMatchers.any())).thenReturn(sampleVet);
        var returnedVet = vetJPAService.save(sampleVet);
        assertEquals(sampleId, returnedVet.getId());
    }

    @Test
    void delete() {
        //act
        vetJPAService.delete(sampleVet);
        //assert
        verify(vetRepository).delete(ArgumentMatchers.any());
    }

    @Test
    void deleteById() {
        //act
        vetJPAService.deleteById(sampleId);
        //assert
        verify(vetRepository).deleteById(ArgumentMatchers.any());
    }
}