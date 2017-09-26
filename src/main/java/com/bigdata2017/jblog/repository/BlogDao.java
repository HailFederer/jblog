package com.bigdata2017.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bigdata2017.jblog.vo.BlogVo;

@Repository
public class BlogDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int createBlog(BlogVo blogVo) {
		
		blogVo.setTitle(blogVo.getId()+"의 님의 블로그");
		blogVo.setLogo("/bigdata/workspace/jblog/logo/images/defaultLogo.jpg");
		
		int count = sqlSession.insert("blog.createBlog", blogVo);
		return count;
	}
	
	public BlogVo getUser(String id) {
		return sqlSession.selectOne("blog.getUser", id);
	}
	
	public int modifyBasic(BlogVo blogVo) {
		int count = sqlSession.update("blog.modifyBasic", blogVo);
		return count;
	}
}
