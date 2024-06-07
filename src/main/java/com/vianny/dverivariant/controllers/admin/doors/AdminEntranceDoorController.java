package com.vianny.dverivariant.controllers.admin.doors;

import com.vianny.dverivariant.dto.response.message.ResponseMainMessage;
import com.vianny.dverivariant.enums.doors.entrance.AdditionalProperties;
import com.vianny.dverivariant.enums.doors.entrance.GlazingEntrance;
import com.vianny.dverivariant.enums.doors.entrance.InstallationPlace;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.models.products.doors.EntranceDoor;
import com.vianny.dverivariant.services.minio.ImageTransferService;
import com.vianny.dverivariant.services.minio.MinioService;
import com.vianny.dverivariant.services.products.doors.EntranceDoorService;
import com.vianny.dverivariant.services.redis.RedisImageService;
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
public class AdminEntranceDoorController {
    private static final Logger log = LogManager.getLogger(AdminEntranceDoorController.class);

    private EntranceDoorService entranceDoorService;
    private ImageTransferService imageTransferService;
    private MinioService minioService;
    private RedisService redisService;
    private RedisImageService redisImageService;

    @Autowired
    public void setEntranceDoorService(EntranceDoorService entranceDoorService) {
        this.entranceDoorService = entranceDoorService;
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

    @PostMapping("/entrance-door")
    @Transactional
    public ResponseEntity<ResponseMainMessage> createEntranceDoor(@RequestParam MultipartFile imageFile, String name, String description,
                                                                  Integer price, InstallationPlace installationPlace, GlazingEntrance glazing,
                                                                  AdditionalProperties additionalProperties) {
        try {
            EntranceDoor entranceDoor = new EntranceDoor(name, description, price, installationPlace, glazing, additionalProperties);

            entranceDoorService.addProduct(entranceDoor);
            imageTransferService.uploadImage(imageFile, entranceDoor.getPathImage());

            redisService.saveData(entranceDoor.getId(), entranceDoor);
            redisImageService.saveData(entranceDoor.getPathImage(), imageFile.getBytes());
        }
        catch (Exception e) {
            log.error(e);
            throw new ServerErrorRequiredException(e.getMessage());
        }

        ResponseMainMessage responseMainMessage = new ResponseMainMessage(HttpStatus.CREATED, "Товар был успешно добавлен");
        return new ResponseEntity<>(responseMainMessage, HttpStatus.CREATED);
    }

    @PutMapping("/entrance-door")
    @Transactional
    public ResponseEntity<ResponseMainMessage> updateEntranceDoor(@RequestParam MultipartFile imageFile, String id, String name, String description,
                                                                  Integer price, InstallationPlace installationPlace, GlazingEntrance glazing,
                                                                  AdditionalProperties additionalProperties) {
        try {
            Optional<EntranceDoor> entranceDoorById = entranceDoorService.findProductByID(id);
            EntranceDoor entranceDoorNew = new EntranceDoor(entranceDoorById.get().getId(), name, description, price, entranceDoorById.get().getPathImage(),
                    installationPlace, glazing, additionalProperties);

            imageTransferService.uploadImage(imageFile, entranceDoorNew.getPathImage());
            entranceDoorService.updateProduct(entranceDoorNew);

            redisService.updateData(entranceDoorNew.getId(), entranceDoorNew);
            redisImageService.updateData(entranceDoorNew.getPathImage(), imageFile.getBytes());
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

    @DeleteMapping("/entrance-door")
    @Transactional
    public ResponseEntity<ResponseMainMessage> deleteEntranceDoor(@RequestParam String id) {
        try {
            Optional<EntranceDoor> entranceDoorById = entranceDoorService.findProductByID(id);

            minioService.removeObject(entranceDoorById.get().getPathImage());
            entranceDoorService.deleteProduct(entranceDoorById.get().getId());

            redisService.deleteData(entranceDoorById.get().getId());
            redisImageService.deleteData(entranceDoorById.get().getPathImage());
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
