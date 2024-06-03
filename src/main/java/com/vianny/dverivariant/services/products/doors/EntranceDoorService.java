package com.vianny.dverivariant.services.products.doors;

import com.vianny.dverivariant.dto.response.product.ProductDetailsDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.doors.EntranceDoor;
import com.vianny.dverivariant.repositories.products.doors.EntranceDoorRepository;
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
public class EntranceDoorService implements AdminCapabilitiesService<EntranceDoor>, ProductRetrievalService<EntranceDoor> {
    private EntranceDoorRepository entranceDoorRepository;
    private ProductDetailsHelper productDetailsHelper;

    @Autowired
    public void setEntranceDoorRepository(EntranceDoorRepository entranceDoorRepository) {
        this.entranceDoorRepository = entranceDoorRepository;
    }
    @Autowired
    public void setProductDetailsHelper(ProductDetailsHelper productDetailsHelper) {
        this.productDetailsHelper = productDetailsHelper;
    }

    @Override
    public void addProduct(EntranceDoor entranceDoor) {
        entranceDoorRepository.save(entranceDoor);
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

    @Override
    public List<ProductDetailsDTO> getAllProductsByType(TypeProducts type) {
        List<EntranceDoor> entranceDoorList = entranceDoorRepository.findByType(type);
        List<ProductDetailsDTO> productDetailsDTOList = new ArrayList<>();

        for (EntranceDoor entranceDoor : entranceDoorList) {
            HashMap<String, String> details = productDetailsHelper.getDetailsEntranceDoor(Optional.ofNullable(entranceDoor));

            assert entranceDoor != null;
            ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO(entranceDoor.getId(), entranceDoor.getName(), entranceDoor.getDescription(),
                    entranceDoor.getPrice(), entranceDoor.getPathImage(), details);

            productDetailsDTOList.add(productDetailsDTO);
        }

        return productDetailsDTOList;
    }

    @Override
    public ProductDetailsDTO getProductById(String id) {
        return null;
    }
}
