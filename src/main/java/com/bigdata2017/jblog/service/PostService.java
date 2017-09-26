package com.bigdata2017.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigdata2017.jblog.repository.PostDao;
import com.bigdata2017.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	public boolean createPost(PostVo postVo) {
		return postDao.createPost(postVo) == 1;
	}
	
	public List<PostVo> getPostList(String id) {
		return postDao.getPostList(id);
	}
}
