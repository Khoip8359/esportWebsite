package Esport_Website.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Esport_Website.entity.Category;

@Repository
public interface CategoryDAO extends JpaRepository<Category, Integer>{
	List<Category> findByCategoryNameContainingIgnoreCase(String keyword);

}
