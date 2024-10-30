package com.gba.getpet.infraestructure.gateways;

import com.gba.getpet.domain.entity.Pet;
import com.gba.getpet.infraestructure.persistence.PetEntity;

public class PetEntityConverter {

    private PetEntityConverter() {}

    public static Pet convert(PetEntity entity) {
        return new Pet(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getImageUrl(),
                entity.getCategory(),
                entity.getStatus(),
                entity.getAge(),
                entity.getBirthDate()
        );
    }

    public static PetEntity convert(Pet domain) {
        return new PetEntity(
                domain.getId(),
                domain.getName(),
                domain.getDescription(),
                domain.getImageUrl(),
                domain.getCategory(),
                domain.getBirthDate(),
                domain.getAge(),
                domain.getStatus()
        );
    }
}