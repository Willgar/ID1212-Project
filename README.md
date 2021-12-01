# ID1212-Project
A Calculator to optimize and reduce tax for moving funds between a Swedish Fund Account to ISK. Wrapped in a interactive Web Application using Spring. 
![ID1212  (1)](https://user-images.githubusercontent.com/28623935/144249133-52ce1d19-87b2-4437-8434-25e95798353b.png)

# SQL Code
Code to create the tables in the Schema 'project' for MySQL.

CREATE TABLE `User` (
	`email` varchar(255) NOT NULL,
	`name` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	PRIMARY KEY (`email`)
);

CREATE TABLE `Session` (
	`session_id` varchar(255) NOT NULL,
	`time` TIME NOT NULL,
	`location` varchar(255) NOT NULL,
	PRIMARY KEY (`session_id`)
);

CREATE TABLE `Input` (
	`input_id` bigint NOT NULL DEFAULT '0',
	`start_capital` bigint NOT NULL DEFAULT '0',
	`profit_capital` bigint NOT NULL,
	`interest_rate` bigint NOT NULL,
	PRIMARY KEY (`input_id`)
);

ALTER TABLE `Session` ADD CONSTRAINT `Session_fk0` FOREIGN KEY (`session_id`) REFERENCES `User`(`email`);

ALTER TABLE `Input` ADD CONSTRAINT `Input_fk0` FOREIGN KEY (`input_id`) REFERENCES `Session`(`session_id`);



