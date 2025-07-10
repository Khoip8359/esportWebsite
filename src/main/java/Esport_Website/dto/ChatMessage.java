package Esport_Website.dto;

import lombok.Data;

@Data
public class ChatMessage {
    private Integer userId1;
    private Integer userId2;
    private String from;
    private String text;
    private String sender;
} 