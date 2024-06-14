package com.vianny.dverivariant.services.products.doors;

import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import com.vianny.dverivariant.dto.response.product.ProductDetailsDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.ConflictRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.doors.EntranceDoor;
import com.vianny.dverivariant.repositories.products.doors.EntranceDoorRepository;
import com.vianny.dverivariant.services.products.AdminCapabilitiesService;
import com.vianny.dverivariant.services.products.ProductRetrievalService;
import com.vianny.dverivariant.services.redis.RedisService;
import com.vianny.dverivariant.utils.product.ProductDetailsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class EntranceDoorService implements AdminCapabilitiesService<EntranceDoor>, ProductRetrievalService<EntranceDoor> {
    private EntranceDoorRepository entranceDoorRepository;
    private ProductDetailsHelper productDetailsHelper;
    private RedisService redisService;

    @Autowired
    public void setEntranceDoorRepository(EntranceDoorRepository entranceDoorRepository) {
        this.entranceDoorRepository = entranceDoorRepository;
    }
    @Autowired
    public void setProductDetailsHelper(ProductDetailsHelper productDetailsHelper) {
        this.productDetailsHelper = productDetailsHelper;
    }
    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public void saveProduct(EntranceDoor entranceDoor) {
        if (!entranceDoorRepository.existsByArticle(entranceDoor.getArticle()))
            entranceDoorRepository.save(entranceDoor);
        else
            throw new ConflictRequiredException("Выберите другой артикул");
    }

    @Override
    public void updateProduct(EntranceDoor entranceDoor) {
        boolean exists = entranceDoorRepository.existsByArticleAndIdNot(entranceDoor.getArticle(), entranceDoor.getId());
        if (exists) {
            throw new ConflictRequiredException("Выберите другой артикул");
        }
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
    public List<ProductBriefDTO> getAllProductsByType(TypeProducts type) {
        List<EntranceDoor> entranceDoorList = entranceDoorRepository.findByType(type);
        List<ProductBriefDTO> productBriefDTOList = new ArrayList<>();

        for (EntranceDoor entranceDoor : entranceDoorList) {
            HashMap<String, String> details = productDetailsHelper.getDetailsEntranceDoor(Optional.ofNullable(entranceDoor));

            assert entranceDoor != null;
            ProductBriefDTO productBriefDTO = new ProductBriefDTO(entranceDoor.getId(), entranceDoor.getArticle(), entranceDoor.getName(), entranceDoor.getDescription(),
                    entranceDoor.getPrice(), entranceDoor.getPathImage(), details);

            productBriefDTOList.add(productBriefDTO);
        }

        return productBriefDTOList;
    }

    @Override
    public ProductDetailsDTO getProductById(String id) {
        Optional<EntranceDoor> entranceDoor;

        Object cachedData = redisService.getData(id);
        if (cachedData != null) {
            entranceDoor = Optional.of((EntranceDoor) cachedData);
        } else {
            entranceDoor = entranceDoorRepository.findById(id);
            entranceDoor.ifPresent(door -> redisService.saveData(id, door));
        }

        if (entranceDoor.isEmpty()) {
            throw new NotFoundRequiredException(HttpStatus.NOT_FOUND, "Продукт не найден");
        }
        HashMap<String, String> details = productDetailsHelper.getDetailsEntranceDoor(entranceDoor);

        return new ProductDetailsDTO(id, entranceDoor.get().getArticle(), entranceDoor.get().getName(),
                entranceDoor.get().getDescription(), entranceDoor.get().getPrice(),
                entranceDoor.get().getPathImage(), entranceDoor.get().getType(), details);
    }
}
