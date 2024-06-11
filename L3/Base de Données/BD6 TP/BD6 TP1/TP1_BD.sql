drop table if exists provenance;
drop table if exists magasin;
drop table if exists usine;
drop table if exists produit;

create table produit (ref_prod integer primary key, nom_prod text not null, couleur text , poids integer);
create table usine (ref_usine integer primary key, nom_usine text not null, ville text not null) ;
create table magasin (ref_mag integer primary key, nom_mag text, ville text not null) ;
create table provenance (ref_prod integer references produit, ref_usine integer references usine, ref_mag integer references magasin, quantite integer,
 constraint cle_prim primary key (ref_prod, ref_usine, ref_mag)) ;

-- remplissage des tables

INSERT INTO produit VALUES 
(1,'tabouret','rouge',5),
(2,'evier','bleu',65),
(3,'bureau','jaune',45),
(4,'lampe a petrole','vert',15),
(5,'ordinateur','rouge',10),
(6,'telephone','bleu',8),
(7,'tabouret','violet',1),
(8,'evier','bleu',65),
(9,'tabouret','orange',3),
(10,'lampe halogene','rose',11),
(11,'lampe a souder','noir',3),
(12,'telephone','bleu',2),
(13,'casse-noix','vert',1),
(14,'casse-pied','marron',55),
(15,'casse-oreille','violet',15);

INSERT INTO usine VALUES
(109,'martin','Nantes'),
(189,'leroux','Marseille'),
(213,'dupont','Bordeaux'),
(402,'peugeot','Toulouse'),
(200,'peugeot','Marseille'),
(302,'rover','Londres');

INSERT INTO magasin VALUES
(14,'Stock10','Paris'),
(16,'JaiTout','Marseille'),
(18,'EnGros','Bordeaux'),
(20,'PrixBas','Toulouse'),
(22,'BasPrix','Marseille'),
(24,'DuBon','Lyon'),
(26,'DuBeau','Toulouse'),
(28,'BasDeGamme','Dublin'),
(30,'PasCher','Lyon'),
(32,NULL,'Rennes'),
(34,'NULL','Metz'),
(36,'','Nantes');

INSERT INTO provenance VALUES
(1,109,14,80),
(1,109,16,100),
(1,302,16,213),
(2,189,30,213),
(3,402,14,315),
(4,200,18,985),
(5,302,20,858),
(6,213,16,315),
(6,109,22,458),
(7,109,16,213),
(8,302,16,2000),
(9,189,30,175),
(10,402,14,100),
(11,109,16,750),
(11,302,16,100),
(12,189,30,315),
(12,200,16,589),
(12,189,22,213),
(13,402,14,499),
(14,109,18,213),
(15,189,20,1958),
(15,189,16,333);


-- copy produit from 'produit.dat'  delimiter ',' 
-- copy usine from 'usine.dat' delimiter ','
-- copy magasin from 'magasin.dat'  delimiter ','
-- copy provenance from 'provenance.dat' delimiter ','