package br.com.superpetcare.superpetcare.domain.dto;

import java.util.UUID;

public record PetDTO(
        UUID id,
        String breedName,
        String serviceDescription,
        String imageUrl,
        double serviceValue
) {
}
