package com.gba.getpet.application.usecases;

import com.gba.getpet.application.gateways.PetGateway;
import com.gba.getpet.domain.entity.Pet;
import com.gba.getpet.infraestructure.gateways.PetRepositoryGateway;
import com.gba.getpet.infraestructure.persistence.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class CreatePetInteractorTest {

    CreatePetInteractor createPetInteractor;

    PetGateway petGateway;

    @Autowired
    PetRepository petRepository;

    @BeforeEach
    void setUp() {
        petGateway = new PetRepositoryGateway(petRepository);
        createPetInteractor = new CreatePetInteractor(petGateway);
    }

    @Test
    void shouldCreatePet() {
        Pet petToCreate = Pet.builder()
                .name("Bruce Wayne")
                .description("Lhasa Apso branco e preto")
                .imageUrl("teste.jpg")
                .category("Cachorro")
                .birthDate(LocalDate.of(2022, 8, 31))
                .status("Disponivel")
                .build();

        Pet petCreated = createPetInteractor.createPet(petToCreate);

        assertThat(petCreated.getName()).isEqualTo(petToCreate.getName());
        assertThat(petCreated.getDescription()).isEqualTo(petToCreate.getDescription());
        assertThat(petCreated.getImageUrl()).isEqualTo(petToCreate.getImageUrl());
        assertThat(petCreated.getCategory()).isEqualTo(petToCreate.getCategory());
        assertThat(petCreated.getStatus()).isEqualTo(petToCreate.getStatus());
        assertThat(petCreated.getBirthDate()).isEqualTo(petToCreate.getBirthDate());
        assertThat(petCreated.getAge()).isEqualTo(petToCreate.getCurrentAge());
    }

    @Test
    void shouldCatchErrorBecausePetAlreadyExists() {
        Pet petToCreate = Pet.builder()
                 .name("Bruce Wayne")
                .description("Lhasa Apso branco e preto")
                .imageUrl("teste.jpg")
                .category("Cachorro")
                .birthDate(LocalDate.of(2022, 8, 31))
                .status("Disponivel")
                .build();

        createPetInteractor.createPet(petToCreate);

        assertThatThrownBy(()-> createPetInteractor.createPet(petToCreate))
                .hasMessage("Pet já cadastrado!");
    }
}