package com.vianny.dverivariant.controllers.admin.floors;

import com.vianny.dverivariant.dto.response.message.ResponseMainMessage;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.enums.floors.quartzvinyl.Base;
import com.vianny.dverivariant.enums.floors.quartzvinyl.BevelQuartzvinyl;
import com.vianny.dverivariant.enums.floors.quartzvinyl.InstallationType;
import com.vianny.dverivariant.enums.floors.quartzvinyl.ManufacturerQuartzvinyl;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.models.products.floors.Quartzvinyl;
import com.vianny.dverivariant.services.minio.FileTransferService;
import com.vianny.dverivariant.services.minio.MinioService;
import com.vianny.dverivariant.services.products.floors.QuartzvinylService;
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
    private QuartzvinylService quartzvinylService;
    private FileTransferService fileTransferService;
    private MinioService minioService;

    @Autowired
    public void setQuartzvinylService(QuartzvinylService quartzvinylService) {
        this.quartzvinylService = quartzvinylService;
    }
    @Autowired
    public void setFileTransferService(FileTransferService fileTransferService) {
        this.fileTransferService = fileTransferService;
    }
    @Autowired
    public void setMinioService(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping("/quartzvinyl")
    @Transactional
    public ResponseEntity<ResponseMainMessage> createQuartzvinyl(@RequestParam MultipartFile imageFile, String name, String description,
                                                                 Integer price, Base base, InstallationType installationType, BevelQuartzvinyl bevelQuartzvinyl,
                                                                 ManufacturerQuartzvinyl manufacturerQuartzvinyl) {
        try {
            Quartzvinyl quartzvinyl = new Quartzvinyl(name, description, price, base, installationType, bevelQuartzvinyl, manufacturerQuartzvinyl);
            String urlImage = TypeProducts.QUARTZVINYL + "/" + quartzvinyl.getIdImage();

            quartzvinylService.addProduct(quartzvinyl, urlImage);
            fileTransferService.uploadImage(imageFile, urlImage);
        }
        catch (Exception e) {
            throw new ServerErrorRequiredException(e.getMessage());
        }

        ResponseMainMessage responseMainMessage = new ResponseMainMessage(HttpStatus.CREATED, "Товар был успешно добавлен");
        return new ResponseEntity<>(responseMainMessage, HttpStatus.CREATED);
    }

    @PutMapping("/quartzvinyl")
    @Transactional
    public ResponseEntity<ResponseMainMessage> updateQuartzvinyl(@RequestParam MultipartFile imageFile, String id, String name, String description,
                                                                  Integer price, Base base, InstallationType installationType, BevelQuartzvinyl bevelQuartzvinyl,
                                                                 ManufacturerQuartzvinyl manufacturerQuartzvinyl) {
        try {
            Optional<Quartzvinyl> quartzvinylById = quartzvinylService.findProductByID(id);
            Quartzvinyl quartzvinylNew = new Quartzvinyl(quartzvinylById.get().getId(), name, description, price, quartzvinylById.get().getUrlImage(),
                    quartzvinylById.get().getIdImage(), base, installationType, bevelQuartzvinyl, manufacturerQuartzvinyl);

            fileTransferService.uploadImage(imageFile, quartzvinylById.get().getUrlImage());
            quartzvinylService.updateProduct(quartzvinylNew);
        }
        catch (NotFoundRequiredException e) {
            throw e;
        }
        catch (Exception e) {
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

            minioService.removeObject(quartzvinylById.get().getUrlImage());
            quartzvinylService.deleteProduct(quartzvinylById.get().getId());
        }
        catch (NotFoundRequiredException e) {
            throw e;
        }
        catch (Exception e) {
            throw new ServerErrorRequiredException(e.getMessage());
        }

        ResponseMainMessage responseMainMessage = new ResponseMainMessage(HttpStatus.OK, "Товар успешно удален");
        return new ResponseEntity<>(responseMainMessage, HttpStatus.OK);
    }
}
