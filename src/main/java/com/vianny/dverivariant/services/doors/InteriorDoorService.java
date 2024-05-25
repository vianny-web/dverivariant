package com.vianny.dverivariant.services.doors;

import com.vianny.dverivariant.models.InteriorDoor;
import com.vianny.dverivariant.repositories.InteriorDoorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteriorDoorService {
    private InteriorDoorRepository interiorDoorRepository;
    @Autowired
    public void setInteriorDoorRepository(InteriorDoorRepository interiorDoorRepository) {
        this.interiorDoorRepository = interiorDoorRepository;
    }

    public void addInteriorDoor(InteriorDoor interiorDoor) {
        interiorDoorRepository.save(new InteriorDoor(
                interiorDoor.getName(),
                interiorDoor.getDescription(),
                interiorDoor.getPrice(),
                interiorDoor.getUrlImage(),
                interiorDoor.getMaterial(),
                interiorDoor.getGlazing(),
                interiorDoor.getModification(),
                interiorDoor.getConstruction(),
                interiorDoor.getManufacturer()));
    }
}
