package com.vianny.dverivariant.services.products.floors;

import com.vianny.dverivariant.dto.response.product.ProductDetailsDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.floors.Quartzvinyl;
import com.vianny.dverivariant.repositories.products.floors.QuartzvinylRepository;
import com.vianny.dverivariant.services.minio.MinioService;
import com.vianny.dverivariant.services.products.AdminCapabilitiesService;
import com.vianny.dverivariant.services.products.ProductRetrievalService;
import com.vianny.dverivariant.utils.product.ProductDetailsHelper;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class QuartzvinylService implements AdminCapabilitiesService<Quartzvinyl>, ProductRetrievalService<Quartzvinyl> {
    private QuartzvinylRepository quartzvinylRepository;
    private ProductDetailsHelper productDetailsHelper;

    @Autowired
    public void setQuartzvinylRepository(QuartzvinylRepository quartzvinylRepository) {
        this.quartzvinylRepository = quartzvinylRepository;
    }
    @Autowired
    public void setProductDetailsHelper(ProductDetailsHelper productDetailsHelper) {
        this.productDetailsHelper = productDetailsHelper;
    }

    @Override
    public void addProduct(Quartzvinyl quartzvinyl) {
        quartzvinylRepository.save(quartzvinyl);
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

    @Override
    public List<ProductDetailsDTO> getAllProductsByType(TypeProducts type) {
        List<Quartzvinyl> quartzvinylList = quartzvinylRepository.findByType(type);
            List<ProductDetailsDTO> productDetailsDTOList = new ArrayList<>();

        for (Quartzvinyl quartzvinyl : quartzvinylList) {
            HashMap<String, String> details = productDetailsHelper.getDetailsQuartzvinyl(Optional.ofNullable(quartzvinyl));

            assert quartzvinyl != null;
            ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO(quartzvinyl.getId(), quartzvinyl.getName(), quartzvinyl.getDescription(),
                    quartzvinyl.getPrice(), quartzvinyl.getPathImage(), details);

            productDetailsDTOList.add(productDetailsDTO);
        }

        return productDetailsDTOList;
    }

    @Override
    public ProductDetailsDTO getProductById(String id) {
        return null;
    }
}
