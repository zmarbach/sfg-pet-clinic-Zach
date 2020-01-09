package com.improving.sfgpetclinic.services.map;

import com.improving.sfgpetclinic.models.Vet;
import com.improving.sfgpetclinic.services.CrudService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements CrudService<Vet, Long> {
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet obj) {
        return super.save(obj.getId(), obj);
    }

    @Override
    public void delete(Vet obj) {
        super.delete(obj);

    }

    @Override
    public void deleteById(Long id) {
        deleteById(id);
    }
}
