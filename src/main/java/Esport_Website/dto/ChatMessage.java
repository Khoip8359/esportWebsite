package Esport_Website.dto;

public class ChatMessage {
    private Integer helpId;
    private Integer userId;
    private String from;
    private String text;

    // Getters and setters
    public Integer getHelpId() { return helpId; }
    public void setHelpId(Integer helpId) { this.helpId = helpId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
} 