-- MySQL
Create Database if not exists LibraryDatabase;
Use LibraryDatabase;

-- Tabela Users
CREATE TABLE IF NOT EXISTS Users(
user_name varchar(25) not null,
user_pass varchar(45) not null,
user_type varchar(10) not null,

Primary Key(user_name))
ENGINE = InnoDB;

-- INSERT ne Users
          
-- INSERT ne Users
INSERT into Users(user_name, user_pass, user_type)
Values('admin', '1234','admin'),
	  ('user', '1234', 'user');

-- i pari qe krijohet meniher automatic
INSERT into Users(user_name, user_pass, user_type)
SELECT * FROM (SELECT 'gentrit', '1234', 'admin') AS tmp
WHERE NOT EXISTS (
    SELECT user_name FROM Users WHERE user_name = 'gentrit'
) LIMIT 1;

-- Tabela Lexuesit
CREATE TABLE IF NOT EXISTS Lexuesit (
EmriMbiemri varchar(50) NOT NULL,
Profesioni varchar(40) NOT NULL,
Adresa varchar(255) NOT NULL,
Sektori varchar(40) NOT NULL,
Cmimi INT NOT NULL,
Regjistrimi Date NOT NULL,
Skadimi Date NOT NULL,
Primary Key(EmriMbiemri))
ENGINE = InnoDB;

-- INSERT ne Lexuesit
INSERT into Lexuesit(EmriMbiemri, Profesioni, Adresa, Sektori, Cmimi, Regjistrimi, Skadimi)
SELECT * FROM (SELECT 'Gentrit Ibishi', 'Student - FIEK','Deshmoret e Kombit', 'Kati i pare', 10, '2020-05-11', '2021-05-12') AS tmp
WHERE NOT EXISTS (
    SELECT EmriMbiemri FROM Lexuesit WHERE EmriMbiemri = 'Gentrit Ibishi'
) LIMIT 1;

INSERT into Lexuesit(EmriMbiemri, Profesioni, Adresa, Sektori, Cmimi, Regjistrimi, Skadimi)
SELECT * FROM (SELECT 'Mirsad Dibrani', 'Student - FIEK','Fushe Kosove', 'Kati i pare', 10, '2020-01-01', '2021-01-02') AS tmp
WHERE NOT EXISTS (
    SELECT EmriMbiemri FROM Lexuesit WHERE EmriMbiemri = 'Mirsad Dibrani'
) LIMIT 1;


-- Tabela per Regjistrimin e librave
CREATE TABLE IF NOT EXISTS RegjistrimiLibrave (
Emri_Librit_Autori varchar(50) NOT NULL,
Viti_Botimit int not null,
ISBNKodi LONG NOT NULL,
Sasia int null,
Primary Key(Emri_Librit_Autori))
ENGINE = InnoDB;

-- INSERT ne RegjistrimiLibrave
      
INSERT into RegjistrimiLibrave(Emri_Librit_Autori, Viti_Botimit, ISBNKodi, Sasia)
SELECT * FROM (SELECT 'Plaku dhe Deti, Ernest Hemingway', 1951, 9781559946360, 5) AS tmp
WHERE NOT EXISTS (
    SELECT Emri_Librit_Autori FROM RegjistrimiLibrave WHERE Emri_Librit_Autori = 'Plaku dhe Deti, Ernest Hemingway'
) LIMIT 1;

INSERT into RegjistrimiLibrave(Emri_Librit_Autori, Viti_Botimit, ISBNKodi, Sasia)
SELECT * FROM (SELECT 'Keshtjella, Ismail Kadare', 1970, 991559946000, 5) AS tmp
WHERE NOT EXISTS (
    SELECT Emri_Librit_Autori FROM RegjistrimiLibrave WHERE Emri_Librit_Autori = 'Keshtjella, Ismail Kadare'
) LIMIT 1;


-- Tabela per mbajtesine e Librit
CREATE TABLE IF NOT EXISTS MbajtesitLibrave (
EmriMbiemri varchar(50) NOT NULL,
Emri_Librit_Autori varchar(50) NOT NULL,
Data_Marrjes DATE NOT NULL,
Data_Kthimit DATE NOT NULL,
user_name varchar(25) not null,
foreign key(user_name) references users(user_name) on delete cascade,
foreign key(EmriMbiemri) references Lexuesit(EmriMbiemri) on delete cascade,
foreign key(Emri_Librit_Autori) references RegjistrimiLibrave(Emri_Librit_Autori) on delete cascade
)
ENGINE = InnoDB;
	    
	    
-- tabela per vendet se ku do te ulen studentet
-- nrVendi identiikues prej 1 deri 100 kati i pare , 101 - 200 kati i dyte, 201- 300 kati i trete
CREATE TABLE IF NOT EXISTS Vendet (
 nrVendi int primary key,
 EmriMbiemri varchar(50),
 Sektori varchar(40) NOT NULL,
 DataKoha timestamp,
 foreign key(EmriMbiemri) references Lexuesit(EmriMbiemri) on delete cascade
)
ENGINE = InnoDB;
 --tabela per TO DO LIST 
CREATE  TABLE todolist(
id INT (5) PRIMARY KEY AUTO_INCREMENT NOT NULL,
data DATE NOT NULL,
text VARCHAR(100) NOT NULL
);


