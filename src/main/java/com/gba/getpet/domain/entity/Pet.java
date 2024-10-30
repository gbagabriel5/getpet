package com.gba.getpet.domain.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.Period;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String category;
    private String status;

    @Setter
    private String age;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    public String getCurrentAge() {
        var currentDate = LocalDate.now();
        var age = Period.between(birthDate, currentDate);

        int years = age.getYears();
        int months = age.getMonths();

        return String.format("%d anos e %d meses", years, months);
    }
}