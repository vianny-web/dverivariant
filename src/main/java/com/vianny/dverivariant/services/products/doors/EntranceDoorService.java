package com.vianny.dverivariant.services.products.doors;

import com.vianny.dverivariant.models.products.doors.EntranceDoor;
import com.vianny.dverivariant.repositories.products.ProductsRepository;
import com.vianny.dverivariant.services.products.AdminCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addProduct(EntranceDoor product, String urlImage) {

    }

    @Override
    public void updateProduct(EntranceDoor productNew) {

    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public Optional<EntranceDoor> findProductByID(String id) {
        return Optional.empty();
    }
}
