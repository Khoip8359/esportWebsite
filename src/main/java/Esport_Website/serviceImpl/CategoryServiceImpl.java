package Esport_Website.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Esport_Website.DAO.CategoryDAO;
import Esport_Website.entity.Category;
import Esport_Website.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	CategoryDAO cateDao;

	@Override
	public List<Category> findALL() {
		 return cateDao.findAll();
	}

}
