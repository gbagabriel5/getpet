package com.gba.getpet.application.usecases;

import com.gba.getpet.application.gateways.PetGateway;
import com.gba.getpet.domain.entity.Pet;
import com.gba.getpet.domain.shared.exceptions.PetNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdatePetStatusInteractor {

    private static final String PET_NOT_FOUND = "Pet não encontrado!";

    private final PetGateway petGateway;

    public Pet updatePetStatus(Long id, String status) {
        Optional<Pet> petSavedOpt = petGateway.findPetById(id);

        if (petSavedOpt.isEmpty()) {
            throw new PetNotFoundException(PET_NOT_FOUND);
        }

        Pet petToUpdateStatus = petSavedOpt.get().toBuilder().status(status).build();

        return petGateway.saveOrUpdatePet(petToUpdateStatus);
    }
}
