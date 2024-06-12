\! echo Question 1.1
\! echo serial ne nécessite pas de valeur, donc quatre attributs suffisent pour remplir la table matchs

\! echo Question 1.2
insert into matchs values
    (20, 7, 2, 3, 'demi-finale');

\! echo Question 1.3
insert into tournois values
    (9, 'Coupe du Monde', 2025, 'Enfer');

select * from tournois;

\! echo Question 1.5
create table joueurs(
    jid serial primary key,
    nom varchar(20),
    prenom varchar(20),
    dateDeNaissance date,
    nationalite varchar(20)
);

\! echo Question 1.6
\i joueur1.sql

\! echo Question 1.7
\! echo On crée une table qui relie joueurs et équipes
create table joueurEquipes(
    jid integer,
    eid integer,
    foreign key (jid) references joueurs(jid),
    foreign key (eid) references equipes(eid)
);

\! echo Question 1.8
\i joueur2.sql

\! echo Question 1.9
INSERT INTO joueurEquipes (jid, eid) VALUES (1, 30);
\! echo Impossible d\'ajouter l\'eid 30, car elle ne figure pas dans la table equipes

\! echo Question 1.10
delete from equipes where nom='All Blacks';
\! Impossible car contrainte d\integrite avec les donnees de la table matchs

\! echo Question 1.11
alter table matchs drop constraint matchs_gagnant_fkey;
alter table matchs drop constraint matchs_perdant_fkey;

alter table matchs add constraint matchs_gagnant_fkey
    foreign key (gagnant) references equipes(eid) on delete cascade;
alter table matchs add constraint matchs_perdant_fkey
    foreign key (perdant) references equipes(eid) on delete cascade;

\! echo Question 1.12
alter table participation drop constraint participation_eid_fkey;
alter table participation add constraint participation_eid_fkey
    foreign key (eid) references equipes(eid) on delete cascade;

alter table joueurEquipes drop constraint joueurEquipes_eid_fkey;
alter table joueurEquipes add constraint joueurEquipes_eid_fkey
    foreign key (eid) references equipes(eid) on delete cascade;

\! echo ------------------------------------
select * from matchs;
delete from equipes where nom='All Blacks';
select * from matchs;

\! echo Question 1.13
alter table joueurEquipes drop constraint joueurequipes_jid_fkey;
alter table joueurEquipes add constraint joueurequipes_jid_fkey
    foreign key (jid) references joueurs(jid) on delete cascade;

delete from joueurs where jid in(
    select joueurEquipes.jid
    from joueurEquipes join equipes
        on joueurEquipes.eid=equipes.eid
    where equipes.pays='Italie'
);

\! echo Question 1.14
delete from joueurs where jid in(
    select joueurEquipes.jid
    from joueurEquipes join participation
        on joueurEquipes.eid=participation.eid
    join tournois
        on participation.tid=tournois.tid
    where tournois.pays='France'
);



\! echo Question 2
drop table agency cascade;
create table agency(
    agency_id text primary key,
    agency_name text not null,
    agency_url text not null,
    agency_timezone varchar(20) not null,
    agency_lang text,
    agency_phone varchar(20),
    agency_email text
);
\copy agency from agency.txt with delimiter ',' null as '';

drop table routes cascade;
create table routes(
    route_id text primary key,
    agency_id text,
    foreign key (agency_id) references agency(agency_id),
    route_short_name varchar(9) not null,
    route_long_name text not null,
    route_desc text,
    route_type integer not null,
    route_url text,
    route_color varchar(6),
    route_text_color varchar(6),
    route_sort_order integer
);
\copy routes from routes.txt with delimiter ',' null as '';

create type enumBikes as enum('0', '1', '2');
drop table trips cascade;
create table trips(
    route_id text,
    foreign key (route_id) references routes(route_id),
    service_id text,
    --foreign key (service_id) references calendar(service_id),
    trip_id text primary key,
    trip_headsign text,
    trip_short_name text,
    direction_id boolean,
    block_id integer unique,
    shape_id integer unique,
    wheelchair_accessible integer,
    bikes_allowed enumBikes
);
\copy trips from trips.txt with delimiter ',' null as '';

drop table stops cascade;
create table stops(
    stop_id text primary key,
    stop_code text,
    stop_name text not null,
    stop_desc text,
    stop_lon numeric not null,
    stop_lat numeric not null,
    zone_id integer default 100,
    stop_url text,
    location_type integer,
    parent_station text,
    --foreign key (parent_station) references stops(stop_id),
    stop_timezone varchar(20),
    level_id integer,
    wheelchair_boarding integer,
    platform_code text
);
\copy stops from stops.txt with delimiter ',' null as '';

create type enumStates as enum('', '0', '1');
drop table stop_times cascade;
create table stop_times(
    trip_id text,
    foreign key (trip_id) references trips(trip_id),
    arrival_time time not null,
    departure_time time not null,
    stop_id text,
    foreign key (stop_id) references stops(stop_id),
    stop_sequence integer not null,
    pickup_type enumStates,
    drop_off_type enumStates,
    local_zone_id integer,
    stop_headsign text,
    timepoint enumStates
);
\copy stop_times from stop_times.txt with delimiter ',' null as '';
