drop table if exists participation cascade;
drop table if exists tournois cascade;
drop table if exists matchs cascade;
drop table if exists equipes cascade;

create table tournois (	
	tid serial primary key, 
	nom text not null, 
	annee integer, 
	pays text
);

create table equipes (
	eid serial primary key, 
	nom text, 
	pays text not null
);

create table matchs (
	mid serial primary key, 
	tournois integer, 
	gagnant integer, 
	perdant integer, 
	tour text,
	FOREIGN KEY (tournois) REFERENCES tournois(tid),
	FOREIGN KEY (gagnant) REFERENCES equipes(eid),
	FOREIGN KEY (perdant) REFERENCES equipes(eid)
);

create table participation (
	tid integer, 
	eid integer, 
	PRIMARY KEY (eid, tid),
	FOREIGN KEY (tid) REFERENCES tournois(tid),
	FOREIGN KEY (eid) REFERENCES equipes(eid)
);



INSERT INTO equipes VALUES 
(1,'All Blacks','Nouvelle-Zélande'),
(2,'XV de France','France'),
(3,'XV du Poireau','Pays de Galles'),
(4,'Pumas','Argentine'),
(5,'XV de la Rose','Angleterre'),
(6,'Springboks','Afrique du Sud'),
(7,'Wallabies','Australie'),
(8,'XV du Chardon','Écosse'),
(9,'Les braves cerisiers','Japon'),
(10,'Azzurri','Italie'),
(11,'St. Pauli','Allemagne'),
(12,'MFC 1871','France');


INSERT INTO tournois VALUES 
(1,'Coupe du Monde',1987,'Nouvelle-Zélande'),
(2,'Coupe du Monde',1991,'Angleterre'),
(3,'Coupe du Monde',1995,'Afrique du Sud'),
(4,'Coupe du Monde',1999,'Pays de Galles'),
(5,'Coupe du Monde',2003,'Australie'),
(6,'Coupe du Monde',2007,'France'),
(7,'Coupe du Monde',2011,'Nouvelle-Zélande'),
(8,'Championnat d''Allemagne',2019,'Allemagne');

INSERT INTO matchs (tournois, gagnant, perdant, tour) VALUES 
(1,1,2,'finale'),
(2,7,5,'finale'),
(3,6,1,'finale'),
(4,7,2,'finale'),
(5,5,7,'finale'),
(6,6,5,'finale'),
(1,2,7,'demi-finale'),
(1,1,3,'demi-finale'),
(2,5,8,'demi-finale'),
(2,7,1,'demi-finale'),
(3,6,2,'demi-finale'),
(3,1,5,'demi-finale'),
(4,7,6,'demi-finale'),
(4,2,1,'demi-finale'),
(5,7,1,'demi-finale'),
(5,5,2,'demi-finale'),
(6,5,2,'demi-finale'),
(6,6,4,'demi-finale'),
(7,1,7,'demi-finale');

INSERT INTO participation VALUES
(7,1),
(7,2),
(7,3),
(7,4),
(7,5),
(7,6),
(7,7),
(7,8),
(7,9),
(7,10),
(6,1),
(6,2),
(6,3),
(6,4),
(6,5),
(6,6),
(6,7),
(6,8),
(6,9),
(6,10),
(5,1),
(5,2),
(5,3),
(5,4),
(5,5),
(5,6),
(5,7),
(5,8),
(5,9),
(5,10),
(4,1),
(4,2),
(4,3),
(4,4),
(4,5),
(4,6),
(4,7),
(4,8),
(4,9),
(4,10),
(3,1),
(3,2),
(3,3),
(3,4),
(3,5),
(3,6),
(3,7),
(3,8),
(3,9),
(3,10),
(2,1),
(2,2),
(2,3),
(2,4),
(2,5),
(2,6),
(2,7),
(2,8),
(2,9),
(2,10),
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(1,10);