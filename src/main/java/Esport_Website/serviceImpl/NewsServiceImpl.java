package Esport_Website.serviceImpl;

import java.util.Date;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Esport_Website.DAO.NewsDAO;
import Esport_Website.DAO.ReactDAO;
import Esport_Website.dto.NewsWithDetailDTO;
import Esport_Website.entity.News;
import Esport_Website.entity.NewsDetail;
import Esport_Website.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService{
	@Autowired
	NewsDAO dao;
	
	@Autowired
	ReactDAO rdao;

	@Override
	public List<News> getALL() {
		return dao.findAll();
	}
	
	@Override
    public NewsWithDetailDTO getNewsWithDetail(Integer newsId) {
        News news = dao.findById(newsId).orElse(null);
        int likeCount = rdao.countByNews_NewsId(newsId).orElse(0);
        if (news == null) return null;

        // Tăng lượt view
        news.setViews(news.getViews() + 1);
        dao.save(news);

        NewsWithDetailDTO dto = new NewsWithDetailDTO();
        dto.setNewsId(news.getNewsId());
        dto.setTitle(news.getTitle());
        dto.setSubtitle(news.getSubtitle());
        dto.setThumbnail(news.getThumbnail());
        dto.setCreatedDate(news.getCreatedDate());
        dto.setAuthorName(news.getAuthor().getName());
        dto.setViews(news.getViews());
        dto.setLikes(likeCount);

        List<String> detailList = news.getNewsDetails()
        	    .stream()
        	    .sorted(Comparator.comparing(NewsDetail::getDetailsNumber))
        	    .map(NewsDetail::getDetail)
        	    .collect(Collectors.toList());

        dto.setDetails(detailList);
        return dto;
    }

	@Override
	public Page<News> getList(Pageable pageable) {
		return dao.findAllByStatusOrderByRemainingPointDesc("published",pageable);
	}

	@Override
	public List<News> getHotNews() {
		return dao.findTop5ByStatusOrderByViewsDesc("published");
	}

	@Override
	public Page<News> getCategory(Integer categoryId,Pageable pageable) {
		return dao.findByCategory_CategoryIdAndStatus(categoryId,"published", pageable);
	}

	@Override
	public List<News> getSuggestNews() {
		return dao.findTop5ByStatusOrderByRemainingPointDesc("published");
	}

	@Override
	public Page<News> searchNews(String keyword, Pageable pageable) {
	    return dao.findByTitleContainingIgnoreCaseAndStatus(keyword,"published", pageable);
	}

	@Override
	public Page<News> getNewsByDate(Date date, Pageable pageable) {
	    // Convert LocalDate to Date range for the entire day
	    java.time.LocalDate localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
	    Date startOfDay = Date.from(localDate.atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
	    Date endOfDay = Date.from(localDate.plusDays(1).atStartOfDay(java.time.ZoneId.systemDefault()).toInstant());
	    return dao.findByCreatedDateBetween(startOfDay, endOfDay, pageable);
	}

	@Override
	public List<News> getNewsByUser(Integer userId) {
		return dao.findByAuthor_UserIdOrderByViewsDesc(userId);
	}
}
