package Esport_Website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import Esport_Website.entity.Category;
import Esport_Website.service.CategoryService;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/api/categories")
	public List<Category> Category() {
		List<Category> list = categoryService.findALL();
		return list;
	}

}