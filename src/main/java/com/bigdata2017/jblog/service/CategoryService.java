package com.bigdata2017.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigdata2017.jblog.repository.CategoryDao;
import com.bigdata2017.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public boolean deleteCategory(CategoryVo categoryVo) {
		return categoryDao.deleteCategory(categoryVo) == 1;
	}
	
	public boolean createCategory(CategoryVo categoryVo) {
		return categoryDao.createCategory(categoryVo) == 1;
	}
	
	public List<CategoryVo> getCategoryList(String id) {
		return categoryDao.getCategoryList(id);
	}
	
	public List<CategoryVo> getCategoryNameList(String id) {
		return categoryDao.getCategoryNameList(id);
	}
}
