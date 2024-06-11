package com.vianny.dverivariant.services.products.floors;

import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import com.vianny.dverivariant.dto.response.product.ProductDetailsDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.floors.Laminate;
import com.vianny.dverivariant.repositories.products.floors.LaminateRepository;
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
public class LaminateService implements AdminCapabilitiesService<Laminate>, ProductRetrievalService<Laminate> {
    private LaminateRepository laminateRepository;
    private ProductDetailsHelper productDetailsHelper;
    private RedisService redisService;

    @Autowired
    public void setLaminateRepository(LaminateRepository laminateRepository) {
        this.laminateRepository = laminateRepository;
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
    public void addProduct(Laminate laminate) {
        laminateRepository.save(laminate);
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
    public List<ProductBriefDTO> getAllProductsByType(TypeProducts type) {
        List<Laminate> laminateList = laminateRepository.findByType(type);
        List<ProductBriefDTO> productBriefDTOList = new ArrayList<>();

        for (Laminate laminate : laminateList) {
            HashMap<String, String> details = productDetailsHelper.getDetailsLaminate(Optional.ofNullable(laminate));

            assert laminate != null;
            ProductBriefDTO productBriefDTO = new ProductBriefDTO(laminate.getId(), laminate.getArticle(), laminate.getName(), laminate.getDescription(),
                    laminate.getPrice(), laminate.getPathImage(), details);

            productBriefDTOList.add(productBriefDTO);
        }

        return productBriefDTOList;
    }

    @Override
    public ProductDetailsDTO getProductById(String id) {
        Optional<Laminate> laminate;

        Object cachedData = redisService.getData(id);
        if (cachedData != null) {
            laminate = Optional.of((Laminate) cachedData);
        } else {
            laminate = laminateRepository.findById(id);
            laminate.ifPresent(laminateType -> redisService.saveData(id, laminateType));
        }

        if (laminate.isEmpty()) {
            throw new NotFoundRequiredException(HttpStatus.NOT_FOUND, "Продукт не найден");
        }
        HashMap<String, String> details = productDetailsHelper.getDetailsLaminate(laminate);

        return new ProductDetailsDTO(id, laminate.get().getArticle(), laminate.get().getName(),
                laminate.get().getDescription(), laminate.get().getPrice(),
                laminate.get().getPathImage(), laminate.get().getType(), details);
    }
}
