package Esport_Website.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Esport_Website.DAO.NewsDAO;
import Esport_Website.entity.News;
import Esport_Website.entity.NewsDetail;
import Esport_Website.entity.NewsWithDetailDTO;
import Esport_Website.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService{
	@Autowired
	NewsDAO dao;

	@Override
	public List<News> getALL() {
		return dao.findAll();
	}
	
	@Override
    public NewsWithDetailDTO getNewsWithDetail(Integer newsId) {
        News news = dao.findById(newsId).orElse(null);
        if (news == null) return null;

        NewsWithDetailDTO dto = new NewsWithDetailDTO();
        dto.setNewsId(news.getNewsId());
        dto.setTitle(news.getTitle());
        dto.setSubtitle(news.getSubtitle());
        dto.setThumbnail(news.getThumbnail());
        dto.setCreatedDate(news.getCreatedDate());
        dto.setAuthorName(news.getAuthor().getName());

        List<String> detailList = news.getNewsDetails()
            .stream()
            .map(NewsDetail::getDetail)
            .collect(Collectors.toList());

        dto.setDetails(detailList);
        return dto;
    }

	@Override
	public Page<News> getList(Pageable pageable) {
		return dao.findAllByOrderByRemainingPointDesc(pageable);
	}

	@Override
	public List<News> getHotNews() {
		return dao.findTop5ByOrderByViewsDesc();
	}

	@Override
	public Page<News> getCategory(Integer categoryId,Pageable pageable) {
		return dao.findByCategory_CategoryId(categoryId, pageable);
	}
	

}
