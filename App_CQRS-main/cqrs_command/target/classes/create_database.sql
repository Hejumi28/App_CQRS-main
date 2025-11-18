drop database bd_command;
drop user user_command;
create user user_command with password '123456';
create database bd_command owner user_command;

