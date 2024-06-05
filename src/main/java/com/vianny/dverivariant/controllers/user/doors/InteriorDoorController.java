package com.vianny.dverivariant.controllers.user.doors;

import com.vianny.dverivariant.dto.response.message.ProductMessage;
import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.services.products.doors.InteriorDoorService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger log = LogManager.getLogger(InteriorDoorController.class);

    private InteriorDoorService interiorDoorService;
    @Autowired
    public void setInteriorDoorService(InteriorDoorService interiorDoorService) {
        this.interiorDoorService = interiorDoorService;
    }

    @GetMapping("/category/interior-door/all")
    public ResponseEntity<ProductMessage<List<ProductBriefDTO>>> getAllProducts() {
        try {
            List<ProductBriefDTO> productBriefDTOS = interiorDoorService.getAllProductsByType(TypeProducts.INTERIOR_DOOR);
            ProductMessage<List<ProductBriefDTO>> dataObject = new ProductMessage<>(HttpStatus.FOUND, productBriefDTOS);
            return new ResponseEntity<>(dataObject,HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e);
            throw new ServerErrorRequiredException(e.getMessage());
        }
    }
}
