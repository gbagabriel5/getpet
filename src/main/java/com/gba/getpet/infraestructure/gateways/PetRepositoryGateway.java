package com.gba.getpet.infraestructure.gateways;

import com.gba.getpet.application.gateways.PetGateway;
import com.gba.getpet.domain.entity.Pet;
import com.gba.getpet.infraestructure.persistence.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import static com.gba.getpet.infraestructure.gateways.PetEntityConverter.convert;

@Component
@RequiredArgsConstructor
public class PetRepositoryGateway implements PetGateway {

    private final PetRepository petRepository;

    @Override
    public Pet saveOrUpdatePet(Pet pet) {
        return convert(petRepository.save(convert(pet)));
    }

    @Override
    public Optional<Pet> findPetByName(String name) {
        return petRepository.findByName(name).map(PetEntityConverter::convert);
    }

    @Override
    public Optional<Pet> findPetById(Long id) {
        return petRepository.findById(id).map(PetEntityConverter::convert);
    }

    @Override
    public List<Pet> findAll() {
        return petRepository.findAll().stream().map(PetEntityConverter::convert).toList();
    }
}