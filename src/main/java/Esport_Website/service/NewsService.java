package Esport_Website.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Esport_Website.entity.News;
import Esport_Website.entity.NewsWithDetailDTO;

public interface NewsService {

	List<News> getALL();
	
	NewsWithDetailDTO getNewsWithDetail(Integer newId);
	
	Page<News> getList(Pageable pageable);

	List<News> getHotNews();

}
