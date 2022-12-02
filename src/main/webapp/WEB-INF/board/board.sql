show tables;

create table board (
	idx int  not null auto_increment,  /* 게시글의 고유번호 */
	nickName varchar(20)  not null,    /* 게시글의 게시자 닉네임 */
	title		 varchar(100) not null,    /* 게시글의 글 제목 */
	email 	 varchar(50),							 /* 글쓴이의 메일주소(회원가입시 필수 입력처리 되어 있다) */
	homePage varchar(50),							 /* 글쓴이의 홈페이지(블로그)주소 */
	content  text not null,						 /* 글 내용 */	
	wDate		 datetime default now(),	 /* 글 올린 날짜 */
	hostIp	 varchar(50) not null,		 /* 글쓴이 접속 IP */ 
	readNum	 int default 0,						 /* 글 조회수 */
	good		 int default 0,						 /* 좋아요 클릭 횟수 누적하기 */
	mid			 varchar(20) not null,		 /* 회원 아이디(게시글 전체 조회시 사용) */
	primary key(idx)
);

desc board;

insert into board values (default,'관리맨','게시판 서비스를 시작합니다.','gy7922@hanmail.net','www.naver.com','이곳은 게시판입니다.',default,'192.168.50.216',default,default,'admin');

select * from board;

/* 게시판에 댓글 달기 */
create table boardReply (
	idx  int not null auto_increment,  /* 댓글의 고유번호 */
	boardIdx int not null,         		 /* 원본글(부모글)의 고유번호(외래키로 지정) */
	mid varchar(20) not null,          /* 댓글 올린이의 아이디 */
	nickName varchar(20) not null,     /* 댓글 올린이의 닉네임 */
	wDate		datetime default now(),		 /* 댓글 올린 날짜 */
	hostIp  varchar(50) not null,      /* 댓글 올린 pc의 ip */
	content text not null,						 /* 댓글 내용 */
	
	primary key(idx),
	foreign key(boardIdx) references board(idx) /* 폴리곤키를 보드idx에 주고 참조값은 보드테이블의 idx다 */
	/*on update cascade 	 부모랑 같이 업데이트되게하겠다 */
	/*on delete restrict   부모키를 삭제하지못하게? */
);

desc boardReply;

/* 날짜함수 처리 연습 */
select now();         -- now() : 오늘 날짜와 시간을 보여준다.
select year(now());   -- year() : 년도 출력
select month(now());	-- month() : 월 출력
select day(now());    -- day()	: 일 출력
select date(now());   -- date(now()) : 년-월-일
select weekday(now());-- 요일 : 0(월),1(화),2(수),3(목),4(금),5(토),6(일)
select dayofweek(now()); -- 요일 : 1(일),2(월),3(화),4(수),5(목),6(금),7(토)
select hour(now())    -- hour() : 시간
select minute(now()); -- minute() : 분
select second(now()); -- second() : 초

select year('2022-12-1');
select idx, year(wDate) from board;
select idx, day(wDate) as 날짜 from board;
select idx, wDate,weekday(wDate) from board;

/* 날짜 연산 */
-- date_add(date, interval type)
select date_add(now(), interval 1 day); -- 오늘날짜보다 +1일 출력
select date_add(now(), interval -1 day); -- 오늘날짜보다 -1일 출력
select date_add(now(), interval 10 day_hour); -- 오늘시간보다 +10시간 출력

-- date_sub(date, interval 값 type)
select date_sub(now(), inteval 1 day); -- 오늘 날짜보다 -1일 출력
select date_sub(now(), inteval -1 day); -- 오늘 날짜보다 +1일 출력

select idx, wDate from board;

-- 년도(2자리):%y, 년도(4자리):%Y, 월:%m, 월(영문):%M, 일:%d, 시(12시간제):%h, 시(24시간제):%H, 분:%i, 초:%s
select idx, date_format(wDate, '%y-%M-%d') from board; -- %M 월을 영문 출력
select idx, date_format(wDate, '%y-%m-%d') from board; -- %m 월을 숫자 출력
select idx, date_format(wDate, '%Y-%m-%d') from board; -- %m 월을 숫자 출력
select idx, date_format(wDate, '%h-%i-%s') from board;
select idx, date_format(wDate, '%H-%I-%') from board;

-- 현재부터 한달 전의 날짜
select date_add(now(), interval -1 month);

-- 하루전 체크
select date_add(now(), interval -1 day);
select date_add(now(), interval -1 day), wDate from board;

-- 날짜차이 계산 : DATEDIFF(시작날짜, 마지막날짜)
select datediff('2022-11-30', '2022-12-01');
select datediff(now(), '2022-11-30');
select idx, wDate, datediff(now(), wDate) from board;
select idx, wDate, datediff(now(), wDate) as day_diff from board;
select *, datediff(now(), wDate) as day_diff from board;

-- 날짜차이와, 시간차이.
select timestampdiff(hour, now(), '2022-11-30');
select timestampdiff(hour, '2022-11-30', now());
select timestampdiff(hour, wDate, now()) from board;
select *, timestampdiff(hour, wDate, now()) as hour_diff from board;
select *, datediff(now(), wDate) as day_diff, timestampdiff(hour, wDate, now()) as hour_diff from board;


/* 이전글 다음글 체크 (idx와 title이 필요하다. vo에 담아서 편히 사용한다.) */
select * from board where idx < 6 order by idx desc limit 1; /* idx가 6보다 작은글들을 내림차순 limit1추가해서 이전글 하나만 .이전글 */
select * from board where idx > 6 limit 1; /* idx가 6보다 큰 것들 limit 1추가해서 다음글 하나만.. 다음글 */

