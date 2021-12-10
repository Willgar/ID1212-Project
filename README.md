# ID1212-Project
A Calculator to optimize and reduce tax for moving funds between a Swedish Fund Account to ISK. Wrapped in a interactive Web Application using Spring. 
![ID1212  (1)](https://user-images.githubusercontent.com/28623935/144249133-52ce1d19-87b2-4437-8434-25e95798353b.png)

# Database
![logical diagram](https://user-images.githubusercontent.com/62156116/145587613-08c8c01f-f693-4911-a815-146a03d207a2.png)

# SQL Code
Code to create the tables in the Schema 'project' for MySQL. 
Add a file namned SECRETS.java to to folder ./src/main/java/se.kth.id1212.taxoptimization/model containing two private static String named DATABASEUSER and DATABASEPASS adjusted after your own MySQL server.

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

# Related Repositories
## https://github.com/Willgar/ID1212-API-Server

The controller makes API Requests to a local API server as seen in the link above. The server handles GET requests and makes calculations which is returned in a JSON object.

## https://github.com/Willgar/ID1212

Previous mandatory tasks from the course. 

