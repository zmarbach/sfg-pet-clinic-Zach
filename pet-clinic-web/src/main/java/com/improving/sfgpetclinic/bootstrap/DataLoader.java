package com.improving.sfgpetclinic.bootstrap;

import com.improving.sfgpetclinic.models.*;
import com.improving.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Component
//since it implements CommandLineRunner...Spring will automatically come in and execute the run method once the entire app is built
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    //Spring will auto-execute this method once entire app is built
    @Override
    @Transactional
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0){
            loadData();
        }
    }

    private void loadData() {
        PetType dogType = new PetType();
        dogType.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dogType);

        PetType catType = new PetType();
        catType.setName("Cat");
        PetType savedCatPetType = petTypeService.save(catType);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Ross");
        owner1.setLastName("Geller");
        owner1.setAddress("123 Fake Street");
        owner1.setAddress("Fake City");
        owner1.setTelephone("1234567890");



        Pet rossPet = new Pet();
        rossPet.setName("Doggo");
        rossPet.setOwner(owner1);
        rossPet.setBirthDate(LocalDate.now());
        rossPet.setPetType(savedDogPetType);
        owner1.getPets().add(rossPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Rachel");
        owner2.setLastName("Green");
        owner2.setAddress("123 Not Real Ave");
        owner2.setAddress("New York City");
        owner2.setTelephone("1234567890");

        Pet rachelPet = new Pet();
        rachelPet.setName("Ugly Cat");
        rachelPet.setOwner(owner2);
        rachelPet.setBirthDate(LocalDate.now());
        rachelPet.setPetType(savedCatPetType);
        owner2.getPets().add(rachelPet);

        ownerService.save(owner2);

        Visit uglyCatVisit = new Visit();
        uglyCatVisit.setDate(LocalDate.now());
        uglyCatVisit.setDescription("first visit");
        uglyCatVisit.setPet(rachelPet);

        visitService.save(uglyCatVisit);

        System.out.println("Loaded Owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Chandler");
        vet1.setLastName("Bing");
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Joey");
        vet2.setLastName("Tribiani");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets!");
    }
}
