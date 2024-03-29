package shop.mtcoding.s3_sample.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.s3_sample.entity.UploadFile;

@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3Client amazonS3Client;

    @Value("${bucket}")
    private String bucket;

    @Transactional
    public void saveUploadFile(MultipartFile multipartFile, String keyword) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        String originalFilename = multipartFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(index + 1);

        String storeFileName = UUID.randomUUID() + "." + ext;
        String key;
        switch (keyword) {
            case "CompanyProfile":
                key = "CompanyProfile/" + storeFileName;
                break;
            case "Court":
                key = "Court/" + storeFileName;
                break;
            case "PlayerProfile":
                key = "PlayerProfile/" + storeFileName;
                break;
            case "Stadium":
                key = "Stadium/" + storeFileName;
                break;
            default:
                throw new IllegalArgumentException("Invalid keyword: " + keyword);
        }
        // String key = "test/" + storeFileName;

        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3Client.putObject(new PutObjectRequest(bucket, key, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        }

        String storeFileUrl = amazonS3Client.getUrl(bucket, key).toString();
        UploadFile uploadFile = new UploadFile(originalFilename, storeFileUrl);
    }

}
