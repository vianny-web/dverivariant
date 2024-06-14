package com.vianny.dverivariant.controllers.user;

import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import com.vianny.dverivariant.exceptions.requiredException.ServerErrorRequiredException;
import com.vianny.dverivariant.services.minio.ImageTransferService;
import com.vianny.dverivariant.services.redis.RedisImageService;
import io.minio.errors.*;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
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
    private static final Logger log = LogManager.getLogger(ImageController.class);

    private ImageTransferService imageTransferService;
    private RedisImageService redisImageService;

    @Autowired
    public void setFileTransferService(ImageTransferService imageTransferService) {
        this.imageTransferService = imageTransferService;
    }
    @Autowired
    public void setRedisImageService(RedisImageService redisImageService) {
        this.redisImageService = redisImageService;
    }

    @GetMapping("/image/")
    public ResponseEntity<Resource> downloadImage(@RequestParam String pathImage) {
        try {
            byte[] imageData;

            if (redisImageService.getData(pathImage) != null) {
                imageData = redisImageService.getData(pathImage);
            } else {
                InputStreamResource imageStream = imageTransferService.downloadFile(pathImage);
                imageData = IOUtils.toByteArray(imageStream.getInputStream());

                redisImageService.saveData(pathImage, imageData);
            }


            HttpHeaders downloadHeaders = new HttpHeaders();
            downloadHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            downloadHeaders.setContentDisposition(ContentDisposition.builder("attachment").filename(pathImage).build());

            return ResponseEntity.ok()
                    .headers(downloadHeaders)
                    .body(new ByteArrayResource(imageData));
        }
        catch (NotFoundRequiredException e) {
            throw e;
        }
        catch (IOException | ErrorResponseException | InsufficientDataException | InternalException |
                 InvalidKeyException | InvalidResponseException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            log.error(e);
            throw new ServerErrorRequiredException(e.getMessage());
        }
    }
}
