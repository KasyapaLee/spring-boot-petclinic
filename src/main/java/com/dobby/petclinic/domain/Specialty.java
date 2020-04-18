package com.dobby.petclinic.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "specialties")
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}