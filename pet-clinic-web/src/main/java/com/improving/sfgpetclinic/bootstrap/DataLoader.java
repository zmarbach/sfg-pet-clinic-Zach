package com.improving.sfgpetclinic.bootstrap;

import com.improving.sfgpetclinic.models.Owner;
import com.improving.sfgpetclinic.models.Vet;
import com.improving.sfgpetclinic.services.OwnerService;
import com.improving.sfgpetclinic.services.VetService;
import com.improving.sfgpetclinic.services.map.OwnerServiceMap;
import com.improving.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
//since it implements CommandLineRunner...Spring will automatically come in and execute the run method once the entire app is built
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    //Spring will auto-execute this method once entire app is built
    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Ross");
        owner1.setLastName("Geller");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Rachel");
        owner2.setLastName("Green");

        ownerService.save(owner2);

        System.out.println("Loaded Owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Chandler");
        vet1.setLastName("Bing");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Joey");
        vet2.setLastName("Tribiani");

        vetService.save(vet2);

        System.out.println("Loaded Vets!");

    }
}
