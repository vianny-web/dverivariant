package com.vianny.dverivariant.services.products.doors;

import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.doors.EntranceDoor;
import com.vianny.dverivariant.repositories.products.doors.EntranceDoorRepository;
import com.vianny.dverivariant.services.products.AdminCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntranceDoorService implements AdminCapabilities<EntranceDoor> {
    private EntranceDoorRepository entranceDoorRepository;
    @Autowired
    public void setEntranceDoorRepository(EntranceDoorRepository entranceDoorRepository) {
        this.entranceDoorRepository = entranceDoorRepository;
    }

    @Override
    public void addProduct(EntranceDoor entranceDoor) {
        entranceDoorRepository.save(new EntranceDoor(
                entranceDoor.getName(),
                entranceDoor.getDescription(),
                entranceDoor.getPrice(),
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
