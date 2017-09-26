package com.bigdata2017.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bigdata2017.jblog.vo.CategoryVo;
import com.bigdata2017.jblog.vo.PostVo;

@Repository
public class PostDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int createPost(PostVo postVo) {
		int count = sqlSession.insert("post.createPost", postVo);
		return count;
	}
	
	public List<PostVo> getPostList(String id){
		return sqlSession.selectList("post.getPostList", id);
	}
}
