
DROP TABLE IF EXISTS desir CASCADE;
DROP TABLE IF EXISTS modele CASCADE;
DROP TABLE IF EXISTS acheteur CASCADE;

create table acheteur (
    prenom varchar(20),
    nom varchar(20),
    taille integer check (taille > 0),
    primary key (prenom, nom)
);

create table modele (
    ref integer primary key,
    createur varchar(20),
    type varchar(20) check (type in ('jupe', 'T-shirt','pantalon', 'robe')),
    taille integer check (taille > 0),
    stock integer check  (stock >= 0),
    prix integer
);
   
create table desir (
    prenom varchar(20),
    nom varchar(20),
    createur varchar(20),
    type varchar(20) check (type in ('jupe', 'T-shirt','pantalon', 'robe')),
    primary key (prenom, nom, createur, type),
    foreign key (prenom, nom) references acheteur(prenom, nom)       
);


INSERT INTO acheteur VALUES
  ('Karima','BenHamida',42),
  ('Karima','Dupond',36),
  ('Laetitia','Dupond',44),
  ('Amelia','Herrbrandt',44),
  ('Paul','Duval',40),
  ('Pauline','Truong',40),
  ('Pauline','Duval',40),
  ('Elizabeth','Jones',44),
  ('Elise','BenHamida',38),
  ('Diego','Truong',36),
  ('Isadora','Martin',38),
  ('Cecilia','Sargasy',40),
  ('Julia','Geyer',36),
  ('Brigita','Macaron',38),
  ('Francois','Trollant',44);
  
  
INSERT INTO modele VALUES
  (10038,'AgnesB','jupe',38,10,50),
  (10040,'AgnesB','jupe',38,2,50),
  (10042,'AgnesB','jupe',42,1,52),
  (10140,'LaguerFeld','robe',40,115,150),
  (10240,'SoniaRykiel','T-shirt',40,20,35),
  (10242,'SoniaRykiel','T-shirt',42,1,35),
  (10244,'SoniaRykiel','T-shirt',44,1,37),
  (10344,'SoniaRykiel','T-shirt',44,1,37),
  (10440,'Prada','T-shirt',40,1,40),
  (10441,'Prada','jupe',40,0,20),
  (10536,'Lanvin','T-shirt',36,30,30),
  (10640,'Dior','T-shirt',40,1,50),
  (10736,'AgnesB','jupe',36,0,60),
  (10738,'AgnesB','jupe',38,0,60),
  (10740,'AgnesB','jupe',40,10,65),
  (10846,'AgnesB','robe',46,20,65),
  (10936,'AgnesB','pantalon',36,13,64),
  (11036,'AgnesB','T-shirt',36,2,39);
  
INSERT INTO desir VALUES
  ('Karima','BenHamida','AgnesB','jupe'),
  ('Karima','BenHamida','LaguerFeld','robe'),
  ('Karima','Dupond','LaguerFeld','pantalon'),
  ('Laetitia','Dupond','SoniaRykiel','T-shirt'),
  ('Amelia','Herrbrandt','SoniaRykiel','T-shirt'),
  ('Paul','Duval','Prada','T-shirt'),
  ('Pauline','Duval','Prada','T-shirt'),
  ('Pauline','Duval','SoniaRykiel','T-shirt'),
  ('Elizabeth','Jones','AgnesB','jupe'),
  ('Elizabeth','Jones','AgnesB','robe'),
  ('Elizabeth','Jones','AgnesB','pantalon'),
  ('Elizabeth','Jones','AgnesB','T-shirt'),
  ('Elise','BenHamida','AgnesB','pantalon'),
  ('Diego','Truong','SoniaRykiel','T-shirt'),
  ('Diego','Truong','AgnesB','pantalon'),
  ('Cecilia','Sargasy','Prada','jupe');