package Esport_Website.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Esport_Website.dto.UploadResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryService {
    
    private final Cloudinary cloudinary;
    
    @Autowired
    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }
    
    public UploadResponse uploadImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        
        // Upload to Cloudinary
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
            "folder", "esport_website",
            "public_id", System.currentTimeMillis() + "_" + file.getOriginalFilename()
        ));
        
        // Return the response with URL and public ID
        String secureUrl = (String) uploadResult.get("secure_url");
        String publicId = (String) uploadResult.get("public_id");
        
        return new UploadResponse(secureUrl, publicId, "Upload successful", true);
    }
    
    @Async
    public void deleteImageAsync(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            System.out.println("[Async] Đã xóa ảnh: " + publicId + " trên thread: " + Thread.currentThread().getName());
        } catch (IOException e) {
            System.err.println("[Async] Lỗi xóa ảnh: " + publicId);
        }
    }

    public int deleteMultipleImages(List<String> publicIds) {
        if (publicIds == null || publicIds.isEmpty()) {
            return 0;
        }
        int deletedCount = 0;
        for (String publicId : publicIds) {
            // Gọi async thay vì đồng bộ
            deleteImageAsync(publicId);
            deletedCount++;
        }
        return deletedCount;
    }
} 