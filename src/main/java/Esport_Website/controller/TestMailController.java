package Esport_Website.controller;

import Esport_Website.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.mail.MessagingException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/test-mail")
public class TestMailController {

    @Autowired
    private EmailService emailService;
    
    @PostMapping("/simple")
    public String sendTestMail(@RequestParam String to,
                               @RequestParam String subject,
                               @RequestParam String content) {
        emailService.sendSimpleEmail(to, subject, content);
        return "Đã gửi mail test tới: " + to;
    }

    @PostMapping("/html")
    public String sendHtmlMail(@RequestParam String to,
                               @RequestParam String subject,
                               @RequestParam String htmlContent) throws MessagingException {
        emailService.sendHtmlEmail(to, subject, htmlContent);
        return "Đã gửi mail HTML tới: " + to;
    }

    @PostMapping("/html-bcc")
    public String sendHtmlMailBcc(@RequestParam String bcc,
                                  @RequestParam String subject,
                                  @RequestParam String htmlContent) throws MessagingException {
        List<String> bccList = Arrays.asList(bcc.split(","));
        emailService.sendHtmlEmailBcc(bccList, subject, htmlContent);
        return "Đã gửi mail HTML BCC tới: " + bcc;
    }
}
