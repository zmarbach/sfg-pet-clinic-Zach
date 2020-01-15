package com.improving.sfgpetclinic.services.map;

import com.improving.sfgpetclinic.models.Specialty;
import com.improving.sfgpetclinic.models.Vet;
import com.improving.sfgpetclinic.services.SpecialtyService;
import com.improving.sfgpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

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
        //defensive coding...in case for some reason we get a bad Vet object where the specialty does NOT have an ID
        if(obj.getSpecialties().size() > 0){
            obj.getSpecialties().forEach(spec -> {
                if(spec.getId() == null){
                    Specialty savedSpecialty = specialtyService.save(spec);
                    spec.setId(savedSpecialty.getId());
                }
            });
        }
        return super.save(obj);
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
