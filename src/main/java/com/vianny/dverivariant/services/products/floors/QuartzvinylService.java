package com.vianny.dverivariant.services.products.floors;

import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import com.vianny.dverivariant.dto.response.product.ProductDetailsDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.ConflictRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.floors.Quartzvinyl;
import com.vianny.dverivariant.repositories.products.floors.QuartzvinylRepository;
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
public class QuartzvinylService implements AdminCapabilitiesService<Quartzvinyl>, ProductRetrievalService<Quartzvinyl> {
    private QuartzvinylRepository quartzvinylRepository;
    private ProductDetailsHelper productDetailsHelper;
    private RedisService redisService;

    @Autowired
    public void setQuartzvinylRepository(QuartzvinylRepository quartzvinylRepository) {
        this.quartzvinylRepository = quartzvinylRepository;
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
    public void saveProduct(Quartzvinyl quartzvinyl) {
        if (!quartzvinylRepository.existsByArticle(quartzvinyl.getArticle()))
            quartzvinylRepository.save(quartzvinyl);
        else
            throw new ConflictRequiredException("Выберите другой артикул");
    }

    @Override
    public void updateProduct(Quartzvinyl quartzvinyl) {
            boolean exists = quartzvinylRepository.existsByArticleAndIdNot(quartzvinyl.getArticle(), quartzvinyl.getId());
        if (exists) {
            throw new ConflictRequiredException("Выберите другой артикул");
        }
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
    public List<ProductBriefDTO> getAllProductsByType(TypeProducts type) {
        List<Quartzvinyl> quartzvinylList = quartzvinylRepository.findByType(type);
            List<ProductBriefDTO> productBriefDTOList = new ArrayList<>();

        for (Quartzvinyl quartzvinyl : quartzvinylList) {
            HashMap<String, String> details = productDetailsHelper.getDetailsQuartzvinyl(Optional.ofNullable(quartzvinyl));

            assert quartzvinyl != null;
            ProductBriefDTO productBriefDTO = new ProductBriefDTO(quartzvinyl.getId(), quartzvinyl.getArticle(), quartzvinyl.getName(), quartzvinyl.getDescription(),
                    quartzvinyl.getPrice(), quartzvinyl.getPathImage(), details);

            productBriefDTOList.add(productBriefDTO);
        }

        return productBriefDTOList;
    }

    @Override
    public ProductDetailsDTO getProductById(String id) {
        Optional<Quartzvinyl> quartzvinyl;

        Object cachedData = redisService.getData(id);
        if (cachedData != null) {
            quartzvinyl = Optional.of((Quartzvinyl) cachedData);
        } else {
            quartzvinyl = quartzvinylRepository.findById(id);
            quartzvinyl.ifPresent(quartzvinylType -> redisService.saveData(id, quartzvinylType));
        }

        if (quartzvinyl.isEmpty()) {
            throw new NotFoundRequiredException(HttpStatus.NOT_FOUND, "Продукт не найден");
        }
        HashMap<String, String> details = productDetailsHelper.getDetailsQuartzvinyl(quartzvinyl);

        return new ProductDetailsDTO(id, quartzvinyl.get().getArticle(), quartzvinyl.get().getName(),
                quartzvinyl.get().getDescription(), quartzvinyl.get().getPrice(),
                quartzvinyl.get().getPathImage(), quartzvinyl.get().getType(), details);
    }
}
