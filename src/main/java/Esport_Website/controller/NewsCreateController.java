package Esport_Website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import Esport_Website.dto.CreateNewsRequest;
import Esport_Website.entity.News;
import Esport_Website.service.NewsService;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "*")
public class NewsCreateController {
	
	@Autowired
	private NewsService newsService;
	
	@PostMapping("/create")
	public ResponseEntity<?> createNews(@RequestBody CreateNewsRequest request) {
		try {
			News news = newsService.createNews(request);
			return ResponseEntity.ok(news);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/update-status/{newsId}")
	public ResponseEntity<?> updateNews(@RequestBody CreateNewsRequest request) {
		try {
			News news = newsService.createNews(request);
			return ResponseEntity.ok(news);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/update-status/{newsId}")
	public ResponseEntity<?> updateNewsStatus(@PathVariable Integer newsId, @RequestParam String status) {
		try {
			News updated = newsService.updateNewsStatus(newsId, status);
			return ResponseEntity.ok(updated);
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

} 