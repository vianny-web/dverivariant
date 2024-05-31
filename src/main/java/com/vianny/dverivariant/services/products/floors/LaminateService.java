package com.vianny.dverivariant.services.products.floors;

import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import com.vianny.dverivariant.dto.response.product.ProductDetailsDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.doors.InteriorDoor;
import com.vianny.dverivariant.models.products.floors.Laminate;
import com.vianny.dverivariant.repositories.products.floors.LaminateRepository;
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
public class LaminateService implements AdminCapabilitiesService<Laminate>, ProductRetrievalService<Laminate> {
    private LaminateRepository laminateRepository;
    private MinioService minioService;

    @Autowired
    public void setLaminateRepository(LaminateRepository laminateRepository) {
        this.laminateRepository = laminateRepository;
    }
    @Autowired
    public void setMinioService(MinioService minioService) {
        this.minioService = minioService;
    }

    @Override
    public void addProduct(Laminate laminate) {
        laminateRepository.save(new Laminate(
                laminate.getName(),
                laminate.getDescription(),
                laminate.getPrice(),
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

    @Override
    public List<ProductBriefDTO> getAllProductsByType(TypeProducts type) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<Laminate> laminateList = laminateRepository.findByType(type);
        List<ProductBriefDTO> productDetailsDTOList = new ArrayList<>();

        for (Laminate laminate : laminateList) {
            ProductBriefDTO productBriefDTO = new ProductBriefDTO(laminate.getId(), laminate.getName(),
                    laminate.getPrice(), minioService.createUrlImage(laminate.getPathImage()), type);

            productDetailsDTOList.add(productBriefDTO);
        }

        return productDetailsDTOList;
    }

    @Override
    public ProductDetailsDTO getProductById(String id) {
        return null;
    }
}
