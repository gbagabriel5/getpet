package com.gba.getpet.infraestructure.controllers.dto.converters;

import com.gba.getpet.domain.entity.Pet;
import com.gba.getpet.infraestructure.controllers.dto.CreatePetRequestDTO;
import com.gba.getpet.infraestructure.controllers.dto.PetResponseDTO;
import java.util.List;

public class PetDTOConverter {

    private PetDTOConverter() {}

    public static List<PetResponseDTO> convert(List<Pet> pets) {
        return pets.stream().map(PetDTOConverter::convert).toList();
    }

    public static PetResponseDTO convert(Pet domain) {
        return new PetResponseDTO(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getImageUrl(),
                domain.getCategory(),
                domain.getBirthDate(),
                domain.getCurrentAge(),
                domain.getStatus()
        );
    }

    public static Pet convert(CreatePetRequestDTO createPetRequestDTO) {
        return Pet.builder()
                .name(createPetRequestDTO.name())
                .description(createPetRequestDTO.description())
                .imageUrl(createPetRequestDTO.imageUrl())
                .category(createPetRequestDTO.category())
                .birthDate(createPetRequestDTO.birthDate())
                .status(createPetRequestDTO.status())
                .build();
    }
}