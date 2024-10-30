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
class UpdatePetStatusInteractorTest {

    UpdatePetStatusInteractor updatePetStatusInteractor;

    PetGateway petGateway;

    @Autowired
    PetRepository petRepository;

    @BeforeEach
    void setUp() {
        petGateway = new PetRepositoryGateway(petRepository);
        updatePetStatusInteractor = new UpdatePetStatusInteractor(petGateway);
    }

    @Test
    void shouldUpdatePetStatus() {
        Pet petToCreate = Pet.builder()
                .name("Bruce Wayne")
                .description("Lhasa Apso branco e preto")
                .imageUrl("teste.jpg")
                .category("Cachorro")
                .birthDate(LocalDate.of(2022, 8, 31))
                .status("Disponivel")
                .build();
        String statusToUpdate = "Adotado";

        Pet petCreated = petGateway.saveOrUpdatePet(petToCreate);

        Pet petUpdated = updatePetStatusInteractor.updatePetStatus(petCreated.getId(), statusToUpdate);

        assertThat(petUpdated.getId()).isEqualTo(petCreated.getId());
        assertThat(petUpdated.getName()).isEqualTo(petCreated.getName());
        assertThat(petUpdated.getDescription()).isEqualTo(petCreated.getDescription());
        assertThat(petUpdated.getImageUrl()).isEqualTo(petCreated.getImageUrl());
        assertThat(petUpdated.getCategory()).isEqualTo(petCreated.getCategory());
        assertThat(petUpdated.getStatus()).isEqualTo(statusToUpdate);
        assertThat(petUpdated.getBirthDate()).isEqualTo(petCreated.getBirthDate());
    }

    @Test
    void shouldCatchErrorBecausePetDoesNotFound() {
        assertThatThrownBy(()-> updatePetStatusInteractor.updatePetStatus(1L, "teste"))
                .hasMessage("Pet não encontrado!");
    }
}