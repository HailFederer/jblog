package com.bigdata2017.jblog.vo;

public class CategoryVo {

	private Long rowNum;
	private Long no;
	private String name;
	private String description;
	private String reg_date;
	private String id;
	private int postNum;

	public Long getRowNum() {
		return rowNum;
	}
	public void setRowNum(Long rowNum) {
		this.rowNum = rowNum;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPostNum() {
		return postNum;
	}
	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}
	
	@Override
	public String toString() {
		return "CategoryVo [rowNum=" + rowNum + ", no=" + no + ", name=" + name + ", description=" + description
				+ ", reg_date=" + reg_date + ", id=" + id + ", postNum=" + postNum + "]";
	}
}
