package com.vianny.dverivariant.controllers.user.doors;

import com.vianny.dverivariant.dto.response.message.ProductMessage;
import com.vianny.dverivariant.dto.response.product.ProductBriefDTO;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.services.products.doors.EntranceDoorService;
import com.vianny.dverivariant.services.redis.RedisListService;
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
public class EntranceDoorController {
    private static final Logger log = LogManager.getLogger(EntranceDoorController.class);

    private EntranceDoorService entranceDoorService;
    private RedisListService redisListService;

    @Autowired
    public void setEntranceDoorService(EntranceDoorService entranceDoorService) {
        this.entranceDoorService = entranceDoorService;
    }
    @Autowired
    public void setRedisListService(RedisListService redisListService) {
        this.redisListService = redisListService;
    }

    @GetMapping("/category/entrance-door/all")
    public ResponseEntity<ProductMessage<List<ProductBriefDTO>>> getAllProducts() {
        try {
            List<ProductBriefDTO> productBriefDTOS;
            List<ProductBriefDTO> cachedData = redisListService.getData(TypeProducts.ENTRANCE_DOOR.toString());

            if (cachedData != null) {
                productBriefDTOS = cachedData;
            }
            else {
                productBriefDTOS = entranceDoorService.getAllProductsByType(TypeProducts.ENTRANCE_DOOR);
                redisListService.saveData(TypeProducts.ENTRANCE_DOOR.toString(), productBriefDTOS);
            }

            ProductMessage<List<ProductBriefDTO>> dataObject = new ProductMessage<>(HttpStatus.FOUND, productBriefDTOS);
            return new ResponseEntity<>(dataObject,HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e);
            throw new ServerErrorRequiredException(e.getMessage());
        }
    }
}
