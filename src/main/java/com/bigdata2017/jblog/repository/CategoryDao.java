package com.bigdata2017.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bigdata2017.jblog.vo.CategoryVo;

@Repository
public class CategoryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int deleteCategory(CategoryVo categoryVo) {
		int count = sqlSession.delete("category.deleteCategory", categoryVo);
		return count;
	}
	
	public int createCategory(CategoryVo categoryVo) {
		if(categoryVo.getName() == null) {
			categoryVo.setName("미분류");
		}
		if(categoryVo.getDescription() == null) {
			categoryVo.setDescription("");
		}
		int count = sqlSession.insert("category.createCategory", categoryVo);
		return count;
	}
	
	public List<CategoryVo> getCategoryList(String id){
		return sqlSession.selectList("category.getCategoryList", id);
	}

	public List<CategoryVo> getCategoryNameList(String id){
		return sqlSession.selectList("category.getCategoryNameList", id);
	}
}
