package Esport_Website.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import Esport_Website.DAO.NewsLetterDAO;
import Esport_Website.entity.NewsLetter;
import Esport_Website.service.NewsLetterService;

@Service
public class NewsLetterServiceImpl implements NewsLetterService{

	@Autowired
	NewsLetterDAO dao;
	
	@Override
	public NewsLetter create(String email) {
		
		Optional<NewsLetter> checking = dao.findByEmail(email);
		
		if(checking.isPresent()) {
			throw new RuntimeException("Tài khoản đã tồn tại");
		}
		
		NewsLetter newsLetter = NewsLetter.builder().email(email).build();
		NewsLetter saved = dao.save(newsLetter);
		sendWelcomeEmailAsync(email);
		return saved;
	}	

	@Async
	public void sendWelcomeEmailAsync(String email) {
		// Giả lập gửi email bất đồng bộ
		System.out.println("[Async] Gửi email chào mừng tới: " + email + " trên thread: " + Thread.currentThread().getName());
	}
}
