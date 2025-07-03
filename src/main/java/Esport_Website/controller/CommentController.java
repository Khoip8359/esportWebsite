package Esport_Website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Esport_Website.dto.CommentRequest;
import Esport_Website.entity.Comment;
import Esport_Website.service.CommentService;

@RestController
@CrossOrigin(origins = "*")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@GetMapping("/api/comments")
	public List<Comment> getComment(@RequestParam Integer newsId){
		
		List<Comment> comments = commentService.findAllByNewsId(newsId);
		
		return comments;
	}
	
	@PostMapping("/api/comments/add")
	public ResponseEntity<?> addComment(@RequestBody CommentRequest comment) {
		try {
			Comment cmt = commentService.addComment(comment); 
            return ResponseEntity.ok(cmt);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
	}
}
