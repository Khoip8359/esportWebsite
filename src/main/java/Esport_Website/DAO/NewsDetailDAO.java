package Esport_Website.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.NewsDetail;

@Repository
public interface NewsDetailDAO extends JpaRepository<NewsDetail, Integer>{

}
