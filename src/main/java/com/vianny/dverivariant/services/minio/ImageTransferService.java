package com.vianny.dverivariant.services.minio;

import com.vianny.dverivariant.config.MinioConfig;
import com.vianny.dverivariant.exceptions.requiredException.NotFoundRequiredException;
import io.minio.GetObjectArgs;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class ImageTransferService {
    private MinioConfig minioConfig;
    @Autowired
    public void setMinioConfig(MinioConfig minioConfig) {
        this.minioConfig = minioConfig;
    }

    public void uploadImage(MultipartFile imageFile, String pathImage) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!imageFile.isEmpty()) {
            String contentType = imageFile.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                throw new IllegalArgumentException("Файл не является изображением. Загрузка не удалась.");
            }

            try (InputStream inputStream = imageFile.getInputStream()) {
                minioConfig.minioClient().putObject(PutObjectArgs.builder()
                        .bucket("dveri-images")
                        .object(pathImage)
                        .stream(inputStream, imageFile.getSize(), -1)
                        .contentType(contentType)
                        .build());
            }
        }
    }
    public InputStreamResource downloadFile(String path) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        InputStream fileStream;
        try {
            fileStream = minioConfig.minioClient().getObject(
                    GetObjectArgs.builder()
                            .bucket("dveri-images")
                            .object(path)
                            .build()
            );
        } catch (ErrorResponseException e) {
            throw new NotFoundRequiredException(HttpStatus.NOT_FOUND, "Файл не найден");
        }

        return new InputStreamResource(fileStream);
    }
}
