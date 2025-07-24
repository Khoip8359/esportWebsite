package Esport_Website.service;

import Esport_Website.DAO.NewsLetterDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsletterRecipientService {
    @Autowired
    private NewsLetterDAO newsLetterDAO;

    public List<String> getAllEmails() {
        return newsLetterDAO.findAll()
                .stream()
                .map(newsLetter -> newsLetter.getEmail())
                .collect(Collectors.toList());
    }
} 