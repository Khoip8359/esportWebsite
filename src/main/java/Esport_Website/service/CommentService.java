package Esport_Website.service;

import java.util.List;

import Esport_Website.dto.CommentRequest;
import Esport_Website.entity.Comment;

public interface CommentService {
	List<Comment> findAllByNewsId(Integer newsId);
	
	Comment addComment(CommentRequest comment);
}
