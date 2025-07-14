package Esport_Website.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageManagementService {
    
    @Autowired
    private CloudinaryService cloudinaryService;
    
    /**
     * Xóa ảnh từ Cloudinary
     * @param publicId Public ID của ảnh trên Cloudinary
     * @return true nếu xóa thành công, false nếu thất bại
     */
    public boolean deleteImage(String publicId) {
        if (publicId == null || publicId.trim().isEmpty()) {
            return false;
        }
        return cloudinaryService.deleteImage(publicId);
    }
    
    /**
     * Xóa nhiều ảnh cùng lúc
     * @param publicIds Danh sách public IDs
     * @return Số lượng ảnh đã xóa thành công
     */
    public int deleteMultipleImages(List<String> publicIds) {
        if (publicIds == null || publicIds.isEmpty()) {
            return 0;
        }
        
        int deletedCount = 0;
        for (String publicId : publicIds) {
            if (cloudinaryService.deleteImage(publicId)) {
                deletedCount++;
            }
        }
        return deletedCount;
    }
    
    /**
     * Kiểm tra xem URL có phải từ Cloudinary không
     * @param imageUrl URL của ảnh
     * @return true nếu là URL từ Cloudinary
     */
    public boolean isCloudinaryUrl(String imageUrl) {
        return imageUrl != null && imageUrl.contains("res.cloudinary.com");
    }
    
    /**
     * Trích xuất public ID từ Cloudinary URL
     * @param cloudinaryUrl URL từ Cloudinary
     * @return Public ID hoặc null nếu không phải URL Cloudinary
     */
    public String extractPublicId(String cloudinaryUrl) {
        if (!isCloudinaryUrl(cloudinaryUrl)) {
            return null;
        }
        
        try {
            // URL format: https://res.cloudinary.com/cloud_name/image/upload/v1234567890/folder/public_id
            String[] parts = cloudinaryUrl.split("/upload/");
            if (parts.length < 2) {
                return null;
            }
            
            String afterUpload = parts[1];
            // Loại bỏ version nếu có
            if (afterUpload.contains("/v")) {
                afterUpload = afterUpload.substring(afterUpload.indexOf("/", 1) + 1);
            }
            
            // Loại bỏ extension
            int lastDotIndex = afterUpload.lastIndexOf(".");
            if (lastDotIndex > 0) {
                afterUpload = afterUpload.substring(0, lastDotIndex);
            }
            
            return afterUpload;
        } catch (Exception e) {
            return null;
        }
    }
} 