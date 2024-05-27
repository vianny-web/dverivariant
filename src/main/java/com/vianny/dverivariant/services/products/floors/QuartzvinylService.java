package com.vianny.dverivariant.services.products.floors;

import com.vianny.dverivariant.models.products.floors.Quartzvinyl;
import com.vianny.dverivariant.repositories.products.ProductsRepository;
import com.vianny.dverivariant.services.products.AdminCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuartzvinylService implements AdminCapabilities<Quartzvinyl> {
    private ProductsRepository<Quartzvinyl> quartzvinylRepository;
    @Autowired
    public void setQuartzvinylRepository(ProductsRepository<Quartzvinyl> quartzvinylRepository) {
        this.quartzvinylRepository = quartzvinylRepository;
    }


    @Override
    public void addProduct(Quartzvinyl product, String urlImage) {

    }

    @Override
    public void updateProduct(Quartzvinyl productNew) {

    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public Optional<Quartzvinyl> findProductByID(String id) {
        return Optional.empty();
    }
}
