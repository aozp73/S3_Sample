package shop.mtcoding.s3_sample.controller;

import java.io.IOException;

// import java.io.IOException;

// import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.s3_sample.core.util.BASE64DecodedMultipartFile;
import shop.mtcoding.s3_sample.core.util.BASE64DecodedMultipartFile;
import shop.mtcoding.s3_sample.dto.ProfileTestDTO;
import shop.mtcoding.s3_sample.service.S3Service;

@RequiredArgsConstructor
@RestController
public class S3Controller {

    private final S3Service s3Service;

    @PostMapping("/save/company")
    public String saveCompnay(@RequestBody ProfileTestDTO profileTestDTO) throws IOException {
        System.out.println("테스트 : " + profileTestDTO.getProfile());

        s3Service.saveUploadFile(BASE64DecodedMultipartFile.convertBase64ToMultipartFile(profileTestDTO.getProfile()),
                "Court");

        return "a";
    }

    // @PostMapping("/save/player")
    // public String savePlayer(MultipartFile multipartFile)
    // throws IOException {
    // System.out.println("테스트 : ");
    // s3Service.saveUploadFile(multipartFile);

    // return "a";
    // }

    // @PostMapping("/save/stadium")
    // public String uploadStadium(MultipartFile multipartFile)
    // throws IOException {
    // System.out.println("테스트 : ");
    // s3Service.saveUploadFile(multipartFile);

    // return "a";
    // }

    // @PostMapping("/save/court")
    // public String uploadCourt(MultipartFile multipartFile)
    // throws IOException {
    // System.out.println("테스트 : ");
    // s3Service.saveUploadFile(multipartFile);

    // return "a";
    // }

}
