package com.vianny.dverivariant.services.doors;

import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.doors.InteriorDoor;
import com.vianny.dverivariant.repositories.InteriorDoorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InteriorDoorService {
    private InteriorDoorRepository interiorDoorRepository;
    @Autowired
    public void setInteriorDoorRepository(InteriorDoorRepository interiorDoorRepository) {
        this.interiorDoorRepository = interiorDoorRepository;
    }

    public void addInteriorDoor(InteriorDoor interiorDoor, String urlImage) {
        interiorDoorRepository.save(new InteriorDoor(
                interiorDoor.getName(),
                interiorDoor.getDescription(),
                interiorDoor.getPrice(),
                urlImage,
                interiorDoor.getIdImage(),
                interiorDoor.getMaterial(),
                interiorDoor.getGlazing(),
                interiorDoor.getModification(),
                interiorDoor.getConstruction(),
                interiorDoor.getManufacturer()));
    }

    public void updateInteriorDoor(InteriorDoor interiorDoorNew) {
        interiorDoorRepository.save(interiorDoorNew);
    }

    public void deleteInteriorDoor(String id) {
        interiorDoorRepository.deleteById(id);
    }

    public Optional<InteriorDoor> findInteriorDoorByID(String id) {
        return Optional.ofNullable(interiorDoorRepository.findById(id).orElseThrow(() -> new NotFoundRequiredException(HttpStatus.NOT_FOUND, "Товар не найден")));
    }
}
