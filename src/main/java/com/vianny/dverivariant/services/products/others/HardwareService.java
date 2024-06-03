package com.vianny.dverivariant.services.products.others;

import com.vianny.dverivariant.dto.response.product.ProductDetailsDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.models.products.others.Hardware;
import com.vianny.dverivariant.repositories.products.others.HardwareRepository;
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
public class HardwareService implements AdminCapabilitiesService<Hardware>, ProductRetrievalService<Hardware> {
    private HardwareRepository hardwareRepository;
    private ProductDetailsHelper productDetailsHelper;

    @Autowired
    public void setHardwareRepository(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }
    @Autowired
    public void setProductDetailsHelper(ProductDetailsHelper productDetailsHelper) {
        this.productDetailsHelper = productDetailsHelper;
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
    public List<ProductDetailsDTO> getAllProductsByType(TypeProducts type) {
        List<Hardware> hardwareList = hardwareRepository.findByType(type);
        List<ProductDetailsDTO> productDetailsDTOList = new ArrayList<>();

        for (Hardware hardware : hardwareList) {
            HashMap<String, String> details = productDetailsHelper.getDetailsHardware(Optional.ofNullable(hardware));

            assert hardware != null;
            ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO(hardware.getId(), hardware.getName(), hardware.getDescription(),
                    hardware.getPrice(), hardware.getPathImage(), details);

            productDetailsDTOList.add(productDetailsDTO);
        }

        return productDetailsDTOList;
    }

    @Override
    public ProductDetailsDTO getProductById(String id) {
        return null;
    }
}
