package com.dobby.petclinic.service.impl;

import com.dobby.petclinic.dao.mapper.*;
import com.dobby.petclinic.domain.*;
import com.dobby.petclinic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author liguoqing
 * @date 2019-04-24
 */
@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private PetMapper petMapper;
    @Autowired
    private PetTypeMapper petTypeMapper;
    @Autowired
    private OwnerMapper ownerMapper;
    @Autowired
    private SpecialtyMapper specialtyMapper;
    @Autowired
    private VetMapper vetMapper;
    @Autowired
    private VisitMapper visitMapper;



    @Override
    public List<Pet> findAllPets() {
        return petMapper.selectAll();
    }

    @Override
    public List<PetType> findPetTypes() {
        return petTypeMapper.selectAll();
    }

    @Override
    public Owner findOwnerById(int id) {
        Owner owner = ownerMapper.selectByPrimaryKey(id);
        Set<Pet> pets = this.findPetByOwnerId(owner.getId());
        for (Pet pet : pets) {
            PetType petType = petTypeMapper.selectByPrimaryKey(pet.getTypeId());
            pet.setType(petType);
            List<Visit> visits = this.findVisitsByPetId(pet.getId());
            pet.setVisits(visits);
        }
        owner.setPets(pets);
        return owner;
    }

    @Override
    public Pet findPetById(int id) {
        Pet pet = petMapper.selectByPrimaryKey(id);
        PetType petType = petTypeMapper.selectByPrimaryKey(pet.getTypeId());
        pet.setType(petType);
        Owner owner = ownerMapper.selectByPrimaryKey(pet.getOwnerId());
        pet.setOwner(owner);
        List<Visit> visits = visitMapper.findVisitsByPetId(pet.getId());
        pet.setVisits(visits);
        return pet;
    }

    @Override
    public Set<Pet> findPetByOwnerId(int ownerId) {
        return petMapper.selectByOwnerId(ownerId);
    }

    @Override
    public void saveOrUpdatePet(Pet pet) {
        if (pet.getId() == null) {
            petMapper.insertSelective(pet);
        } else {
            petMapper.updateByPrimaryKeySelective(pet);
        }
    }

    @Override
    public void saveOrUpdateVisit(Visit visit) {
        if (visit.getId() == null) {
            visitMapper.insertSelective(visit);
        } else {
            visitMapper.updateByPrimaryKeySelective(visit);
        }
    }

    @Override
    public List<Vet> findAllVets() {
        List<Vet> vets = vetMapper.selectAll();
        for (Vet vet : vets) {
            List<Specialty> specialties = specialtyMapper.findByVetId(vet.getId());
            vet.setSpecialties(new HashSet<>(specialties));
        }
        return vets;
    }

    @Override
    public void saveOrUpdateOwner(Owner owner) {
        if (owner.getId() == null) {
            ownerMapper.insertSelective(owner);
        } else {
            ownerMapper.updateByPrimaryKeySelective(owner);
        }
    }

    @Override
    public List<Owner> findOwnerByLastName(String lastName) {
        lastName = lastName.trim();
        return ownerMapper.findOwnerByLastName(lastName);
    }

    @Override
    public List<Visit> findVisitsByPetId(int petId) {
        return visitMapper.findVisitsByPetId(petId);
    }

    @Override
    public List<Owner> findAllOwners() {
        return ownerMapper.selectAll();
    }


    @Override
    public void deleteOwner(Owner owner) {
        ownerMapper.delete(owner);
    }
}
