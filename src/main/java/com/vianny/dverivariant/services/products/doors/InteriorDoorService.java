package com.vianny.dverivariant.services.products.doors;

import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import com.vianny.dverivariant.dto.response.product.ProductDetailsDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.doors.InteriorDoor;
import com.vianny.dverivariant.repositories.products.doors.InteriorDoorRepository;
import com.vianny.dverivariant.services.products.AdminCapabilitiesService;
import com.vianny.dverivariant.services.products.ProductRetrievalService;
import com.vianny.dverivariant.utils.product.ProductDetailsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InteriorDoorService implements AdminCapabilitiesService<InteriorDoor>, ProductRetrievalService<InteriorDoor> {
    private InteriorDoorRepository<InteriorDoor> interiorDoorRepository;
    private ProductDetailsHelper productDetailsHelper;

    @Autowired
    public void setInteriorDoorRepository(InteriorDoorRepository<InteriorDoor> interiorDoorRepository) {
        this.interiorDoorRepository = interiorDoorRepository;
    }
    @Autowired
    public void setProductDetailsHelper(ProductDetailsHelper productDetailsHelper) {
        this.productDetailsHelper = productDetailsHelper;
    }

    @Override
    public void addProduct(InteriorDoor interiorDoor) {
        interiorDoorRepository.save(interiorDoor);
    }

    @Override
    public void updateProduct(InteriorDoor interiorDoorNew) {
        interiorDoorRepository.save(interiorDoorNew);
    }

    @Override
    public void deleteProduct(String id) {
        interiorDoorRepository.deleteById(id);
    }

    @Override
    public Optional<InteriorDoor> findProductByID(String id) {
        return Optional.ofNullable(interiorDoorRepository.findById(id).orElseThrow(() -> new NotFoundRequiredException(HttpStatus.NOT_FOUND, "Товар не найден")));
    }

    @Override
    public List<ProductBriefDTO> getAllProductsByType(TypeProducts type) {
        List<InteriorDoor> interiorDoorList = interiorDoorRepository.findByType(type);
        List<ProductBriefDTO> productBriefDTOList = new ArrayList<>();

        for (InteriorDoor interiorDoor : interiorDoorList) {
            HashMap<String, String> details = productDetailsHelper.getDetailsInteriorDoor(Optional.ofNullable(interiorDoor));

            assert interiorDoor != null;
            ProductBriefDTO productBriefDTO = new ProductBriefDTO(interiorDoor.getId(), interiorDoor.getName(),
                    interiorDoor.getDescription(), interiorDoor.getPrice(), interiorDoor.getPathImage(), details);

            productBriefDTOList.add(productBriefDTO);
        }

        return productBriefDTOList;
    }

    @Override
    public ProductDetailsDTO getProductById(String id) {
        Optional<InteriorDoor> interiorDoor = interiorDoorRepository.findById(id);
        HashMap<String, String> details = productDetailsHelper.getDetailsInteriorDoor(interiorDoor);

        if (interiorDoor.isEmpty()) {
            throw new NotFoundRequiredException(HttpStatus.NOT_FOUND, "Продукт не найден");
        }

        return new ProductDetailsDTO(id, interiorDoor.get().getName(),
                interiorDoor.get().getDescription(), interiorDoor.get().getPrice(),
                interiorDoor.get().getPathImage(), interiorDoor.get().getType(), details);
    }
}
