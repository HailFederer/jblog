select rowNum, t.* from
		(select data.*, 
			   nvl(data2.count, 0) postNum
		  from (select no, 
		  			   name, 
		  			   nvl(description, ' ') description, 
		  			   to_char(reg_date,'YYYY-MM-DD hh:mi:ss') reg_date
		   		  from category 
		    	 where id = 'federer'
		 	  order by reg_date asc) data, 
		 	   (select category_no, 
		 	   		   count(*) count 
		 	   	  from post 
		 	  group by category_no) data2
		 where data.no = data2.category_no(+)
	  order by data.reg_date desc) t
	  ;


insert into member values('federer', 'federer', '1', sysdate);
insert into blog values('federer', 'federer의 블로그', '1212');
insert into category values (seq_category.nextval, '미분류2', '', sysdate, 'federer');
insert into post values (seq_post.nextval, 'title3', 'content3', sysdate, 9);

select * from member;
select * from blog;
select * from category;
select * from post;

delete from category where id='federer' and no=9;

delete from member;
delete from blog;
delete from category;
delete from post;

select rowNum, data.*, nvl(data2.count, 0) postNum
		  from (select no, name, nvl(description, ' ') description, reg_date 
		   		  from category 
		    	 where id = 'federer'
		 	  order by no asc) data, (select category_no, count(*) count from post group by category_no) data2
		where data.no = data2.category_no(+);

-- seq_category

drop SEQUENCE seq_category;

create SEQUENCE seq_category
start with 1
increment by 1
maxvalue 9999999999;



drop SEQUENCE seq_post;

create SEQUENCE seq_post
start with 1
increment by 1
maxvalue 9999999999;

-------------------------