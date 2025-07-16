package Esport_Website.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import Esport_Website.DAO.NewsDAO;
import Esport_Website.DAO.ReactDAO;
import Esport_Website.DAO.UsersDAO;
import Esport_Website.dto.ReactRequest;
import Esport_Website.entity.News;
import Esport_Website.entity.React;
import Esport_Website.entity.Users;
import Esport_Website.service.ReactService;

@Service
public class ReactServiceImpl implements ReactService{
	
	@Autowired
	ReactDAO dao;
	
	@Autowired
	UsersDAO udao;

	@Autowired
	NewsDAO ndao;

	@Override
	public React changeReact(ReactRequest request) {
		Users user = udao.findById(request.getUser_id())
		        .orElseThrow(() -> new RuntimeException("User not found"));
		News news = ndao.findById(request.getNews_id())
		        .orElseThrow(() -> new RuntimeException("News not found"));
		
		Optional<React> check = dao.findByUser_UserIdAndNews_NewsId(request.getUser_id(), request.getNews_id());
		
		if(!check.isPresent()) {
			React react = React.builder().user(user).news(news).build();
			logReactAsync(user.getUserId(), news.getNewsId(), true);
			return dao.save(react);
		}else {
			dao.deleteById(check.get().getReactId());
			logReactAsync(user.getUserId(), news.getNewsId(), false);
			return null;
		}
	}

	@Override
	public Optional<React> checkReact(ReactRequest request) {
		Optional<React> check = dao.findByUser_UserIdAndNews_NewsId(request.getUser_id(), request.getNews_id());
		return check;
	}

	@Async
	public void logReactAsync(Integer userId, Integer newsId, boolean isLike) {
		String action = isLike ? "LIKE" : "UNLIKE";
		System.out.println("[Async] User " + userId + " " + action + " news " + newsId + " trên thread: " + Thread.currentThread().getName());
	}
}
