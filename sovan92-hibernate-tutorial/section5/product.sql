use mydb;

create table product(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(20),
description varchar(100),
price decimal(8,3) 
);

select * from product;