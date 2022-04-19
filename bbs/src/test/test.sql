create table board(
    bno int auto_increment primary key,
    title varchar(150),
    content varchar(2000),
    writer varchar(50),
    regdate timestamp default now(),
    updatedate timestamp default now()
);

insert into board(title, content, writer) values ('테스트 제목', '테스트 내용', '작가');
insert into board(title, content, writer) values ('테스트 제목', '테스트 내용', '작가');
insert into board(title, content, writer) values ('테스트 제목', '테스트 내용', '작가');