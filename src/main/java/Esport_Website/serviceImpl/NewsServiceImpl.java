package Esport_Website.serviceImpl;

import java.util.Date;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import Esport_Website.DAO.CategoryDAO;
import Esport_Website.DAO.NewsDAO;
import Esport_Website.DAO.ReactDAO;
import Esport_Website.DAO.UsersDAO;
import Esport_Website.dto.CreateNewsRequest;
import Esport_Website.dto.NewsWithDetailDTO;
import Esport_Website.entity.Category;
import Esport_Website.entity.News;
import Esport_Website.entity.NewsDetail;
import Esport_Website.entity.Users;
import Esport_Website.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService{
	@Autowired
	NewsDAO dao;
	
	@Autowired
	ReactDAO rdao;
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	UsersDAO usersDAO;

	@Override
	public List<News> getALL() {
		return dao.findAll();
	}
	
	@Override
    public NewsWithDetailDTO getNewsWithDetail(Integer newsId) {
        News news = dao.findById(newsId).orElse(null);
        int likeCount = rdao.countByNews_NewsId(newsId).orElse(0);
        if (news == null) return null;

        if(news.getStatus().equals("published")) {
        	news.setViews(news.getViews() + 1);
            if(news.getRemainingPoint()>0) {
            	news.setRemainingPoint(news.getRemainingPoint()-1);
            }
            dao.save(news);
        }

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
	
	@Override
	public Page<News> getPendingArticles(Pageable pageable) {
		return dao.findAllByStatusOrderByRemainingPointDesc("pending", pageable);
	}
	
	@Override
	public News createNews(CreateNewsRequest request) {
		// Validate required fields
		if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
			throw new RuntimeException("Tiêu đề không được để trống");
		}
		if (request.getCategoryId() == null) {
			throw new RuntimeException("Danh mục không được để trống");
		}
		if (request.getAuthorId() == null) {
			throw new RuntimeException("Tác giả không được để trống");
		}
		if (request.getDetails() == null || request.getDetails().isEmpty()) {
			throw new RuntimeException("Nội dung không được để trống");
		}
		
		// Get category and author
		Category category = categoryDAO.findById(request.getCategoryId())
			.orElseThrow(() -> new RuntimeException("Danh mục không tồn tại"));
		Users author = usersDAO.findById(request.getAuthorId())
			.orElseThrow(() -> new RuntimeException("Tác giả không tồn tại"));
		
		// Create news
		int paidPoint = request.getPaidPoint() != null ? request.getPaidPoint() : 0;
		// Bổ sung: Trừ điểm của user nếu đủ điểm
		if (paidPoint > 0) {
			if (author.getRemainingPoint() == null) author.setRemainingPoint(0);
			if (author.getRemainingPoint() < paidPoint) {
				throw new RuntimeException("Bạn không đủ điểm để gửi đơn này!");
			}
			author.setRemainingPoint(author.getRemainingPoint() - paidPoint);
			usersDAO.save(author);
		}
		News news = News.builder()
			.title(request.getTitle())
			.subtitle(request.getSubtitle())
			.thumbnail(request.getThumbnail())
			.category(category)
			.author(author)
			.status(request.getStatus() != null ? request.getStatus() : "draft")
			.paidPoint(paidPoint)
			.remainingPoint(paidPoint)
			.build();
		
		// Save news first to get the ID
		News savedNews = dao.save(news);
		
		// Create news details
		List<NewsDetail> newsDetails = request.getDetails().stream()
			.map(detail -> {
				NewsDetail newsDetail = new NewsDetail();
				newsDetail.setNews(savedNews);
				newsDetail.setDetail(detail);
				newsDetail.setDetailsNumber(request.getDetails().indexOf(detail) + 1);
				return newsDetail;
			})
			.collect(Collectors.toList());
		
		// Set news details
		savedNews.setNewsDetails(newsDetails);
		
		// Save the news with details
		return dao.save(savedNews);
	}

	@Override
	public News updateNewsStatus(Integer newsId, String status) {
		News news = dao.findById(newsId).orElseThrow(() -> new RuntimeException("Không tìm thấy bài viết"));
		news.setStatus(status);
		return dao.save(news);
	}
}
