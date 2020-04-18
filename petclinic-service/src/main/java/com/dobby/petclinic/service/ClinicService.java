package com.dobby.petclinic.service;

import com.dobby.petclinic.domain.*;
import java.util.List;
import java.util.Set;

/**
 * @author liguoqing
 * @date 2019-01-04
 */
public interface ClinicService {

    List<Pet> findAllPets();

    List<PetType> findPetTypes();

    Owner findOwnerById(int id);

    Pet findPetById(int id);

    Set<Pet> findPetByOwnerId(int ownerId);

    void saveOrUpdatePet(Pet pet);

    void saveOrUpdateVisit(Visit visit);

    List<Vet> findAllVets();

    void saveOrUpdateOwner(Owner owner);

    List<Owner> findOwnerByLastName(String lastName);

    List<Visit> findVisitsByPetId(int petId);

    List<Owner> findAllOwners();

}
