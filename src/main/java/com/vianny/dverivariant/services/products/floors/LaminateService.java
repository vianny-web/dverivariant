package com.vianny.dverivariant.services.products.floors;

import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.doors.EntranceDoor;
import com.vianny.dverivariant.models.products.floors.Laminate;
import com.vianny.dverivariant.repositories.products.ProductsRepository;
import com.vianny.dverivariant.services.products.AdminCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LaminateService implements AdminCapabilities<Laminate> {
    private ProductsRepository<Laminate> laminateRepository;
    @Autowired
    public void setLaminateRepository(ProductsRepository<Laminate> laminateRepository) {
        this.laminateRepository = laminateRepository;
    }

    @Override
    public void addProduct(Laminate laminate, String urlImage) {
        laminateRepository.save(new Laminate(
                laminate.getName(),
                laminate.getDescription(),
                laminate.getPrice(),
                urlImage,
                laminate.getIdImage(),
                laminate.getClassType(),
                laminate.getThickness(),
                laminate.getWaterResistance(),
                laminate.getBevel(),
                laminate.getCountryOfOrigin()));
    }

    @Override
    public void updateProduct(Laminate laminate) {
        laminateRepository.save(laminate);
    }

    @Override
    public void deleteProduct(String id) {
        laminateRepository.deleteById(id);
    }

    @Override
    public Optional<Laminate> findProductByID(String id) {
        return Optional.ofNullable(laminateRepository.findById(id).orElseThrow(() -> new NotFoundRequiredException(HttpStatus.NOT_FOUND, "Товар не найден")));
    }
}
