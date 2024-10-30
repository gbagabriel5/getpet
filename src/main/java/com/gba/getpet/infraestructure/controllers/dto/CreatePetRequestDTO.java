package com.gba.getpet.infraestructure.controllers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public record CreatePetRequestDTO(
        String name,
        String description,
        String imageUrl,
        String category,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDate,
        String status
) {}