package com.improving.sfgpetclinic.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

//lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

@Entity
@Table(name="visits")
public class Visit extends BaseEntity {

    @Column(name="date")
    private LocalDate date;

    @Column(name="description")
    private String description;

    @ManyToOne
    @JoinColumn(name="pet_id")
    private Pet pet;

}
