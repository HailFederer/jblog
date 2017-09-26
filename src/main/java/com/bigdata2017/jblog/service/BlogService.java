package com.bigdata2017.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigdata2017.jblog.repository.BlogDao;
import com.bigdata2017.jblog.vo.BlogVo;

@Service
public class BlogService {

	@Autowired
	private BlogDao blogDao;
	
	public boolean createBlog(BlogVo blogVo) {
		return blogDao.createBlog(blogVo) == 1;
	}
	
	public BlogVo getUser(String id) {
		return blogDao.getUser(id);
	}
	
	public int modifyBasic(BlogVo blogVo) {
		int count = blogDao.modifyBasic(blogVo);
		return count;
	}
}
