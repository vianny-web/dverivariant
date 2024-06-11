package com.vianny.dverivariant.controllers.admin.other;

import com.vianny.dverivariant.dto.response.message.ResponseMainMessage;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.enums.others.HardwareType;
import com.vianny.dverivariant.exceptions.requiredException.ConflictRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.models.products.others.Hardware;
import com.vianny.dverivariant.services.minio.ImageTransferService;
import com.vianny.dverivariant.services.minio.MinioService;
import com.vianny.dverivariant.services.products.others.HardwareService;
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
public class AdminHardwareController {
    private static final Logger log = LogManager.getLogger(AdminHardwareController.class);

    private HardwareService hardwareService;
    private ImageTransferService imageTransferService;
    private MinioService minioService;
    private RedisService redisService;
    private RedisImageService redisImageService;
    private RedisListService redisListService;

    @Autowired
    public void setHardwareService(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
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

    @PostMapping("/hardware")
    @Transactional
    public ResponseEntity<ResponseMainMessage> createHardware(@RequestParam MultipartFile imageFile, String article, String name, String description,
                                                              Integer price, HardwareType hardwareType) {
        try {
            Hardware hardware = new Hardware(article, name, description, price, hardwareType);

            hardwareService.addProduct(hardware);
            imageTransferService.uploadImage(imageFile, hardware.getPathImage());

            redisService.saveData(hardware.getId(), hardware);
            redisListService.saveData(TypeProducts.HARDWARE.toString(), hardwareService.getAllProductsByType(TypeProducts.HARDWARE));
            redisImageService.saveData(hardware.getPathImage(), imageFile.getBytes());
        }
        catch (ConflictRequiredException e) {
            throw e;
        }
        catch (Exception e) {
            log.error(e);
            throw new ServerErrorRequiredException(e.getMessage());
        }

        ResponseMainMessage responseMainMessage = new ResponseMainMessage(HttpStatus.CREATED, "Товар был успешно добавлен");
        return new ResponseEntity<>(responseMainMessage, HttpStatus.CREATED);
    }

    @PutMapping("/hardware")
    @Transactional
    public ResponseEntity<ResponseMainMessage> updateHardware(@RequestParam MultipartFile imageFile, String id, String article, String name, String description,
                                                                  Integer price, HardwareType hardwareType) {
        try {
            Optional<Hardware> hardwareById = hardwareService.findProductByID(id);
            Hardware hardwareNew = new Hardware(hardwareById.get().getId(), article, name, description, price, hardwareType, hardwareById.get().getPathImage());

            imageTransferService.uploadImage(imageFile, hardwareNew.getPathImage());
            hardwareService.updateProduct(hardwareNew);

            redisService.saveData(hardwareNew.getId(), hardwareNew);
            redisListService.saveData(TypeProducts.HARDWARE.toString(), hardwareService.getAllProductsByType(TypeProducts.HARDWARE));
            redisImageService.saveData(hardwareNew.getPathImage(), imageFile.getBytes());
        }
        catch (NotFoundRequiredException | ConflictRequiredException e) {
            throw e;
        }
        catch (Exception e) {
            log.error(e);
            throw new ServerErrorRequiredException(e.getMessage());
        }

        ResponseMainMessage responseMainMessage = new ResponseMainMessage(HttpStatus.OK, "Товар успешно обновлен");
        return new ResponseEntity<>(responseMainMessage, HttpStatus.OK);
    }

    @DeleteMapping("/hardware")
    @Transactional
    public ResponseEntity<ResponseMainMessage> deleteHardware(@RequestParam String id) {
        try {
            Optional<Hardware> hardwareById = hardwareService.findProductByID(id);

            minioService.removeObject(hardwareById.get().getPathImage());
            hardwareService.deleteProduct(hardwareById.get().getId());

            redisService.deleteData(hardwareById.get().getId());
            redisListService.deleteData(TypeProducts.HARDWARE.toString());
            redisImageService.deleteData(hardwareById.get().getPathImage());
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
