drop database if exists shop03;
create database shop03;
use shop03;
create table t_user(
	id int(100) primary key auto_increment,
	username varchar(100),
	password varchar(100),
	nickname varchar(100),
	type int(10)
);
create table t_address(
	id int(100) primary key auto_increment,
	name varchar(100),
	phone int(100),
	user_id int(100),
	constraint foreign key (user_id) references t_user (id)
);
create table t_order (
	id int(100) primary key auto_increment,
	buyDate datetime,
	payDate datetime,
	confirmDate datetime,
	status int(4),
	user_id int(100),
	address_id int(100),
	constraint foreign key (user_id) references t_user (id),
	constraint foreign key (address_id) references t_address (id)
);
create table t_category(
	id int(100) primary key auto_increment,
	name varchar(100)
);
create table t_goods(
	id int(100) primary key auto_increment,
	name varchar(100),
	price double,
	intro varchar(100),
	img varchar(100),
	stock int(100),
	status int(2),
	category_id int(100),
	constraint foreign key (category_id) references t_category (id)
);

create table t_orderGoods(
	id int(100) primary key auto_increment,
	num int(100),
	goods_id int(100),
	order_id int(100),
	constraint foreign key (order_id) references t_order (id),
	constraint foreign key (goods_id) references t_goods (id)
);
grant all on shop03.* to 'wpq_shop'@'localhost' identified by '123';
