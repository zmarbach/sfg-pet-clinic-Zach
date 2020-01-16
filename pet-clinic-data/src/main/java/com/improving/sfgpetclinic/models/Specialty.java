package com.improving.sfgpetclinic.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//lombok annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="specialties")
public class Specialty extends BaseEntity {

    @Column(name="description")
    private String description;

}
