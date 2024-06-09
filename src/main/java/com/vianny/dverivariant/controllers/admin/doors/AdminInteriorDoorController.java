package com.vianny.dverivariant.controllers.admin.doors;

import com.vianny.dverivariant.dto.response.message.ResponseMainMessage;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.enums.doors.interior.*;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.models.products.doors.InteriorDoor;
import com.vianny.dverivariant.services.products.doors.InteriorDoorService;
import com.vianny.dverivariant.services.minio.ImageTransferService;
import com.vianny.dverivariant.services.minio.MinioService;
import com.vianny.dverivariant.services.redis.RedisImageService;
import com.vianny.dverivariant.services.redis.RedisListService;
import com.vianny.dverivariant.services.redis.RedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/adm")
public class AdminInteriorDoorController {
    private static final Logger log = LogManager.getLogger(AdminInteriorDoorController.class);

    private InteriorDoorService interiorDoorService;
    private ImageTransferService imageTransferService;
    private MinioService minioService;
    private RedisService redisService;
    private RedisImageService redisImageService;
    private RedisListService redisListService;

    @Autowired
    public void setInteriorDoorService(InteriorDoorService interiorDoorService) {
        this.interiorDoorService = interiorDoorService;
    }
    @Autowired
    public void setFileTransferService(ImageTransferService imageTransferService) {
        this.imageTransferService = imageTransferService;
    }
    @Autowired
    public void setMinioService(MinioService minioService) {
        this.minioService = minioService;
    }
    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }
    @Autowired
    public void setRedisImageService(RedisImageService redisImageService) {
        this.redisImageService = redisImageService;
    }
    @Autowired
    public void setRedisListService(RedisListService redisListService) {
        this.redisListService = redisListService;
    }

    @PostMapping("/interior-door")
    @Transactional
    public ResponseEntity<ResponseMainMessage> createInteriorDoor(@RequestParam MultipartFile imageFile, String name, String description,
                                                                  Integer price, Material material, GlazingInterior glazing, Modification modification,
                                                                  Construction construction, ManufacturerInterior manufacturer) {
        try {
            InteriorDoor interiorDoor = new InteriorDoor(name, description, price, material, glazing, modification, construction, manufacturer);

            interiorDoorService.addProduct(interiorDoor);
            imageTransferService.uploadImage(imageFile, interiorDoor.getPathImage());

            redisService.saveData(interiorDoor.getId(), interiorDoor);
            redisListService.saveData(TypeProducts.INTERIOR_DOOR.toString(), interiorDoorService.getAllProductsByType(TypeProducts.INTERIOR_DOOR));
            redisImageService.saveData(interiorDoor.getPathImage(), imageFile.getBytes());
        }
        catch (Exception e) {
            log.error(e);
            throw new ServerErrorRequiredException(e.getMessage());
        }

        ResponseMainMessage responseMainMessage = new ResponseMainMessage(HttpStatus.CREATED, "Товар был успешно добавлен");
        return new ResponseEntity<>(responseMainMessage, HttpStatus.CREATED);
    }

    @PutMapping("/interior-door")
    @Transactional
    public ResponseEntity<ResponseMainMessage> updateInteriorDoor(@RequestParam MultipartFile imageFile, String id, String name, String description,
                                                                  Integer price, Material material, GlazingInterior glazing, Modification modification,
                                                                  Construction construction, ManufacturerInterior manufacturer) {
        try {
            Optional<InteriorDoor> interiorDoorById = interiorDoorService.findProductByID(id);
            InteriorDoor interiorDoorNew = new InteriorDoor(interiorDoorById.get().getId(), name, description, price, interiorDoorById.get().getPathImage(),
                    material, glazing, modification, construction, manufacturer);

            interiorDoorService.updateProduct(interiorDoorNew);
            imageTransferService.uploadImage(imageFile, interiorDoorNew.getPathImage());

            redisService.saveData(interiorDoorNew.getId(), interiorDoorNew);
            redisListService.saveData(TypeProducts.INTERIOR_DOOR.toString(), interiorDoorService.getAllProductsByType(TypeProducts.INTERIOR_DOOR));
            redisImageService.saveData(interiorDoorNew.getPathImage(), imageFile.getBytes());
        }
        catch (NotFoundRequiredException e) {
            throw e;
        }
        catch (Exception e) {
            log.error(e);
            throw new ServerErrorRequiredException(e.getMessage());
        }

        ResponseMainMessage responseMainMessage = new ResponseMainMessage(HttpStatus.OK, "Товар успешно обновлен");
        return new ResponseEntity<>(responseMainMessage, HttpStatus.OK);
    }

    @DeleteMapping("/interior-door")
    @Transactional
    public ResponseEntity<ResponseMainMessage> deleteInteriorDoor(@RequestParam String id) {
        try {
            Optional<InteriorDoor> interiorDoorById = interiorDoorService.findProductByID(id);

            interiorDoorService.deleteProduct(interiorDoorById.get().getId());
            minioService.removeObject(interiorDoorById.get().getPathImage());

            redisService.deleteData(interiorDoorById.get().getId());
            redisListService.deleteData(TypeProducts.INTERIOR_DOOR.toString());
            redisImageService.deleteData(interiorDoorById.get().getPathImage());
        }
        catch (NotFoundRequiredException e) {
            throw e;
        }
        catch (Exception e) {
            log.error(e);
            throw new ServerErrorRequiredException(e.getMessage());
        }

        ResponseMainMessage responseMainMessage = new ResponseMainMessage(HttpStatus.OK, "Товар успешно удален");
        return new ResponseEntity<>(responseMainMessage, HttpStatus.OK);
    }
}
