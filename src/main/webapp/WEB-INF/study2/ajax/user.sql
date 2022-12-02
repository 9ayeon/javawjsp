show tables;

create table user (
	idx int not null auto_increment primary key,
	mid varchar(20)	not null,
	name varchar(20) not null,
	age int default 20,
	address varchar(50)
);

desc user;

insert into user values (default, 'admin', '관리자', 30, '청주');
insert into user values (default, '9ayeon', '가연', 27, '청주');
insert into user values (default, 'ygy232', '메롱', 11, '서울');
insert into user values (default, 'zlzl22', '키키', 17, '제주');
insert into user values (default, 'nyaong', '뭉뭉', 20, '인천');

select * from user;
select * from user order by idx desc;