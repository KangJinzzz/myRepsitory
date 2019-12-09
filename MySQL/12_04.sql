
create table if not exists goods(
	id int primary key auto_increment,
	name varchar(30),
	price decimal(11, 2),
	num int,
	desciption varchar(50)
);

create table if not exists teacher(
	id int primary key auto_increment,
	name varchar(20),
	height decimal(3, 2),
	weight decimal(3, 2),
	sex bit,
	education varchar(20),
	birthday timestamp,
	id_card varchar(20)
);

create table books(
	id int primary key auto_increment,
	name varchar(20),
	author varchar(20),
	price decimal(5, 2),
	category varchar(30)
);


--1、在以上创建的商品表中（上次作业）插入一条数据：名称为“学生书包”、价格18.91、库存101、描述为空
insert into goods (name, price, num) values
	('学生书包', 18.91, 101);


--2、修改所有库存大于30的商品记录，将价格增加50块
update goods set price = price+50 where num > 30;


--3、删除商品表中，价格大于60，或者是库存小于200的记录
delete from goods where price > 60 or num < 200;


--4、在图书表中新增一条记录：Java核心技术、作者“Cay S. Horstman”，价格56.43，分类为“计算机技术”
insert into books (name, author, price, category) values
	('Java核心技术', 'Cay S. Horstman', 56.43, '计算机技术');


--5、修改“Java核心技术”的图书信息，将价格修改为61
update books set price = 61 where name = 'Java核心技术';


--1、student学生表中，字段有姓名name，年龄age，要求查询姓张，并且年龄在18到25岁之间的学生
select * from student where name like '张%' and age between 18 and 25;


--2、查询article文章表中，发表日期create_date在2019年1月1日上午10点30分至2019年11月10日下午4点2分的文章
select * from article where create_date between '2019-01-01 10:30:00' and '2019-11-10 14:02:00';


--3、查询article文章表中，文章标题title为空，或者满足发表日期create_date在2019年1月1日之后
select * from article where tittle is null or create_date > '2019-01-01 00:00:00';


--4、查询book图书表中，作者author列不为空，或者满足条件：价格price在50元以上且出版日期publish_date在2019年之后的图书信息
select * from author is not null or (price > 50 and publish_date > '2019-01-01 00:00:00');


--5、查询用户user表中，满足以下条件的用户数据：
--ID在1至200或300至500，且账号accout列不为空
--充值金额amount在1000以上。
select * from user where (id between 1 and 200 or id between 300 and 500) and accout is not null and amount > 1000;
