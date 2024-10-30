package com.gba.getpet.application.gateways;

import com.gba.getpet.domain.entity.Pet;
import java.util.List;
import java.util.Optional;

public interface PetGateway {
    Pet saveOrUpdatePet(Pet pet);
    Optional<Pet> findPetByName(String name);
    Optional<Pet> findPetById(Long id);
    List<Pet> findAll();
}