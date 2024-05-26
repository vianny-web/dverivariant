package com.vianny.dverivariant.controllers;

import com.vianny.dverivariant.dto.response.message.ResponseMainMessage;
import com.vianny.dverivariant.enums.doors.TypeProducts;
import com.vianny.dverivariant.enums.doors.interior.*;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.models.InteriorDoor;
import com.vianny.dverivariant.services.doors.InteriorDoorService;
import com.vianny.dverivariant.services.minio.FileTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/adm")
public class AdminController {
    private InteriorDoorService interiorDoorService;
    private FileTransferService fileTransferService;
    @Autowired
    public void setInteriorDoorService(InteriorDoorService interiorDoorService) {
        this.interiorDoorService = interiorDoorService;
    }
    @Autowired
    public void setFileTransferService(FileTransferService fileTransferService) {
        this.fileTransferService = fileTransferService;
    }

    @PostMapping("/interior-door")
    @Transactional
    public ResponseEntity<ResponseMainMessage> createInteriorDoor(@RequestParam MultipartFile imageFile, String name, String description,
                                                                  Integer price, Material material, Glazing glazing, Modification modification,
                                                                  Construction construction, Manufacturer manufacturer) {

        InteriorDoor interiorDoor = new InteriorDoor(name, description, price, material, glazing, modification, construction, manufacturer);
        try {
            String urlImage = TypeProducts.INTERIOR_DOOR + "/" + interiorDoor.getIdImage();

            interiorDoorService.addInteriorDoor(interiorDoor, urlImage);
            fileTransferService.uploadImage(imageFile, urlImage);
        }
        catch (Exception e) {
            throw new ServerErrorRequiredException(e.getMessage());
        }

        ResponseMainMessage responseMainMessage = new ResponseMainMessage(HttpStatus.CREATED, "Товар был успешно добавлен");
        return new ResponseEntity<>(responseMainMessage, HttpStatus.CREATED);
    }
}
