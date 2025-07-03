package Esport_Website.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		return dao.save(newsLetter);
	}	
	
}
