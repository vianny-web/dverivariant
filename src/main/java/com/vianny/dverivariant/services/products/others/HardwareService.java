package com.vianny.dverivariant.services.products.others;

import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.others.Hardware;
import com.vianny.dverivariant.repositories.products.others.HardwareRepository;
import com.vianny.dverivariant.services.products.AdminCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HardwareService implements AdminCapabilities<Hardware> {
    private HardwareRepository hardwareRepository;
    @Autowired
    public void setHardwareRepository(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public void addProduct(Hardware hardware, String urlImage) {
        hardwareRepository.save(new Hardware(
                hardware.getName(),
                hardware.getDescription(),
                hardware.getPrice(),
                hardware.getHardwareType()
        ));
    }

    @Override
    public void updateProduct(Hardware hardware) {
        hardwareRepository.save(hardware);
    }

    @Override
    public void deleteProduct(String id) {
        hardwareRepository.deleteById(id);
    }

    @Override
    public Optional<Hardware> findProductByID(String id) {
        return Optional.ofNullable(hardwareRepository.findById(id).orElseThrow(() -> new NotFoundRequiredException(HttpStatus.NOT_FOUND, "Товар не найден")));
    }
}
