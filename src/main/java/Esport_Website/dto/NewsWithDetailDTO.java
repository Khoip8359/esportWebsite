package Esport_Website.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class NewsWithDetailDTO {
    private Integer newsId;
    private String title;
    private String subtitle;
    private String thumbnail;
    private int views;
    private int likes;
    private Date createdDate;
    private String authorName;
    private List<String> details;
}