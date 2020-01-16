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
@ToString

@Entity
@Table(name="types")
public class PetType extends BaseEntity {

    @Column(name="name")
    private String name;


}
