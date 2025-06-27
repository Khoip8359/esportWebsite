package Esport_Website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import Esport_Website.dto.NewsWithDetailDTO;
import Esport_Website.service.NewsService;

@RestController
@CrossOrigin(origins = "*")
public class NewsDetailController {
	@Autowired
	NewsService newsService;
	
	@GetMapping("/api/news/detail/{newsId}")
	public NewsWithDetailDTO getDetail(@PathVariable Integer newsId) {
		NewsWithDetailDTO dto = newsService.getNewsWithDetail(newsId);
		return dto;
	}
	
}