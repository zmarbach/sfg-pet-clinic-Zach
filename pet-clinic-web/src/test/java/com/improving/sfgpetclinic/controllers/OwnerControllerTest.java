package com.improving.sfgpetclinic.controllers;

import com.improving.sfgpetclinic.models.Owner;
import com.improving.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;

    MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        //initializes mock environment for your controller before each test
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void findOwnersPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void searchOwnerReturnMany() throws Exception {
        //arrange
        List<Owner> owners = new ArrayList<>();
        Owner owner = Owner.builder().firstName("Zach").id(1L).build();
        Owner owner2 = Owner.builder().firstName("Kelli").id(2L).build();
        owners.add(owner);
        owners.add(owner2);
        when(ownerService.findAllByLastNameLikeIgnoreCase(anyString())).thenReturn(owners);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("selections", hasSize(2)))
                .andExpect(view().name("owners/ownersList"));

    }

    @Test
    void searchOwnerReturnOne() throws Exception {
        //arrange
        List<Owner> owners = new ArrayList<>();
        Owner owner = Owner.builder().firstName("Zach").id(1L).build();
        owners.add(owner);
        when(ownerService.findAllByLastNameLikeIgnoreCase(anyString())).thenReturn(owners);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

    }

    @Test
    void searchOwnerWithNullValueShouldReturnAllOwners() throws Exception {
        //arrange
        Owner owner = Owner.builder().id(1L).build();
        Owner owner2 = Owner.builder().id(2L).build();
        Owner owner3 = Owner.builder().id(3L).build();
        List<Owner> owners = new ArrayList<>();
        owners.add(owner);
        owners.add(owner2);
        owners.add(owner3);
        when(ownerService.findAllByLastNameLikeIgnoreCase(anyString())).thenReturn(owners);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/owners").requestAttr("lastName", nullValue()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("selections", hasSize(3)))
                .andExpect(view().name("owners/ownersList"));
    }

    @Test
    void showOwnerDetails() throws Exception {
        //arrange
        Owner owner = Owner.builder().id(1L).firstName("Zach").build();
        when(ownerService.findById(anyLong())).thenReturn(owner);

        //act and assert
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));

        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void processCreationForm() throws Exception {
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }

    @Test
    void initUpdateOwnerForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(MockMvcRequestBuilders.post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService).save(ArgumentMatchers.any());
    }




}