package com.improving.sfgpetclinic.services;

import com.improving.sfgpetclinic.models.Owner;
import com.improving.sfgpetclinic.models.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);
    Vet save(Vet vet);
    Set<Vet> findAll();
}
