package Esport_Website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Esport_Website.entity.News;
import Esport_Website.service.NewsService;

@RestController
public class NewsListController {
	@Autowired
	NewsService newsService;
	
	@GetMapping("/api/home/list")
    public Page<News> getNewsList(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size
    ) {
        PageRequest pageable = PageRequest.of(page, size);
        return newsService.getList(pageable);
    }
	
	@GetMapping("/api/hotNews")
	public List<News> getHotNew(){
		
		List<News> hotNews = newsService.getHotNews();
		
		return hotNews;
	}
	
}
