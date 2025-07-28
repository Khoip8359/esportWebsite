package Esport_Website.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import Esport_Website.service.EmailService;
import Esport_Website.service.LatestNewsService;
import Esport_Website.service.NewsletterRecipientService;

import java.util.List;
import jakarta.mail.MessagingException;

@Component
public class NewsletterScheduler {
    @Autowired
    private EmailService emailService;

    @Autowired
    private NewsletterRecipientService recipientService;

    @Autowired
    private LatestNewsService latestNewsService;

    // Gửi lúc 6h và 18h mỗi ngày
    @Scheduled(cron = "0 0 6,18 * * ?", zone = "Asia/Ho_Chi_Minh")
    public void sendDailyNewsletter() {
        List<String> emails = recipientService.getAllEmails();
        String subject = "Bản tin Esport mới nhất";
        String htmlContent = latestNewsService.getTop3NewsHtmlContent();
        try {
            emailService.sendHtmlEmailBcc(emails, subject, htmlContent);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
} 