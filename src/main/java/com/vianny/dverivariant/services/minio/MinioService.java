package com.vianny.dverivariant.services.minio;

import com.vianny.dverivariant.config.MinioConfig;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioService {
    private MinioConfig minioConfig;

    @Autowired
    public void setMinioConfig(MinioConfig minioConfig) {
        this.minioConfig = minioConfig;
    }

    public void createBucket(String title) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (!minioConfig.minioClient().bucketExists(BucketExistsArgs.builder().bucket(title).build())) {
            minioConfig.minioClient().makeBucket(
                    MakeBucketArgs
                            .builder()
                            .bucket(title)
                            .build()
            );
        }
    }

    public void removeObject(String pathImage) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket("dveri-images")
                .object(pathImage)
                .build();

        minioConfig.minioClient().removeObject(removeObjectArgs);
    }
}
