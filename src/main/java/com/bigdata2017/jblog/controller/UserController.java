package com.bigdata2017.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bigdata2017.jblog.service.BlogService;
import com.bigdata2017.jblog.service.CategoryService;
import com.bigdata2017.jblog.service.UserService;
import com.bigdata2017.jblog.vo.BlogVo;
import com.bigdata2017.jblog.vo.CategoryVo;
import com.bigdata2017.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(
			@ModelAttribute UserVo userVo
			) {
		userService.join(userVo);
		BlogVo blogVo = new BlogVo();
		blogVo.setId(userVo.getId());
		blogService.createBlog(blogVo);
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setId(userVo.getId());
		categoryService.createCategory(categoryVo);
		return "user/joinsuccess";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
}
