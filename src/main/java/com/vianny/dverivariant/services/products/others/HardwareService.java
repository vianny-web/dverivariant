package com.vianny.dverivariant.services.products.others;

import com.vianny.dverivariant.models.products.others.Hardware;
import com.vianny.dverivariant.repositories.products.ProductsRepository;
import com.vianny.dverivariant.services.products.AdminCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HardwareService implements AdminCapabilities<Hardware> {
    private ProductsRepository<Hardware> hardwareRepository;
    @Autowired
    public void setHardwareRepository(ProductsRepository<Hardware> hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public void addProduct(Hardware product, String urlImage) {

    }

    @Override
    public void updateProduct(Hardware productNew) {

    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public Optional<Hardware> findProductByID(String id) {
        return Optional.empty();
    }
}
