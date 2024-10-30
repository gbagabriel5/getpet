package com.gba.getpet.infraestructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PetRepository extends JpaRepository<PetEntity, Long> {
    Optional<PetEntity> findByName(String name);
}