package com.vianny.dverivariant.controllers.user;

import com.vianny.dverivariant.dto.response.message.ProductMessage;
import com.vianny.dverivariant.dto.response.product.ProductDetailsDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.services.products.doors.EntranceDoorService;
import com.vianny.dverivariant.services.products.doors.InteriorDoorService;
import com.vianny.dverivariant.services.products.floors.LaminateService;
import com.vianny.dverivariant.services.products.floors.QuartzvinylService;
import com.vianny.dverivariant.services.products.others.HardwareService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalog")
public class ProductController {
    private static final Logger log = LogManager.getLogger(ProductController.class);

    private final InteriorDoorService interiorDoorService;
    private final EntranceDoorService entranceDoorService;
    private final LaminateService laminateService;
    private final QuartzvinylService quartzvinylService;
    private final HardwareService hardwareService;

    @Autowired
    public ProductController(InteriorDoorService interiorDoorService, EntranceDoorService entranceDoorService, LaminateService laminateService, QuartzvinylService quartzvinylService, HardwareService hardwareService) {
        this.interiorDoorService = interiorDoorService;
        this.entranceDoorService = entranceDoorService;
        this.laminateService = laminateService;
        this.quartzvinylService = quartzvinylService;
        this.hardwareService = hardwareService;
    }

    @GetMapping("/product")
    public ResponseEntity<ProductMessage<ProductDetailsDTO>> getProduct(@RequestParam String id, @RequestParam TypeProducts type) {
        try {
            ProductDetailsDTO productById = null;

            switch (type) {
                case INTERIOR_DOOR -> productById = interiorDoorService.getProductById(id);
                case ENTRANCE_DOOR -> productById = entranceDoorService.getProductById(id);
                case LAMINATE -> productById = laminateService.getProductById(id);
                case QUARTZVINYL -> productById = quartzvinylService.getProductById(id);
                case HARDWARE -> productById = hardwareService.getProductById(id);
            }
            
            ProductMessage<ProductDetailsDTO> dataObject = new ProductMessage<>(HttpStatus.FOUND, productById);
            return new ResponseEntity<>(dataObject,HttpStatus.OK);
        }
        catch (NotFoundRequiredException e) {
            log.warn(e);
            throw e;
        }
        catch (Exception e) {
            log.error(e);
            throw new ServerErrorRequiredException(e.getMessage());
        }
    }
}
