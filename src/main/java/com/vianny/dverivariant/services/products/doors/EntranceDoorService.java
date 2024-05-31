package com.vianny.dverivariant.services.products.doors;

import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.doors.EntranceDoor;
import com.vianny.dverivariant.models.products.doors.InteriorDoor;
import com.vianny.dverivariant.repositories.products.doors.EntranceDoorRepository;
import com.vianny.dverivariant.repositories.products.doors.InteriorDoorRepository;
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
public class EntranceDoorService implements AdminCapabilitiesService<EntranceDoor>, ProductRetrievalService<EntranceDoor> {
    private EntranceDoorRepository entranceDoorRepository;
    private MinioService minioService;

    @Autowired
    public void setEntranceDoorRepository(EntranceDoorRepository entranceDoorRepository) {
        this.entranceDoorRepository = entranceDoorRepository;
    }
    @Autowired
    public void setMinioService(MinioService minioService) {
        this.minioService = minioService;
    }

    @Override
    public void addProduct(EntranceDoor entranceDoor) {
        entranceDoorRepository.save(new EntranceDoor(
                entranceDoor.getName(),
                entranceDoor.getDescription(),
                entranceDoor.getPrice(),
                entranceDoor.getInstallationPlace(),
                entranceDoor.getGlazing(),
                entranceDoor.getAdditionalProperties()));
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
    public List<ProductBriefDTO> getAllProductsByType(TypeProducts type) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        List<EntranceDoor> entranceDoorList = entranceDoorRepository.findByType(type);
        List<ProductBriefDTO> productDetailsDTOList = new ArrayList<>();

        for (EntranceDoor entranceDoor : entranceDoorList) {
            ProductBriefDTO productBriefDTO = new ProductBriefDTO(entranceDoor.getId(), entranceDoor.getName(),
                    entranceDoor.getPrice(), minioService.createUrlImage(entranceDoor.getPathImage()), type);

            productDetailsDTOList.add(productBriefDTO);
        }

        return productDetailsDTOList;
    }
}
