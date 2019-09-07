create table knowledge(
    id int primary key auto_increment,
    no varchar(20),
    name varchar(100),
    createtime datetime,
    sysuser_id int
);

insert into knowledge(no, name, createtime, sysuser_id) values('a', 'IO', '2019-09-06 12:06:45', 1);
insert into knowledge(no, name, createtime, sysuser_id) values('b', '多线程', '2019-09-06 12:06:45', 1);
insert into knowledge(no, name, createtime, sysuser_id) values('c', '数据库', '2019-09-06 12:06:45', 1);
insert into knowledge(no, name, createtime, sysuser_id) values('d', 'docker', '2019-09-06 12:06:45', 1);
insert into knowledge(no, name, createtime, sysuser_id) values('e', 'linux', '2019-09-06 12:06:45', 1);
insert into knowledge(no, name, createtime, sysuser_id) values('f', 'mybatis', '2019-09-06 12:06:45', 1);