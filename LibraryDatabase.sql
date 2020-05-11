-- MySQL
Create Database LibraryDatabase;
Use LibraryDatabase;

-- Tabela Users
Create Table Users(
user_name varchar(25) not null,
user_pass varchar(45) not null,
user_type varchar(10) not null,
Primary Key(user_name));

-- INSERT ne Users
INSERT into Users(user_name, user_pass, user_type)
Values('admin', '1234','admin'),
	  ('user', '1234', 'user'),
      ('gentrit', '1234', 'admin');

-- Tabela Lexuesit
Create Table Lexuesit (
EmriMbiemri varchar(50) NOT NULL,
Profesioni varchar(40) NOT NULL,
Adresa varchar(255) NOT NULL,
Sektori varchar(40) NOT NULL,
Cmimi INT NOT NULL,
Regjistrimi Date NOT NULL,
Skadimi Date NOT NULL,
Primary Key(EmriMbiemri));

-- INSERT ne Lexuesit
INSERT into Lexuesit(EmriMbiemri, Profesioni, Adresa, Sektori, Cmimi, Regjistrimi, Skadimi)
Values('Gentrit Ibishi', 'Student - FIEK','Deshmoret e Kombit', 'Kati 3', 10, '2020-05-11', '2021-05-12'),
	  ('Mirsad Dibrani', 'Student - FIEK','Fushe Kosove', 'Kati 3', 10, '2020-01-01', '2021-01-02');


-- Tabela per Regjistrimin e librave
Create Table RegjistrimiLibrave (
Emri_Librit_Autori varchar(50) NOT NULL,
Viti_Botimit int not null,
ISBNKodi LONG NOT NULL,
Sasia int null,
Primary Key(Emri_Librit_Autori));

-- INSERT ne RegjistrimiLibrave
INSERT into RegjistrimiLibrave(Emri_Librit_Autori, Viti_Botimit, ISBNKodi, Sasia)
Values('Plaku dhe Deti, Ernest Hemingway', 1951, 9781559946360, 5),
	  ('Keshtjella, Ismail Kadare', 1970, 991559946000, 5);


-- Tabela per mbajtesine e Librit
Create Table MbajtesitLibrave (
EmriMbiemri varchar(50) NOT NULL,
Emri_Librit_Autori varchar(50) NOT NULL,
Data_Marrjes DATE NOT NULL,
Data_Kthimit DATE NOT NULL,
user_name varchar(25) not null,
foreign key(user_name) references users(user_name) on delete cascade,
foreign key(EmriMbiemri) references Lexuesit(EmriMbiemri) on delete cascade,
foreign key(Emri_Librit_Autori) references RegjistrimiLibrave(Emri_Librit_Autori) on delete cascade
);
