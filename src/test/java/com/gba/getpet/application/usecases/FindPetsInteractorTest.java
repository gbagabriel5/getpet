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
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class FindPetsInteractorTest {

    FindPetsInteractor findPetsInteractor;

    CreatePetInteractor createPetInteractor;

    PetGateway petGateway;

    @Autowired
    PetRepository petRepository;

    @BeforeEach
    void setUp() {
        petGateway = new PetRepositoryGateway(petRepository);
        createPetInteractor = new CreatePetInteractor(petGateway);
        findPetsInteractor = new FindPetsInteractor(petGateway);
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

        Pet petToCreate2 = Pet.builder()
                .name("Garfield")
                .description("Amarelo")
                .imageUrl("teste1.jpg")
                .category("Gato")
                .birthDate(LocalDate.of(2002, 10, 31))
                .status("Adotado")
                .build();


        Pet petCreated = createPetInteractor.createPet(petToCreate);
        Pet petCreated2 = createPetInteractor.createPet(petToCreate2);

        List<Pet> petsReturned = findPetsInteractor.findAllPets();

        assertThat(petsReturned.size()).isEqualTo(2L);
        assertThat(petsReturned.getFirst().getName()).isEqualTo(petCreated.getName());
        assertThat(petsReturned.getFirst().getDescription()).isEqualTo(petToCreate.getDescription());
        assertThat(petsReturned.getFirst().getImageUrl()).isEqualTo(petToCreate.getImageUrl());
        assertThat(petsReturned.getFirst().getCategory()).isEqualTo(petToCreate.getCategory());
        assertThat(petsReturned.getFirst().getStatus()).isEqualTo(petToCreate.getStatus());
        assertThat(petsReturned.getFirst().getBirthDate()).isEqualTo(petToCreate.getBirthDate());
        assertThat(petsReturned.getFirst().getAge()).isEqualTo(petToCreate.getCurrentAge());
        assertThat(petsReturned.get(1).getName()).isEqualTo(petCreated2.getName());
        assertThat(petsReturned.get(1).getDescription()).isEqualTo(petToCreate2.getDescription());
        assertThat(petsReturned.get(1).getImageUrl()).isEqualTo(petToCreate2.getImageUrl());
        assertThat(petsReturned.get(1).getCategory()).isEqualTo(petToCreate2.getCategory());
        assertThat(petsReturned.get(1).getStatus()).isEqualTo(petToCreate2.getStatus());
        assertThat(petsReturned.get(1).getBirthDate()).isEqualTo(petToCreate2.getBirthDate());
        assertThat(petsReturned.get(1).getAge()).isEqualTo(petToCreate2.getCurrentAge());
    }
}