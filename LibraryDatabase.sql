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

-- Tabela per mbajtesine e Librit
Create Table MbajtesitLibravae (
MarrjaLibritID INT NOT NULL auto_increment,
Emri varchar(25) NOT NULL,
Mbiemri varchar(25) NOT NULL,
ISBNKodi INT NOT NULL,
Emri_Librit varchar(50) NOT NULL,
Autori_Librit varchar(25) NOT NULL,
Viti_Botimit int NOT NULL,
Data_Marrjes DATE NOT NULL,
Data_Kthimit DATE NOT NULL,
user_name varchar(25) not null,
Primary Key(MarrjaLibritID),
foreign key(user_name) references Users(user_name) on delete cascade,
foreign key(Emri) references Lexuesit(Emri) on delete cascade,
foreign key(Mbiemri) references Lexuesit(Mbiemri) on delete cascade
);

-- Tabela per Regjistrimin e librave
Create Table RegjistrimiLibrave (
RegjistrimiID int auto_increment not null,
Emri_Librit varchar(50) NOT NULL,
Autori_Librit varchar(25) NOT NULL,
Viti_Botimit int not null,
ISBNKodi LONG NOT NULL,
Sasia int null,
Primary Key(RegjistrimiID));
