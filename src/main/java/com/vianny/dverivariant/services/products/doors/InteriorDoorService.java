package com.vianny.dverivariant.services.products.doors;

import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.doors.InteriorDoor;
import com.vianny.dverivariant.repositories.ProductsRepository;
import com.vianny.dverivariant.services.products.AdminCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InteriorDoorService implements AdminCapabilities<InteriorDoor> {
    private ProductsRepository<InteriorDoor> interiorDoorRepository;
    @Autowired
    public void setInteriorDoorRepository(ProductsRepository<InteriorDoor> interiorDoorRepository) {
        this.interiorDoorRepository = interiorDoorRepository;
    }

    @Override
    public void addProduct(InteriorDoor interiorDoor, String urlImage) {
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

    @Override
    public void updateProduct(InteriorDoor interiorDoorNew) {
        interiorDoorRepository.save(interiorDoorNew);
    }

    @Override
    public void deleteProduct(String id) {
        interiorDoorRepository.deleteById(id);
    }

    @Override
    public Optional<InteriorDoor> findProductByID(String id) {
        return Optional.ofNullable(interiorDoorRepository.findById(id).orElseThrow(() -> new NotFoundRequiredException(HttpStatus.NOT_FOUND, "Товар не найден")));
    }
}
