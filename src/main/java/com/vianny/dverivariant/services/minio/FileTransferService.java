package com.vianny.dverivariant.services.minio;

import com.vianny.dverivariant.config.MinioConfig;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class FileTransferService {
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
}
