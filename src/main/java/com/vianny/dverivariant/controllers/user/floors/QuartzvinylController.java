package com.vianny.dverivariant.controllers.user.floors;

import com.vianny.dverivariant.dto.response.message.ProductMessage;
import com.vianny.dverivariant.dto.response.product.ProductDetailsDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.services.products.floors.QuartzvinylService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class QuartzvinylController {
    private QuartzvinylService quartzvinylService;
    @Autowired
    public void setQuartzvinylService(QuartzvinylService quartzvinylService) {
        this.quartzvinylService = quartzvinylService;
    }

    @GetMapping("/category/quartzvinyl/all")
    public ResponseEntity<ProductMessage<List<ProductDetailsDTO>>> getAllProducts() {
        try {
            List<ProductDetailsDTO> productDetailsDTOS = quartzvinylService.getAllProductsByType(TypeProducts.QUARTZVINYL);
            ProductMessage<List<ProductDetailsDTO>> dataObject = new ProductMessage<>(HttpStatus.FOUND, productDetailsDTOS);
            return new ResponseEntity<>(dataObject,HttpStatus.OK);
        }
        catch (Exception e) {
            throw new ServerErrorRequiredException(e.getMessage());
        }
    }
}
