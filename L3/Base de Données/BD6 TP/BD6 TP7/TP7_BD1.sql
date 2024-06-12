drop table if exists temps cascade;
drop table if exists etapes cascade;
drop table if exists coureurs cascade;
drop table if exists pays cascade;
drop table if exists equipestour cascade;

create table equipestour(
codeEquipe char(3) primary key,
nomEquipe varchar(25) NOT NULL,
directeurSportif varchar(25) NOT NULL 
);

create table pays(
codePays char(3) primary key,
nomPays varchar(20) NOT NULL
);

create table coureurs(
numeroCoureur integer primary key,
nomCoureur varchar(50) NOT NULL,
codeEquipe char(3) NOT NULL,
codePays char(3),
dateNaiss date,
foreign key(codeEquipe) references equipestour(CodeEquipe),
foreign key(codePays) references pays(codePays));

create table etapes(
numeroEtape integer primary key,
dateEtape date NOT NULL,
villeDepart varchar(20) NOT NULL,
villeArrive varchar(20) NOT NULL,
nbKm integer NOT NULL
);

create table temps(
numeroCoureur integer references coureurs,
numeroEtape integer references etapes,
tempsRealise time NOT NULL,
primary key(numeroCoureur, numeroEtape)
);

insert into pays values ('ALL','Allemagne');
insert into pays values ('FRA','France');
insert into pays values ('SUI','Suisse');
insert into pays values ('AUT','AUTRICHE');
insert into pays values ('BEL','BELGIQUE');
insert into pays values ('DAN','DANEMARK');
insert into pays values ('ESP','ESPAGNE');
insert into pays values ('G-B','GRANDE BRETAGNE');
insert into pays values ('ITA','ITALIE');
insert into pays values ('P-B','PAYS-BAS');
insert into pays values ('RUS','RUSSIE');

insert into equipestour values ('COF','Cofidis','Cyrille GUIMARD');
insert into equipestour values ('ONC','O.N.C.E.','Manolo SAIZ');
insert into equipestour values ('TEL','Telekom','Walter GODEFROOT');
insert into equipestour values ('BAN','BANESTO','Eusebio UNZUE');
insert into equipestour values ('CSO','CASINO','Vincent LAVENU');
insert into equipestour values ('FDJ','LA FRANCAISE DES JEUX','Marc MADIOT');
insert into equipestour values ('FES','FESTINA','Bruno ROUSSEL');
insert into equipestour values ('GAN','GAN','Roger LEGEAY');
insert into equipestour values ('SAE','SAECO','Claudio CORTI');

insert into coureurs values (8,'ULLRICH Jan','TEL','ALL','2000-08-12');
insert into coureurs values (31,'JALABERT Laurent','ONC','FRA','1997-04-11');
insert into coureurs values (61,'ROMINGER Tony','COF','SUI','1995-12-02');
insert into coureurs values (62,'SASTRE Carlos','COF','ESP','1993-01-23');
insert into coureurs values (91,'BOARDMAN Chris','GAN','G-B','1999-09-18');
insert into coureurs values (114,'CIPOLLINI Mario','SAE','ITA','2002-06-08');
insert into coureurs values (115,'ZABEL Erik','SAE','ALL', '2001-05-01');
insert into coureurs values (151,'OLANO Abraham','BAN','ESP','1993-07-29');
INSERT INTO coureurs VALUES (13,'BIDOCHON Bernard','FDJ','FRA','1992-11-11');
INSERT INTO coureurs VALUES (18,'TALON Achille','FDJ','ITA','1995-06-08');
INSERT INTO coureurs VALUES (19,'SANSPAYS Bruno','FDJ',NULL,'1995-04-06');

insert into etapes values (1,'1997-07-06','ROUEN','FORGES-LES-EAUX',192);
insert into etapes values (2,'1997-07-07','ST-VALERY-EN-CAUX','VIRE',262);
insert into etapes values (3,'1997-07-08','VIRE','PLUMELEC',224);
insert into etapes values (4,'1997-07-09','MONT ST MICHEL','DINARD',52);

INSERT INTO temps VALUES (13,1,'04:54:33');
INSERT INTO temps VALUES (13,2,'06:54:33');
INSERT INTO temps VALUES (13,3,'04:59:33');
INSERT INTO temps VALUES (13,4,'03:04:33');
INSERT INTO temps VALUES (18,1,'05:04:33');
INSERT INTO temps VALUES (18,2,'06:34:33');
INSERT INTO temps VALUES (18,3,'04:59:43');
INSERT INTO temps VALUES (18,4,'02:04:33');
insert into temps values (8,3,'04:54:33');
insert into temps values (8,1,'04:48:21');
insert into temps values (8,2,'06:27:47');
insert into temps values (8,4,'01:12:19');
insert into temps values (31,3,'04:54:33');
insert into temps values (31,1,'04:48:37');
insert into temps values (31,2,'06:27:47');
insert into temps values (31,4,'01:04:13');
insert into temps values (61,1,'04:48:24');
insert into temps values (61,2,'06:27:47');
insert into temps values (91,3,'04:54:33');
insert into temps values (91,1,'04:48:19');
insert into temps values (91,2,'06:27:47');
insert into temps values (114,3,'04:54:44');
insert into temps values (114,1,'04:48:09');
insert into temps values (114,2,'06:27:47');
insert into temps values (151,3,'04:54:33');
insert into temps values (151,1,'04:48:29');
insert into temps values (151,2,'06:27:47');
insert into temps values (151,4,'01:06:13');