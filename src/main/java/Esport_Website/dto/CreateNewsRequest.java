package Esport_Website.dto;

import java.util.List;
import lombok.Data;

@Data
public class CreateNewsRequest {
    private String title;
    private String subtitle;
    private String thumbnail;
    private Integer categoryId;
    private Integer authorId;
    private String status;
    private List<String> details;
    private Integer paidPoint;
} 