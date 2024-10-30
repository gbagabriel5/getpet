package com.gba.getpet.infraestructure.controllers.impl;

import com.gba.getpet.application.usecases.CreatePetInteractor;
import com.gba.getpet.application.usecases.FindPetsInteractor;
import com.gba.getpet.application.usecases.UpdatePetStatusInteractor;
import com.gba.getpet.infraestructure.controllers.PetController;
import com.gba.getpet.infraestructure.controllers.dto.CreatePetRequestDTO;
import com.gba.getpet.infraestructure.controllers.dto.PetResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static com.gba.getpet.infraestructure.controllers.dto.converters.PetDTOConverter.convert;

@RestController
@RequiredArgsConstructor
public class PetControllerImpl implements PetController {

    private final CreatePetInteractor createPetInteractor;
    private final UpdatePetStatusInteractor updatePetStatusInteractor;
    private final FindPetsInteractor findPetsInteractor;

    @Override
    public ResponseEntity<PetResponseDTO> createPet(CreatePetRequestDTO requestDTO) {
        PetResponseDTO responseDTO = convert(createPetInteractor.createPet(convert(requestDTO)));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PetResponseDTO> updatePetStatus(Long id, String status) {
        PetResponseDTO responseDTO = convert(updatePetStatusInteractor.updatePetStatus(id, status));
        return ResponseEntity.ok(responseDTO);
    }

    @Override
    public ResponseEntity<List<PetResponseDTO>> findAllPets() {
        return ResponseEntity.ok(convert(findPetsInteractor.findAllPets()));
    }
}