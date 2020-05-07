-- MySQL
Create Database LibraryDatabase;
Use LibraryDatabase;

-- Tabela Users
Create Table Users(
user_id Int Not null auto_increment,
user_name varchar(25) not null,
user_pass varchar(45) not null,
user_type varchar(10) not null,
Primary Key(user_id));

-- INSERT ne Users
INSERT into Users(user_name, user_pass, user_type)
Values('admin', '1234','admin'),
	  ('user', '1234', 'user'),
      ('gentrit', '1234', 'admin');

-- Tabela Lexuesit
Create Table Lexuesit (
Id INT NOT NULL AUTO_INCREMENT,
Emri varchar(25) NOT NULL,
Mbiemri varchar(25) NOT NULL,
Profesioni varchar(40) NOT NULL,
Adresa varchar(255) NOT NULL,
Sektori varchar(40) NOT NULL,
Cmimi INT NOT NULL,
Regjistrimi Date NOT NULL,
Skadimi Date NOT NULL,
Primary Key(Id));