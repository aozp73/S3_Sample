package shop.mtcoding.s3_sample.core.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
// import org.apache.commons.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

public class BASE64DecodedMultipartFile implements MultipartFile {
    public static MultipartFile convertBase64ToMultipartFile(String base64)
            throws IOException {
        String[] parts = base64.split(",");
        // base64Data : data:image/png;base64, 없앤 base64 String 값
        String base64Data = parts[1];
        // 하드 디스크는 이진데이터를 읽어 저장하므로 base64 문자셋 -> 이진 데이터 디코딩
        byte[] decodedData = Base64.getDecoder().decode(base64Data);
        // byte[] decodedBytes = Base64.decodeBase64(base64);
        return new BASE64DecodedMultipartFile(decodedData);
    }

    private final byte[] imgContent;

    public BASE64DecodedMultipartFile(byte[] imgContent) {
        this.imgContent = imgContent;
    }

    @Override
    public String getName() {
        return "base64.jpg";
    }

    @Override
    public String getOriginalFilename() {
        return "base64.jpg";
    }

    @Override
    public String getContentType() {
        return "image/jpeg";
    }

    @Override
    public boolean isEmpty() {
        return imgContent == null || imgContent.length == 0;
    }

    @Override
    public long getSize() {
        return imgContent.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return imgContent;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(imgContent);
    }

    @Override
    public void transferTo(File file) throws IOException, IllegalStateException {
        new FileOutputStream(file).write(imgContent);
    }
}