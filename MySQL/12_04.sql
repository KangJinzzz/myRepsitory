create table if not exists goods(
	id int primary key auto_increment,
	name varchar(30),
	price decimal,
	num int,
	desciption varchar(50)
);

create table if not exists teacher(
	id int primary key auto_increment,
	name varchar(20),
	height decimal,
	weight decimal,
	sex bit,
	education varchar(20),
	birthday timestamp,
	id_card varchar(20)
);

create table books(
	id int primary key auto_increment,
	name varchar(20),
	author varchar(20),
	price decimal,
	category varchar(30)
);