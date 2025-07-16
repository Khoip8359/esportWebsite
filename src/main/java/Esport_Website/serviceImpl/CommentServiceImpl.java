package Esport_Website.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import Esport_Website.DAO.CommentDAO;
import Esport_Website.DAO.NewsDAO;
import Esport_Website.DAO.UsersDAO;
import Esport_Website.dto.CommentRequest;
import Esport_Website.entity.Comment;
import Esport_Website.entity.News;
import Esport_Website.entity.Users;
import Esport_Website.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	CommentDAO dao;
	
	@Autowired
	UsersDAO udao;
	
	@Autowired
	NewsDAO ndao;

	@Override
	public List<Comment> findAllByNewsId(Integer newsId) {
		return dao.findAllByNews_NewsIdOrderByDateDesc(newsId);
	}

	@Override
	public Comment addComment(CommentRequest comment) {
		Users user = udao.findById(comment.getUserId())
        .orElseThrow(() -> new RuntimeException("User not found"));
		News news = ndao.findById(comment.getNewsId())
        .orElseThrow(() -> new RuntimeException("News not found"));
		
		Comment cmt = Comment.builder()
				.user(user)
				.news(news)
				.commentDetail(comment.getCommentDetail())
				.date(comment.getDate()).build();
		
		Comment saved = dao.save(cmt);
		logCommentAsync(user.getUserId(), news.getNewsId(), comment.getCommentDetail());
		return saved;
	}

	@Async
	public void logCommentAsync(Integer userId, Integer newsId, String detail) {
		System.out.println("[Async] User " + userId + " COMMENT news " + newsId + ": " + detail + " trên thread: " + Thread.currentThread().getName());
	}
}
