--https://spring.io/guides/gs/accessing-data-mysql/
create database db_example;
create user 'springuser' identified by 'ThePassword';
grant all on db_example.* to 'springuser'@'%';