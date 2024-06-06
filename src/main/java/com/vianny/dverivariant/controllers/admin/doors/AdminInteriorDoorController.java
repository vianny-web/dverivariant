package com.vianny.dverivariant.controllers.admin.doors;

import com.vianny.dverivariant.dto.response.message.ResponseMainMessage;
import com.vianny.dverivariant.enums.doors.interior.*;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.models.products.doors.InteriorDoor;
import com.vianny.dverivariant.services.products.doors.InteriorDoorService;
import com.vianny.dverivariant.services.minio.ImageTransferService;
import com.vianny.dverivariant.services.minio.MinioService;
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

    @PostMapping("/interior-door")
    @Transactional
    public ResponseEntity<ResponseMainMessage> createInteriorDoor(@RequestParam MultipartFile imageFile, String name, String description,
                                                                  Integer price, Material material, GlazingInterior glazing, Modification modification,
                                                                  Construction construction, ManufacturerInterior manufacturer) {
        try {
            InteriorDoor interiorDoor = new InteriorDoor(name, description, price, material, glazing, modification, construction, manufacturer);

            interiorDoorService.addProduct(interiorDoor);
            imageTransferService.uploadImage(imageFile, interiorDoor.getPathImage());
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

            imageTransferService.uploadImage(imageFile, interiorDoorNew.getPathImage());
            interiorDoorService.updateProduct(interiorDoorNew);
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

            minioService.removeObject(interiorDoorById.get().getPathImage());
            interiorDoorService.deleteProduct(interiorDoorById.get().getId());
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
