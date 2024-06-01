package com.vianny.dverivariant.services.products.others;

import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import com.vianny.dverivariant.dto.response.product.ProductDetailsDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.floors.Laminate;
import com.vianny.dverivariant.models.products.others.Hardware;
import com.vianny.dverivariant.repositories.products.others.HardwareRepository;
import com.vianny.dverivariant.services.minio.MinioService;
import com.vianny.dverivariant.services.products.AdminCapabilitiesService;
import com.vianny.dverivariant.services.products.ProductRetrievalService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HardwareService implements AdminCapabilitiesService<Hardware>, ProductRetrievalService<Hardware> {
    private HardwareRepository hardwareRepository;
    private MinioService minioService;

    @Autowired
    public void setHardwareRepository(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }
    @Autowired
    public void setMinioService(MinioService minioService) {
        this.minioService = minioService;
    }

    @Override
    public void addProduct(Hardware hardware) {
        hardwareRepository.save(hardware);
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

    @Override
    public List<ProductBriefDTO> getAllProductsByType(TypeProducts type) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<Hardware> hardwareList = hardwareRepository.findByType(type);
        List<ProductBriefDTO> productDetailsDTOList = new ArrayList<>();

        for (Hardware hardware : hardwareList) {
            ProductBriefDTO productBriefDTO = new ProductBriefDTO(hardware.getId(), hardware.getName(),
                    hardware.getPrice(), minioService.createUrlImage(hardware.getPathImage()), type);

            productDetailsDTOList.add(productBriefDTO);
        }

        return productDetailsDTOList;
    }

    @Override
    public ProductDetailsDTO getProductById(String id) {
        return null;
    }
}
