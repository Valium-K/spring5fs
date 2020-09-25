create user 'spring5'@'localhost' identified by 'spring5';

create database spring5fs character set=utf8;

grant all privileges on spring5fs.* to 'spring5'@'localhost';

create table spring5fs.MEMBER (
    ID int auto_increment primary key,
    EMAIL varchar(30),
    PASSWORD varchar(20),
    NAME varchar(20),
    REGDATE datetime,
    unique key (EMAIL)
) engine=innoDB character set = utf8;