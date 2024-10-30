package com.gba.getpet.application.usecases;

import com.gba.getpet.application.gateways.PetGateway;
import com.gba.getpet.domain.entity.Pet;
import com.gba.getpet.domain.shared.exceptions.PetAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreatePetInteractor {

    private static final String PET_ALREADY_EXIST = "Pet já cadastrado!";

    private final PetGateway petGateway;

    public Pet createPet(Pet pet) {
        Optional<Pet> petSavedOpt = petGateway.findPetByName(pet.getName());

        if (petSavedOpt.isPresent()) {
            throw new PetAlreadyExistsException(PET_ALREADY_EXIST);
        }

        pet.setAge(pet.getCurrentAge());

        return petGateway.saveOrUpdatePet(pet);
    }
}