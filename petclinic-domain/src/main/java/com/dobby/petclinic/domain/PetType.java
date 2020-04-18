package com.dobby.petclinic.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "types")
public class PetType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return this.getName();
    }

}