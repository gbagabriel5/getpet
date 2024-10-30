package com.gba.getpet.infraestructure.controllers;

import com.gba.getpet.infraestructure.controllers.dto.CreatePetRequestDTO;
import com.gba.getpet.infraestructure.controllers.dto.PetResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("pet")
public interface PetController {
    @PostMapping
    @Operation(description = "Cria novo PET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Criado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    ResponseEntity<PetResponseDTO> createPet(@RequestBody CreatePetRequestDTO requestDTO);

    @PutMapping("/id/{id}/status/{status}")
    @Operation(description = "Atualiza status de adoção do PET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Erro ao não encontrar PET cadastrado!")
    })
    ResponseEntity<PetResponseDTO> updatePetStatus(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "status") String status
    );

    @GetMapping
    @PutMapping("/id/{id}/status/{status}")
    @Operation(description = "Retorna lista de PETS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso!"),
    })
    ResponseEntity<List<PetResponseDTO>> findAllPets();
}