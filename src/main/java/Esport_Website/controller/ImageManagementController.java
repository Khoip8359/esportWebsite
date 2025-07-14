package Esport_Website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Esport_Website.service.ImageManagementService;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "*")
public class ImageManagementController {
    
    @Autowired
    private ImageManagementService imageManagementService;
    
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteImage(@RequestParam String publicId) {
        try {
            boolean success = imageManagementService.deleteImage(publicId);
            if (success) {
                return ResponseEntity.ok("Image deleted successfully");
            } else {
                return ResponseEntity.badRequest().body("Failed to delete image");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting image: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/delete-multiple")
    public ResponseEntity<?> deleteMultipleImages(@RequestBody List<String> publicIds) {
        try {
            int deletedCount = imageManagementService.deleteMultipleImages(publicIds);
            return ResponseEntity.ok("Deleted " + deletedCount + " images successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting images: " + e.getMessage());
        }
    }
    
    @GetMapping("/extract-public-id")
    public ResponseEntity<?> extractPublicId(@RequestParam String imageUrl) {
        try {
            String publicId = imageManagementService.extractPublicId(imageUrl);
            if (publicId != null) {
                return ResponseEntity.ok(publicId);
            } else {
                return ResponseEntity.badRequest().body("Not a valid Cloudinary URL");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error extracting public ID: " + e.getMessage());
        }
    }
    
    @GetMapping("/is-cloudinary")
    public ResponseEntity<?> isCloudinaryUrl(@RequestParam String imageUrl) {
        try {
            boolean isCloudinary = imageManagementService.isCloudinaryUrl(imageUrl);
            return ResponseEntity.ok(isCloudinary);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error checking URL: " + e.getMessage());
        }
    }
} 