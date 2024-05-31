package com.vianny.dverivariant.controllers.admin.other;

import com.vianny.dverivariant.dto.response.message.ResponseMainMessage;
import com.vianny.dverivariant.enums.TypeProducts;
import com.vianny.dverivariant.enums.others.HardwareType;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.models.products.others.Hardware;
import com.vianny.dverivariant.services.minio.FileTransferService;
import com.vianny.dverivariant.services.minio.MinioService;
import com.vianny.dverivariant.services.products.others.HardwareService;
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
    private HardwareService hardwareService;
    private FileTransferService fileTransferService;
    private MinioService minioService;

    @Autowired
    public void setHardwareService(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }
    @Autowired
    public void setFileTransferService(FileTransferService fileTransferService) {
        this.fileTransferService = fileTransferService;
    }
    @Autowired
    public void setMinioService(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping("/hardware")
    @Transactional
    public ResponseEntity<ResponseMainMessage> createHardware(@RequestParam MultipartFile imageFile, String name, String description,
                                                              Integer price, HardwareType hardwareType) {
        try {
            Hardware hardware = new Hardware(name, description, price, hardwareType);

            hardwareService.addProduct(hardware);
            fileTransferService.uploadImage(imageFile, hardware.getPathImage());
        }
        catch (Exception e) {
            throw new ServerErrorRequiredException(e.getMessage());
        }

        ResponseMainMessage responseMainMessage = new ResponseMainMessage(HttpStatus.CREATED, "Товар был успешно добавлен");
        return new ResponseEntity<>(responseMainMessage, HttpStatus.CREATED);
    }

    @PutMapping("/hardware")
    @Transactional
    public ResponseEntity<ResponseMainMessage> updateHardware(@RequestParam MultipartFile imageFile, String id, String name, String description,
                                                                  Integer price, HardwareType hardwareType) {
        try {
            Optional<Hardware> hardwareById = hardwareService.findProductByID(id);
            Hardware hardwareNew = new Hardware(hardwareById.get().getId(), name, description, price, hardwareType);

            fileTransferService.uploadImage(imageFile, hardwareById.get().getPathImage());
            hardwareService.updateProduct(hardwareNew);
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

    @DeleteMapping("/hardware")
    @Transactional
    public ResponseEntity<ResponseMainMessage> deleteHardware(@RequestParam String id) {
        try {
            Optional<Hardware> hardwareById = hardwareService.findProductByID(id);

            minioService.removeObject(hardwareById.get().getPathImage());
            hardwareService.deleteProduct(hardwareById.get().getId());
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
