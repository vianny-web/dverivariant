package com.vianny.dverivariant.services.products.doors;

import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.doors.EntranceDoor;
import com.vianny.dverivariant.models.products.doors.InteriorDoor;
import com.vianny.dverivariant.repositories.products.ProductsRepository;
import com.vianny.dverivariant.services.products.AdminCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntranceDoorService implements AdminCapabilities<EntranceDoor> {
    private ProductsRepository<EntranceDoor> entranceDoorRepository;
    @Autowired
    public void setEntranceDoorRepository(ProductsRepository<EntranceDoor> entranceDoorRepository) {
        this.entranceDoorRepository = entranceDoorRepository;
    }

    @Override
    public void addProduct(EntranceDoor entranceDoor, String urlImage) {
        entranceDoorRepository.save(new EntranceDoor(
                entranceDoor.getName(),
                entranceDoor.getDescription(),
                entranceDoor.getPrice(),
                urlImage,
                entranceDoor.getIdImage(),
                entranceDoor.getInstallationPlace(),
                entranceDoor.getGlazing(),
                entranceDoor.getAdditionalProperties()));
    }

    @Override
    public void updateProduct(EntranceDoor entranceDoor) {
        entranceDoorRepository.save(entranceDoor);
    }

    @Override
    public void deleteProduct(String id) {
        entranceDoorRepository.deleteById(id);
    }

    @Override
    public Optional<EntranceDoor> findProductByID(String id) {
        return Optional.ofNullable(entranceDoorRepository.findById(id).orElseThrow(() -> new NotFoundRequiredException(HttpStatus.NOT_FOUND, "Товар не найден")));
    }
}
