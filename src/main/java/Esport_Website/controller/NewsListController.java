package Esport_Website.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Esport_Website.entity.News;
import Esport_Website.service.NewsService;

@RestController
@CrossOrigin(origins = "*")
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
	
	@GetMapping("/api/news/category/{categoryId}")
	public Page<News> getCategory(@PathVariable Integer categoryId,@RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size) {
		PageRequest pageable = PageRequest.of(page, size);
        return newsService.getCategory(categoryId,pageable);
	}
	
	@GetMapping("/api/suggestNews")
	public List<News> getSuggestNews(){
		
		List<News> hotNews = newsService.getSuggestNews();
		
		return hotNews;
	}

	@GetMapping("/api/news/search")
	public Page<News> searchNews(
	    @RequestParam String keyword,
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "5") int size) {
	    
	    PageRequest pageable = PageRequest.of(page, size);
	    return newsService.searchNews(keyword, pageable);
	}
	
	@GetMapping("/api/news/by-date")
	public Page<News> getNewsByDate(
	    @RequestParam String date,
	    @RequestParam(defaultValue = "0") int page,
	    @RequestParam(defaultValue = "5") int size) {
	    
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Date parsedDate = dateFormat.parse(date);
	        PageRequest pageable = PageRequest.of(page, size);
	        return newsService.getNewsByDate(parsedDate, pageable);
	    } catch (Exception e) {
	        throw new RuntimeException("Invalid date format. Use yyyy-MM-dd", e);
	    }
	}
	
	@GetMapping("/api/news/{userId}")
	public List<News> getNewsByUser(@PathVariable Integer userId){
		
		List<News> news = newsService.getNewsByUser(userId);
		
		return news;
	}
}
