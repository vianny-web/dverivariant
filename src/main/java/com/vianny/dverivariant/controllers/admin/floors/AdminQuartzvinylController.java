package com.vianny.dverivariant.controllers.admin.floors;

import com.vianny.dverivariant.dto.response.message.ResponseMainMessage;
import com.vianny.dverivariant.enums.floors.quartzvinyl.Base;
import com.vianny.dverivariant.enums.floors.quartzvinyl.BevelQuartzvinyl;
import com.vianny.dverivariant.enums.floors.quartzvinyl.InstallationType;
import com.vianny.dverivariant.enums.floors.quartzvinyl.ManufacturerQuartzvinyl;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.models.products.floors.Quartzvinyl;
import com.vianny.dverivariant.services.minio.ImageTransferService;
import com.vianny.dverivariant.services.minio.MinioService;
import com.vianny.dverivariant.services.products.floors.QuartzvinylService;
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
public class AdminQuartzvinylController {
    private static final Logger log = LogManager.getLogger(AdminQuartzvinylController.class);

    private QuartzvinylService quartzvinylService;
    private ImageTransferService imageTransferService;
    private MinioService minioService;
    private RedisService redisService;
    private RedisImageService redisImageService;

    @Autowired
    public void setQuartzvinylService(QuartzvinylService quartzvinylService) {
        this.quartzvinylService = quartzvinylService;
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

    @PostMapping("/quartzvinyl")
    @Transactional
    public ResponseEntity<ResponseMainMessage> createQuartzvinyl(@RequestParam MultipartFile imageFile, String name, String description,
                                                                 Integer price, Base base, InstallationType installationType, BevelQuartzvinyl bevel,
                                                                 ManufacturerQuartzvinyl manufacturer) {
        try {
            Quartzvinyl quartzvinyl = new Quartzvinyl(name, description, price, base, installationType, bevel, manufacturer);

            quartzvinylService.addProduct(quartzvinyl);
            imageTransferService.uploadImage(imageFile, quartzvinyl.getPathImage());

            redisService.saveData(quartzvinyl.getId(), quartzvinyl);
            redisImageService.saveData(quartzvinyl.getPathImage(), imageFile.getBytes());
        }
        catch (Exception e) {
            log.error(e);
            throw new ServerErrorRequiredException(e.getMessage());
        }

        ResponseMainMessage responseMainMessage = new ResponseMainMessage(HttpStatus.CREATED, "Товар был успешно добавлен");
        return new ResponseEntity<>(responseMainMessage, HttpStatus.CREATED);
    }

    @PutMapping("/quartzvinyl")
    @Transactional
    public ResponseEntity<ResponseMainMessage> updateQuartzvinyl(@RequestParam MultipartFile imageFile, String id, String name, String description,
                                                                  Integer price, Base base, InstallationType installationType, BevelQuartzvinyl bevel,
                                                                 ManufacturerQuartzvinyl manufacturer) {
        try {
            Optional<Quartzvinyl> quartzvinylById = quartzvinylService.findProductByID(id);
            Quartzvinyl quartzvinylNew = new Quartzvinyl(quartzvinylById.get().getId(), name, description, price, quartzvinylById.get().getPathImage(),
                    base, installationType, bevel, manufacturer);

            imageTransferService.uploadImage(imageFile, quartzvinylNew.getPathImage());
            quartzvinylService.updateProduct(quartzvinylNew);

            redisService.saveData(quartzvinylNew.getId(), quartzvinylNew);
            redisImageService.saveData(quartzvinylNew.getPathImage(), imageFile.getBytes());
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

    @DeleteMapping("/quartzvinyl")
    @Transactional
    public ResponseEntity<ResponseMainMessage> deleteQuartzvinyl(@RequestParam String id) {
        try {
            Optional<Quartzvinyl> quartzvinylById = quartzvinylService.findProductByID(id);

            minioService.removeObject(quartzvinylById.get().getPathImage());
            quartzvinylService.deleteProduct(quartzvinylById.get().getId());

            redisService.deleteData(quartzvinylById.get().getId());
            redisImageService.deleteData(quartzvinylById.get().getPathImage());
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
