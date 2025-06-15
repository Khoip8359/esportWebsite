package Esport_Website.DAO;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.News;

@Repository
public interface NewsDAO extends JpaRepository<News, Integer> {
	Page<News> findAllByOrderByRemainingPointDesc(Pageable pageable);
	Page<News> findByCategory_CategoryId(Integer categoryId, Pageable pageable);
	List<News> findTop5ByOrderByViewsDesc();
}
