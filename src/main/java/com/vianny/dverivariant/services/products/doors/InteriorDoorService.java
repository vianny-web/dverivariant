package com.vianny.dverivariant.services.products.doors;

import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.doors.InteriorDoor;
import com.vianny.dverivariant.repositories.products.doors.InteriorDoorRepository;
import com.vianny.dverivariant.services.products.AdminCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InteriorDoorService implements AdminCapabilities<InteriorDoor> {
    private InteriorDoorRepository<InteriorDoor> interiorDoorRepository;
    @Autowired
    public void setInteriorDoorRepository(InteriorDoorRepository<InteriorDoor> interiorDoorRepository) {
        this.interiorDoorRepository = interiorDoorRepository;
    }

    @Override
    public void addProduct(InteriorDoor interiorDoor, String urlImage) {
        interiorDoorRepository.save(new InteriorDoor(
                interiorDoor.getName(),
                interiorDoor.getDescription(),
                interiorDoor.getPrice(),
                urlImage,
                interiorDoor.getIdImage(),
                interiorDoor.getMaterial(),
                interiorDoor.getGlazing(),
                interiorDoor.getModification(),
                interiorDoor.getConstruction(),
                interiorDoor.getManufacturer()));
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

    public List<ProductBriefDTO> findAll(TypeProducts type) {
        List<InteriorDoor> interiorDoorList = interiorDoorRepository.findByType(type);
        List<ProductBriefDTO> productDetailsDTOList = new ArrayList<>();

        for (InteriorDoor interiorDoor : interiorDoorList) {
            ProductBriefDTO productBriefDTO = new ProductBriefDTO(interiorDoor.getId(), interiorDoor.getName(),
                    interiorDoor.getPrice(), interiorDoor.getUrlImage(), type);

            productDetailsDTOList.add(productBriefDTO);
        }

        return productDetailsDTOList;
    }
}
