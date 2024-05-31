package com.vianny.dverivariant.services.products.floors;

import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.floors.Quartzvinyl;
import com.vianny.dverivariant.repositories.products.floors.QuartzvinylRepository;
import com.vianny.dverivariant.services.products.AdminCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuartzvinylService implements AdminCapabilities<Quartzvinyl> {
    private QuartzvinylRepository quartzvinylRepository;
    @Autowired
    public void setQuartzvinylRepository(QuartzvinylRepository quartzvinylRepository) {
        this.quartzvinylRepository = quartzvinylRepository;
    }

    @Override
    public void addProduct(Quartzvinyl quartzvinyl) {
        quartzvinylRepository.save(new Quartzvinyl(
                quartzvinyl.getName(),
                quartzvinyl.getDescription(),
                quartzvinyl.getPrice(),
                quartzvinyl.getBase(),
                quartzvinyl.getInstallationType(),
                quartzvinyl.getBevel(),
                quartzvinyl.getManufacturer()));
    }

    @Override
    public void updateProduct(Quartzvinyl quartzvinyl) {
        quartzvinylRepository.save(quartzvinyl);
    }

    @Override
    public void deleteProduct(String id) {
        quartzvinylRepository.deleteById(id);
    }

    @Override
    public Optional<Quartzvinyl> findProductByID(String id) {
        return Optional.ofNullable(quartzvinylRepository.findById(id).orElseThrow(() -> new NotFoundRequiredException(HttpStatus.NOT_FOUND, "Товар не найден")));
    }
}
