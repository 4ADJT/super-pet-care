package br.com.superpetcare.superpetcare.controller;

import br.com.superpetcare.superpetcare.domain.dto.PetDTO;
import br.com.superpetcare.superpetcare.domain.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/pets")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Pet API")
public class PetController {

    @Autowired
    private PetService petService;

    @Operation(summary = "Busca todos os serviços dos pets", description = "Método responsável por buscar todos os serviços para os pets.")
    @GetMapping
    public ResponseEntity<Collection<PetDTO>> findAll() {
        var pets = petService.findAll();
        return ResponseEntity.ok(pets);
    }

    @Operation(summary = "Busca pelo id um serviço especifico para o pet", description = "Método responsável por buscar um serviços pelo Id.")
    @GetMapping("/id/{id}")
    public ResponseEntity<PetDTO> findById(@PathVariable UUID id){
        var pet = petService.findById(id);
        return ResponseEntity.ok(pet);
    }

    @GetMapping("/service/{serviceDescription}")
    public ResponseEntity<Collection<PetDTO>> findByServiceDescription(@PathVariable String serviceDescription){
        var pet = petService.findByServiceDescription(serviceDescription);
        return ResponseEntity.ok(pet);
    }
    @GetMapping("/breed/{breedName}")
    public ResponseEntity<Collection<PetDTO>> findByBreedName(@PathVariable String breedName){
        var pet = petService.findByBreedName(breedName);
        return ResponseEntity.ok(pet);
    }

    @PostMapping
    public ResponseEntity<PetDTO> save(@RequestBody PetDTO petDTO){
        petDTO = petService.save(petDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(petDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> update(
            @PathVariable UUID id, @RequestBody PetDTO updatedPetDTO)
    {
        var petDTO = petService.update(id, updatedPetDTO);
        return ResponseEntity.ok(petDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
