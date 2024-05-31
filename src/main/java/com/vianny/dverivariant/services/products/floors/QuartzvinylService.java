package com.vianny.dverivariant.services.products.floors;

import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.floors.Quartzvinyl;
import com.vianny.dverivariant.repositories.products.floors.QuartzvinylRepository;
import com.vianny.dverivariant.services.products.AdminCapabilitiesService;
import com.vianny.dverivariant.services.products.ProductRetrievalService;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class QuartzvinylService implements AdminCapabilitiesService<Quartzvinyl>, ProductRetrievalService<Quartzvinyl> {
    private QuartzvinylRepository quartzvinylRepository;
    @Autowired
    public void setQuartzvinylRepository(QuartzvinylRepository quartzvinylRepository) {
        this.quartzvinylRepository = quartzvinylRepository;
    }

    @Override
    public void addProduct(Quartzvinyl quartzvinyl) {
        quartzvinylRepository.save(new Quartzvinyl(
                quartzvinyl.getName(),
                quartzvinyl.getDescription(),
                quartzvinyl.getPrice(),
                quartzvinyl.getBase(),
                quartzvinyl.getInstallationType(),
                quartzvinyl.getBevel(),
                quartzvinyl.getManufacturer()));
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
    public List<ProductBriefDTO> getAllProductsByType(TypeProducts type) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return List.of();
    }
}
