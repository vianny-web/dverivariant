package com.vianny.dverivariant.controllers.admin.floors;

import com.vianny.dverivariant.dto.response.message.ResponseMainMessage;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.enums.floors.laminate.*;
import com.vianny.dverivariant.exceptions.requiredException.ConflictRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.models.products.floors.Laminate;
import com.vianny.dverivariant.services.minio.ImageTransferService;
import com.vianny.dverivariant.services.minio.MinioService;
import com.vianny.dverivariant.services.products.floors.LaminateService;
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
public class AdminLaminateController {
    private static final Logger log = LogManager.getLogger(AdminLaminateController.class);

    private LaminateService laminateService;
    private ImageTransferService imageTransferService;
    private MinioService minioService;
    private RedisService redisService;
    private RedisImageService redisImageService;
    private RedisListService redisListService;

    @Autowired
    public void setLaminateService(LaminateService laminateService) {
        this.laminateService = laminateService;
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

    @PostMapping("/laminate")
    @Transactional
    public ResponseEntity<ResponseMainMessage> createLaminate(@RequestParam MultipartFile imageFile, String article, String name, String description,
                                                                  Integer price, ClassType classType, Thickness thickness, WaterResistance waterResistance, BevelLaminate bevel,
                                                              CountryOfOrigin countryOfOrigin) {
        try {
            Laminate laminate = new Laminate(article, name, description, price, classType, thickness, waterResistance, bevel, countryOfOrigin);

            laminateService.saveOrUpdateProduct(laminate);
            imageTransferService.uploadImage(imageFile, laminate.getPathImage());

            redisService.saveData(laminate.getId(), laminate);
            redisListService.saveData(TypeProducts.LAMINATE.toString(), laminateService.getAllProductsByType(TypeProducts.LAMINATE));
            redisImageService.saveData(laminate.getPathImage(), imageFile.getBytes());
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

    @PutMapping("/laminate")
    @Transactional
    public ResponseEntity<ResponseMainMessage> updateLaminate(@RequestParam MultipartFile imageFile, String id, String article, String name, String description,
                                                              Integer price, ClassType classType, Thickness thickness, WaterResistance waterResistance, BevelLaminate bevel,
                                                              CountryOfOrigin countryOfOrigin) {
        try {
            Optional<Laminate> laminateById = laminateService.findProductByID(id);
            Laminate laminateNew = new Laminate(laminateById.get().getId(), article, name, description, price, laminateById.get().getPathImage(),
                    classType, thickness, waterResistance, bevel, countryOfOrigin);

            imageTransferService.uploadImage(imageFile, laminateNew.getPathImage());
            laminateService.saveOrUpdateProduct(laminateNew);

            redisService.saveData(laminateNew.getId(), laminateNew);
            redisListService.saveData(TypeProducts.LAMINATE.toString(), laminateService.getAllProductsByType(TypeProducts.LAMINATE));
            redisImageService.saveData(laminateNew.getPathImage(), imageFile.getBytes());
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

    @DeleteMapping("/laminate")
    @Transactional
    public ResponseEntity<ResponseMainMessage> deleteLaminate(@RequestParam String id) {
        try {
            Optional<Laminate> laminateById = laminateService.findProductByID(id);

            minioService.removeObject(laminateById.get().getPathImage());
            laminateService.deleteProduct(laminateById.get().getId());

            redisService.deleteData(laminateById.get().getId());
            redisListService.deleteData(TypeProducts.LAMINATE.toString());
            redisImageService.deleteData(laminateById.get().getPathImage());
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
