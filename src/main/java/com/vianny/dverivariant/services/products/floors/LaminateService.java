package com.vianny.dverivariant.services.products.floors;

import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.floors.Laminate;
import com.vianny.dverivariant.repositories.products.floors.LaminateRepository;
import com.vianny.dverivariant.services.products.AdminCapabilitiesService;
import com.vianny.dverivariant.services.products.ProductRetrievalService;
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

    @Autowired
    public void setLaminateRepository(LaminateRepository laminateRepository) {
        this.laminateRepository = laminateRepository;
    }
    @Autowired
    public void setProductDetailsHelper(ProductDetailsHelper productDetailsHelper) {
        this.productDetailsHelper = productDetailsHelper;
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
            ProductBriefDTO productBriefDTO = new ProductBriefDTO(laminate.getId(), laminate.getName(), laminate.getDescription(),
                    laminate.getPrice(), laminate.getPathImage(), details);

            productBriefDTOList.add(productBriefDTO);
        }

        return productBriefDTOList;
    }

    @Override
    public ProductBriefDTO getProductById(String id) {
        return null;
    }
}
