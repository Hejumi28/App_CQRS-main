drop database bd_query;
drop user user_query;
create user user_query with password '123456';
create database bd_query owner user_query;
