// package shop.mtcoding.s3_sample2.core.util;

// import java.io.ByteArrayInputStream;
// import java.io.IOException;
// import java.util.Base64;

// import org.springframework.web.multipart.MultipartFile;

// public class S3Util {
// public MultipartFile convertBase64ToMultipartFile(String base64String, String
// fileName) throws IOException {
// byte[] decodedBytes = Base64.getDecoder().decode(base64String);
// ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes);
// return new MockMultipartFile(fileName, fileName, null, inputStream);
// }
// }
