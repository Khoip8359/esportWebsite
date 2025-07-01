package Esport_Website.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CommentRequest {
    private Integer userId;
    private Integer newsId;
    private String commentDetail;
    private Date date;
}