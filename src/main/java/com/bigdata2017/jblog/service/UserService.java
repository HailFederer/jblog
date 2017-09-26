package com.bigdata2017.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bigdata2017.jblog.repository.UserDao;
import com.bigdata2017.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public boolean join(UserVo userVo) {
		return userDao.insert(userVo) == 1;
	}
	
	public UserVo getUser(String id, String password) {
		
		UserVo userVo = new UserVo();
		userVo.setId(id);
		userVo.setPassword(password);
		
		userVo = userDao.getUser(userVo);
		
		return userVo;
	}
}
