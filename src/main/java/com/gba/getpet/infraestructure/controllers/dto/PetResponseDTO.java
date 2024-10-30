package com.gba.getpet.infraestructure.controllers.dto;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public record PetResponseDTO(
        Long id,
        String name,
        String description,
        String imageUrl,
        String category,
        @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate birthDate,
        String age,
        String status
) {}