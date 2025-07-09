package Esport_Website.controller;

import java.util.Date;

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
        // Tìm Help theo sender (userId), nếu chưa có thì tạo mới
        Help help = helpService.findBySenderId(message.getUserId());
        if (help == null) {
            help = new Help();
            Users sender = usersService.getUserById(message.getUserId());
            help.setSender(sender);
            helpService.save(help);
        }
        HelpDetail detail = new HelpDetail();
        detail.setHelp(help);
        detail.setHelpTime(new java.util.Date());
        detail.setDetail(message.getText());
        helpDetailService.save(detail);
        return message;
    }
} 