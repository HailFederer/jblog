package com.bigdata2017.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bigdata2017.jblog.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(UserVo userVo) {
		int count = sqlSession.insert("user.insert", userVo);
		return count;
	}
	
	public UserVo getUser(UserVo userVo) {
		UserVo authUser = sqlSession.selectOne("user.getByIdAndPassword", userVo);
		return authUser;
	}
}
