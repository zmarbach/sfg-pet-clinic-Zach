package com.improving.sfgpetclinic.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="pets")
public class Pet extends BaseEntity{


    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name="birth_date")
    //this will convert String to LocalDate, but only impacts this birthDate property (unlike initBinder on Visit Controller that impacts any LocalDate property coming through visit controller)
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    @Builder
    public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDate = birthDate;
        if(visits == null || visits.size() > 0){
            this.visits = visits;
        }

    }
}
