package com.improving.sfgpetclinic.services.map;

import com.improving.sfgpetclinic.models.Visit;
import com.improving.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {
    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit visit) {
        //need these ids and objects before saving...want to have the proper things for visit to make sense)
        if(visit.getPet() == null || visit.getPet().getOwner() == null || visit.getPet().getId() == null || visit.getPet().getOwner().getId() == null){
          throw new RuntimeException("Invalid visit. Missing data.");
        }
        else{
            return super.save(visit);
        }
    }

    @Override
    public void delete(Visit obj) {
        super.delete(obj);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
