package Esport_Website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import Esport_Website.dto.ChatMessage;
import Esport_Website.entity.Help;
import Esport_Website.entity.HelpDetail;
import Esport_Website.entity.Users;
import Esport_Website.service.HelpDetailService;
import Esport_Website.service.HelpService;
import Esport_Website.service.UsersService;

@Controller
public class ChatController {
    @Autowired
    private HelpService helpService;
    @Autowired
    private HelpDetailService helpDetailService;
    @Autowired
    private UsersService usersService;

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage message) {
        // Tạo merge từ userId1 và userId2 (sắp xếp tăng dần)
        int minId = Math.min(message.getUserId1(), message.getUserId2());
        int maxId = Math.max(message.getUserId1(), message.getUserId2());
        String merge = minId + "-" + maxId;
        
        // Tìm Help theo merge, nếu chưa có thì tạo mới
        Help help = helpService.findByMerge(merge);
        if (help == null) {
            help = new Help();
            Users sender = usersService.getUserById(message.getUserId1());
            Users receiver = usersService.getUserById(message.getUserId2());
            help.setSender(sender);
            help.setReceiver(receiver);
            help.setMerge(merge);
            helpService.save(help);
        }
        
        // Kiểm tra quyền truy cập: chỉ cho phép user thuộc hội thoại gửi tin nhắn
        if (!isUserInConversation(message.getUserId1(), message.getUserId2(), help)) {
            // Trả về tin nhắn lỗi hoặc bỏ qua
            return null;
        }
        
        HelpDetail detail = new HelpDetail();
        detail.setHelp(help);
        detail.setHelpTime(new java.util.Date());
        detail.setDetail(message.getText());
        detail.setSender(message.getSender()); // Sử dụng trường sender từ ChatMessage
        helpDetailService.save(detail);
        return message;
    }
    
    // Kiểm tra xem user có thuộc hội thoại không
    private boolean isUserInConversation(Integer userId1, Integer userId2, Help help) {
        // Kiểm tra xem user gửi tin nhắn có phải là sender hoặc receiver không
        return (help.getSender().getUserId().equals(userId1) && help.getReceiver().getUserId().equals(userId2)) ||
               (help.getSender().getUserId().equals(userId2) && help.getReceiver().getUserId().equals(userId1));
    }
} 