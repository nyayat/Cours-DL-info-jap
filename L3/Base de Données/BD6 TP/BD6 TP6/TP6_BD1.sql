drop table if exists rencontre;
drop table if exists gain;
drop table if exists joueurtennis;

create table joueurtennis (
    nujoueur integer primary key,
    nom text not null,
    prenom text not null,
    annaiss integer not null,
    nationalite text not null);

create table rencontre (
    nugagnant integer not null,
    nuperdant integer not null,
    lieutournoi text not null,
    annee integer not null,
    primary key (nuperdant,lieutournoi,annee),
    foreign key (nuperdant) references joueurtennis(nujoueur));

create table gain (
    nujoueur integer,
    lieutournoi text not null,
    annee integer not null,
    prime integer not null,
    sponsor text not null,
    primary key (nujoueur,lieutournoi,annee),
    foreign key (nujoueur) references joueurtennis);
    
-- remplissage des tables

\COPY joueurtennis FROM 'joueurtennis.dat' WITH csv;
\COPY rencontre FROM 'rencontre.dat'  WITH csv;
\COPY gain FROM 'gain.dat' WITH csv;
