package com.dobby.petclinic.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "type_id")
    private Integer typeId;
    @Column(name = "owner_id")
    private Integer ownerId;

    //////

    private Owner owner;
    private PetType type;

    private List<Visit> visits = new ArrayList<>();

    public boolean isNew() {
        return this.id == null;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
        this.ownerId = owner.getId();
    }

    public void setType(PetType type) {
        this.type = type;
        this.typeId = type.getId();
    }

    protected List<Visit> getVisitsInternal() {
        if (this.visits == null) {
            this.visits = new ArrayList<>();
        }
        return this.visits;
    }

    public void addVisit(Visit visit) {
        getVisitsInternal().add(visit);
        visit.setPet(this);
    }


    @Override
    public String toString() {
        return this.getName();
    }

}
