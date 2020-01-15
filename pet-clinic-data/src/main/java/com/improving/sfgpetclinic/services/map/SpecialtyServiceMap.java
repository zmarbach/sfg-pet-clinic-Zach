package com.improving.sfgpetclinic.services.map;

import com.improving.sfgpetclinic.models.Specialty;
import com.improving.sfgpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class SpecialtyServiceMap extends AbstractMapService<Specialty, Long> implements SpecialtyService {
    @Override
    public Set<Specialty> findAll() {
        return super.findAll();
    }

    @Override
    public Specialty findById (Long id) {
        return super.findById(id);
    }

    @Override
    public Specialty save(Specialty obj) {
        return super.save(obj);
    }

    @Override
    public void delete(Specialty obj) {
        super.delete(obj);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
