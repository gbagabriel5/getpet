package com.gba.getpet.application.usecases;

import com.gba.getpet.application.gateways.PetGateway;
import com.gba.getpet.domain.entity.Pet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindPetsInteractor {

    private final PetGateway petGateway;

    public List<Pet> findAllPets() {
        List<Pet> pets = petGateway.findAll();
        getUpdatedAge(pets);
        return pets;
    }

    private static void getUpdatedAge(List<Pet> pets) {
        pets.forEach(pet-> pet.setAge(pet.getCurrentAge()));
    }
}