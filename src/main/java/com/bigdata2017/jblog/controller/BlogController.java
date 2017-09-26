package com.bigdata2017.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bigdata2017.jblog.service.BlogService;
import com.bigdata2017.jblog.service.CategoryService;
import com.bigdata2017.jblog.service.FileUploadService;
import com.bigdata2017.jblog.service.PostService;
import com.bigdata2017.jblog.vo.BlogVo;
import com.bigdata2017.jblog.vo.CategoryVo;
import com.bigdata2017.jblog.vo.PostVo;
import com.bigdata2017.jblog.vo.UserVo;
import com.bigdata2017.security.AuthJBlog;
import com.bigdata2017.security.AuthUser;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping( {"", "/{pathNo1}", "/{pathNo1}/{pathNo2}" } )
	public String main(
		@PathVariable String id,
		@PathVariable Optional<Long> pathNo1,
		@PathVariable Optional<Long> pathNo2,
		ModelMap modelMap) {
		
		BlogVo blogVo = blogService.getUser(id);
		modelMap.addAttribute("id", id);
		modelMap.addAttribute("title", blogVo.getTitle());
		modelMap.addAttribute("logoUrl", blogVo.getLogo());

		List<CategoryVo> categoryList = categoryService.getCategoryNameList(id);
		List<PostVo> postList = postService.getPostList(id);
		
		modelMap.addAttribute("categoryList", categoryList);
		modelMap.addAttribute("postList", postList);
		
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if( pathNo2.isPresent() ) {
			postNo = pathNo2.get();
			categoryNo = pathNo1.get();
			System.out.println("categoryNo: "+categoryNo+" postNo: "+postNo);
		} else if( pathNo1.isPresent() ){
			categoryNo = pathNo1.get();
			System.out.println("categoryNo: "+categoryNo);
		}
		
		//modelMap.putAll( blogService.getAll( id, categoryNo, postNo ) );
		return "blog/blog-main";
	}
	
	@AuthJBlog
	@RequestMapping(value={"/admin", "/admin/basic"}, method=RequestMethod.GET)
	public String basic(
			@PathVariable String id,
			ModelMap modelMap
			) {
		BlogVo blogVo = blogService.getUser(id);
		modelMap.addAttribute("id", id);
		modelMap.addAttribute("title", blogVo.getTitle());
		modelMap.addAttribute("logoUrl", blogVo.getLogo());
		
		return "blog/blog-admin-basic";
	}

	@AuthJBlog
	@RequestMapping(value={"/admin/basic"}, method=RequestMethod.POST)
	public String basic(
			@PathVariable String id,
			@RequestParam(value="logoFile", required=false, defaultValue="") MultipartFile logoFile,
			@ModelAttribute BlogVo blogVo
			) {
		String logoUrl = fileUploadService.restore(logoFile);
		blogVo.setLogo(logoUrl);
		blogVo.setId(id);
		blogService.modifyBasic(blogVo);
		
		return "redirect:/"+id;
	}

	@AuthJBlog
	@RequestMapping(value={"/admin/category"}, method=RequestMethod.GET)
	public String category(
			@PathVariable String id,
			ModelMap modelMap
			) {
		BlogVo blogVo = blogService.getUser(id);
		modelMap.addAttribute("id", id);
		modelMap.addAttribute("title", blogVo.getTitle());
		List<CategoryVo> list = categoryService.getCategoryList(id);
		modelMap.addAttribute("list", list);
		
		return "blog/blog-admin-category";
	}

	@AuthJBlog
	@RequestMapping(value={"/admin/category"}, method=RequestMethod.POST)
	public String category(
			@PathVariable String id,
			@ModelAttribute CategoryVo categoryVo
			) {
		categoryVo.setId(id);
		categoryService.createCategory(categoryVo);
		
		return "redirect:/"+id+"/admin/category";
	}

	@AuthJBlog
	@RequestMapping(value={"/admin/category/delete/{no}"}, method=RequestMethod.GET)
	public String categoryDelete(
			@PathVariable String id,
			@PathVariable Long no
			) {
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setId(id);
		categoryVo.setNo(no);
		categoryService.deleteCategory(categoryVo);
		
		return "redirect:/"+id+"/admin/category";
	}

	@AuthJBlog
	@RequestMapping(value={"/admin/write"}, method=RequestMethod.GET)
	public String write(
			@PathVariable String id,
			ModelMap modelMap
			) {
		BlogVo blogVo = blogService.getUser(id);
		modelMap.addAttribute("id", id);
		modelMap.addAttribute("title", blogVo.getTitle());
		List<CategoryVo> list = categoryService.getCategoryNameList(id);
		modelMap.addAttribute("list", list);
		
		return "blog/blog-admin-write";
	}

	@AuthJBlog
	@RequestMapping(value={"/admin/write"}, method=RequestMethod.POST)
	public String write(
			@PathVariable String id,
			@ModelAttribute PostVo postVo
			) {
		postService.createPost(postVo);
		
		return "redirect:/"+id;
	}

}




