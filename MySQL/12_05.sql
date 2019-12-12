-----------------------------------------------------------------------
1、D	2、C	3、A	4、C

--1. 新增貂蝉同学的借阅记录：诗经，从2019年9月25日17:50到2019年10月25日17:50
insert into borrow_info (book_id, student_id, start_time, end_time) values
	(10, 3, '2019-09-25 17:50:00', '2019-10-25 17:50:00');

--2. 查询计算机分类下的图书借阅信息
select bi.*, cat.name from borrow_info bi 
	join category cat on cat.name = '计算机';

--3. 修改图书《深入理解Java虚拟机》的价格为61.20
update book set price = 61.20 where name = '深入理解Java虚拟机';

--4. 删除id最大的一条借阅记录
delete from borrow_info order by id desc limit 1;

------------------------------------------------------------------------




------------------------
1、B	2、A	3、C
------------------------





--------------------------------------------------------------
1、B

--1、设计一个考勤系统，包含员工表，考勤记录表
create table employee(
	id int primary key auto_increment,
	name varchar(20)
);

create table attendance(
	id int primary key auto_increment,
	start_time timestamp default null,
	end_time timestamp default null,
	employee_id int,
	foreign key(employee_id) references employee(id)
);


--2、设计一个学校宿舍管理系统，要求包含宿舍信息，学生信息，每日的宿舍查房记录。
create table dormitory(
	id int primary key auto_increment,
	name varchar(20)
);

drop table if exists student;
create table student(
	id int primary key auto_increment,
	name varchar(20),
	dormitory_id int,
	foreign key(dormitory_id) references dormitory(id)
);

create table check_dorm(
	id int primary key auto_increment,
	check_time timestamp,
	dormitory_id int,
	foreign key(dormitory_id) references dormitory(id)
);



--3、设计一个车辆违章系统，包含用户表，车辆表，违章信息表。违章信息表中包含用户和车辆的违章信息
create table users(
	id int primary key auto_increment,
	name varchar(20)
);

create table cars(
	id int primary key,
	model varchar(30),
	users_id int,
	foreign key(users_id) references users(id)
);

create table violation(
	id int primary key auto_increment,
	vio_time timestamp,
	users_id int,
	cars_id int,
	description varchar(30),
	foreign key(users_id) references users(id),
	foreign key(cars_id) references cars(id)
);


insert into users(name) values 
	('赵云'), 
	('关羽');
insert into cars values 
	(1, '白马', 1),
	(2, '赤兔', 2);
insert into violation(vio_time, users_id, cars_id) values 
	('2019-12-12 14:45:00', 2, 2);
--4、设计一个学校食堂管理系统，包含食堂表，食堂仓口表，仓口收费记录表
create table restaurant(
	id int primary key auto_increment,
	name varchar(20)
);

create table window(
	id int primary key auto_increment,
	food varchar(20),
	res_id int,
	foreign key(res_id) references restaurant(id)
);

create table charge_info(
	id int primary key auto_increment,
	price decimal(4,2),
	res_id int,
	win_id int,
	foreign key(res_id) references restaurant(id),
	foreign key(win_id) references window(id)
);
-------------------------------------------------------------











