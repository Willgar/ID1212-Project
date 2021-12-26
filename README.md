# ID1212-Project
A Calculator to optimize and reduce tax for moving funds between a Swedish Fund Account to ISK. Wrapped in a interactive Web Application using Spring. 

**Heroku Link:** https://fundtoiskcalculator.herokuapp.com/
![ID1212  (1)](https://user-images.githubusercontent.com/28623935/144249133-52ce1d19-87b2-4437-8434-25e95798353b.png)

# Database
![logical diagram](https://user-images.githubusercontent.com/62156116/145587613-08c8c01f-f693-4911-a815-146a03d207a2.png)

# SQL Code
Code to create the tables in the Schema 'project' for MySQL.
Add a file namned SECRETS.java to to folder ./src/main/java/se.kth.id1212.taxoptimization/model containing two private static String named DATABASEUSER and DATABASEPASS adjusted after your own MySQL server.

CREATE DATABASE project;

CREATE TABLE user (
 email VARCHAR(255) NOT NULL,
 first_name VARCHAR(255) NOT NULL,
 last_name VARCHAR(255) NOT NULL,
 password VARCHAR(255) NOT NULL,
 country VARCHAR(255) NOT NULL,
 city VARCHAR(255) NOT NULL,
 mobile_number VARCHAR(255) NOT NULL,
 gender VARCHAR(255) NOT NULL,
 subscribe_newsletter BOOLEAN NOT NULL
);

ALTER TABLE user ADD CONSTRAINT PK_user PRIMARY KEY (email);


CREATE TABLE session (
 session_id INT NOT NULL,
 time TIMESTAMP NOT NULL,
 location VARCHAR(255) NOT NULL,
 browser VARCHAR(255) NOT NULL,
 email VARCHAR(255) NOT NULL
);

ALTER TABLE session ADD CONSTRAINT PK_session PRIMARY KEY (session_id);


CREATE TABLE csn_input (
 input_id INT NOT NULL,
 total_loan INT NOT NULL,
 estimated_years INT NOT NULL,
 max_years INT NOT NULL,
 average_payment INT NOT NULL,
 interest_rate INT NOT NULL,
 desired_payment INT NOT NULL,
 session_id INT NOT NULL
);

ALTER TABLE csn_input ADD CONSTRAINT PK_csn_input PRIMARY KEY (input_id);


CREATE TABLE input (
 input_id INT NOT NULL,
 start_capital INT NOT NULL,
 profit_capital INT NOT NULL,
 interest_rate INT NOT NULL,
 fund_account_capital INT NOT NULL,
 isk_account_capital INT NOT NULL,
 account_difference INT NOT NULL,
 session_id INT NOT NULL
);

ALTER TABLE input ADD CONSTRAINT PK_input PRIMARY KEY (input_id);


CREATE TABLE payments (
 payment_id INT NOT NULL,
 yearly_average_minimum INT NOT NULL,
 yearly_profit INT NOT NULL,
 yearly_average_extra INT NOT NULL,
 yearly_profit_extra INT NOT NULL,
 capital_difference INT NOT NULL,
 years_from_start INT NOT NULL,
 input_id INT NOT NULL
);

ALTER TABLE payments ADD CONSTRAINT PK_payments PRIMARY KEY (payment_id, input_id);


CREATE TABLE year_basis (
 years_from_start INT NOT NULL,
 isk_account_capital INT NOT NULL,
 fund_account_capital INT NOT NULL,
 capital_difference INT NOT NULL,
 input_id INT NOT NULL
);

ALTER TABLE year_basis ADD CONSTRAINT PK_year_basis PRIMARY KEY (years_from_start, input_id);


ALTER TABLE session ADD CONSTRAINT FK_session_0 FOREIGN KEY (email) REFERENCES user (email);


ALTER TABLE csn_input ADD CONSTRAINT FK_csn_input_0 FOREIGN KEY (session_id) REFERENCES session (session_id);


ALTER TABLE input ADD CONSTRAINT FK_input_0 FOREIGN KEY (session_id) REFERENCES session (session_id);


ALTER TABLE payments ADD CONSTRAINT FK_payments_0 FOREIGN KEY (input_id) REFERENCES csn_input (input_id);


ALTER TABLE year_basis ADD CONSTRAINT FK_year_basis_0 FOREIGN KEY (input_id) REFERENCES input (input_id);


# Docker
docker build -t taxapplication:latest .

docker run -d --name taxapplication -p 8080:8080 taxapplication:latest

# Related Repositories
## https://github.com/Willgar/ID1212-API-Server

The controller makes API Requests to a local API server as seen in the link above. The server handles GET requests and makes calculations which is returned in a JSON object.

## https://github.com/Willgar/ID1212

Previous mandatory tasks from the course. 

