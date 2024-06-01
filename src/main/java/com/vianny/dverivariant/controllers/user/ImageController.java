package com.vianny.dverivariant.controllers.user;

import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.services.minio.ImageTransferService;
import io.minio.errors.*;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/catalog")
public class ImageController {
    private ImageTransferService imageTransferService;
    @Autowired
    public void setFileTransferService(ImageTransferService imageTransferService) {
        this.imageTransferService = imageTransferService;
    }

    @GetMapping("/image/")
    public ResponseEntity<Resource> downloadImage(@RequestParam String pathImage) {
        try {
            InputStreamResource resource = imageTransferService.downloadFile(pathImage);

            HttpHeaders downloadHeaders = new HttpHeaders();
            downloadHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            downloadHeaders.setContentDisposition(ContentDisposition.builder("attachment").filename(pathImage).build());

            return ResponseEntity.ok()
                    .headers(downloadHeaders)
                    .body(resource);
        } catch (IOException | ErrorResponseException | InsufficientDataException | InternalException |
                 InvalidKeyException | InvalidResponseException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            throw new ServerErrorRequiredException(e.getMessage());
        }
    }
}
