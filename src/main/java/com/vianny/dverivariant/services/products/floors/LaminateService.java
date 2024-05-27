package com.vianny.dverivariant.services.products.floors;

import com.vianny.dverivariant.models.products.floors.Laminate;
import com.vianny.dverivariant.repositories.products.ProductsRepository;
import com.vianny.dverivariant.services.products.AdminCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addProduct(Laminate product, String urlImage) {

    }

    @Override
    public void updateProduct(Laminate productNew) {

    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public Optional<Laminate> findProductByID(String id) {
        return Optional.empty();
    }
}
