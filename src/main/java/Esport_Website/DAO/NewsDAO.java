package Esport_Website.DAO;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.News;

@Repository
public interface NewsDAO extends JpaRepository<News, Integer> {
	Page<News> findAllByStatusOrderByRemainingPointDesc(String status,Pageable pageable);
	
	Page<News> findByCategory_CategoryIdAndStatus(Integer categoryId,String status, Pageable pageable);
	
	List<News> findTop5ByStatusOrderByViewsDesc(String status);
	
	List<News> findTop5ByStatusOrderByRemainingPointDesc(String status);
	
	Page<News> findByTitleContainingIgnoreCaseAndStatus(String keyword,String status, Pageable pageable);
	
	Page<News> findByCreatedDateBetween(java.util.Date startDate, java.util.Date endDate, Pageable pageable);
	
	List<News> findByAuthor_UserIdOrderByViewsDesc(Integer authorId);
	
	News findTopByOrderByCreatedDateDesc();
	
	@Query("SELECT n FROM News n LEFT JOIN FETCH n.newsDetails WHERE n.newsId = (SELECT MAX(n2.newsId) FROM News n2)")
	News findLatestNewsWithDetails();
	
	@Query("SELECT DISTINCT n FROM News n LEFT JOIN FETCH n.newsDetails Where n.Status = 'published' ORDER BY n.createdDate DESC")
	List<News> findTop3NewsWithDetails(Pageable pageable);
}
