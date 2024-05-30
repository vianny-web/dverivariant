package com.vianny.dverivariant.controllers.user.doors;

import com.vianny.dverivariant.dto.response.product.ProductDTO;
import com.vianny.dverivariant.dto.response.message.ProductMessage;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.services.products.doors.InteriorDoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class InteriorDoorController {
    private InteriorDoorService interiorDoorService;
    @Autowired
    public void setInteriorDoorService(InteriorDoorService interiorDoorService) {
        this.interiorDoorService = interiorDoorService;
    }

    @GetMapping("/category/interior-door/all")
    public ResponseEntity<ProductMessage<List<ProductDTO>>> getAllProducts() {
        try {
            List<ProductDTO> productDTOList = interiorDoorService.findAll(TypeProducts.INTERIOR_DOOR);
            ProductMessage<List<ProductDTO>> dataObject = new ProductMessage<>(HttpStatus.FOUND, productDTOList);
            return new ResponseEntity<>(dataObject,HttpStatus.OK);
        }
        catch (Exception e) {
            throw new ServerErrorRequiredException(e.getMessage());
        }
    }
}
