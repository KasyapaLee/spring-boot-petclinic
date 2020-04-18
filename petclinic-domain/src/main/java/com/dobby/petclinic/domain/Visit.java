package com.dobby.petclinic.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "visits")
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pet_id")
    private Integer petId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "visit_date")
    private LocalDate date = LocalDate.now();

    @Column(name = "description")
    private String description;

    @Transient
    private Pet pet;

    public boolean isNew() {
        return this.id == null;
    }
}