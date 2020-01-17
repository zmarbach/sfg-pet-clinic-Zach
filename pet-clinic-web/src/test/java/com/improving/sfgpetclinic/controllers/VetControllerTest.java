package com.improving.sfgpetclinic.controllers;

import com.improving.sfgpetclinic.models.Vet;
import com.improving.sfgpetclinic.services.VetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//must have this annotation if want to use @InjectMocks..otherwise need 2 additional lines of code in SetUp method (see below)
@ExtendWith(MockitoExtension.class)
class VetControllerTest {
    @Mock
    VetService vetService;

    @InjectMocks
    VetController vetController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        vetController = new VetController(vetService);
        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();

    }

    @Test
    void vetIndex() throws Exception {
        //arrange
        Vet vet = new Vet();
        vet.setId(1L);
        vet.setFirstName("Zach");
        Set<Vet> vets = new HashSet<>();
        vets.add(vet);

        when(vetService.findAll()).thenReturn(vets);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/vets"))
                .andExpect(status().isOk())
                .andExpect(view().name("vets/index"))
                .andExpect(model().attribute("vets", hasSize(1)));
    }
}