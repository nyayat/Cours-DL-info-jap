\! echo Question 1.a
select distinct nom, annaiss from joueurtennis j
    join rencontre r on j.nujoueur=r.nuperdant
    where lieutournoi='Roland Garros' and r.annee=1994
union
select distinct nom, annaiss from joueurtennis j
    join rencontre r on j.nujoueur=r.nugagnant
where lieutournoi='Roland Garros' and r.annee=1994;

\! echo ou
select nom, annaiss from joueurtennis j
    join gain g on j.nujoueur=g.nujoueur
where g.lieutournoi='Roland Garros' and g.annee=1994;

\! echo Question 1.b
select distinct nom, nationalite from joueurtennis j
    join gain g1 on j.nujoueur=g1.nujoueur
    join gain g2 on j.nujoueur=g2.nujoueur
where g1.lieutournoi='Roland Garros'
    and g2.lieutournoi='Wimbledon' and g2.annee=1992;

\! echo Question 1.c
select distinct nom, nationalite from joueurtennis j
    join gain g on j.nujoueur=g.nujoueur
    join rencontre r on j.nujoueur=r.nugagnant
where g.sponsor='Peugeot' and r.lieutournoi='Roland Garros';

\! echo Question 1.d
select distinct r1.nuperdant from rencontre r1
where r1.nuperdant not in(
    select r2.nuperdant from rencontre r2
    where lieutournoi='Roland Garros'
)
and r1.nuperdant not in(
    select r2.nugagnant from rencontre r2
    where lieutournoi='Wimbledon'
);

\! echo Question 1.e
select distinct j.nom, r.lieutournoi, r.annee from joueurtennis j
    join rencontre r on j.nujoueur=r.nugagnant
where (j.nujoueur, r.lieutournoi, r.annee) not in(
    select r2.nuperdant, r2.lieutournoi, r2.annee from rencontre r2
);

\! echo Question 1.f
select j.nom from joueurtennis j
join gain g on j.nujoueur=g.nujoueur
where g.annee=1994
group by j.nom
having count(distinct g.lieutournoi)
=
all(select count(distinct r.lieutournoi)
    from rencontre r
    where r.annee=1994
);

\! echo Question 1.g
select count(*) from rencontre;

\! echo Question 1.h
select g.lieutournoi, g.annee, count(distinct g.nujoueur)
from gain g
group by g.lieutournoi, g.annee;

\! echo Question 1.i
select j.nujoueur from joueurtennis j
join gain g on g.nujoueur=j.nujoueur
group by j.nujoueur
having count(distinct sponsor)>1;

\! echo ou
select j.nujoueur from joueurtennis j
join gain g1 on g1.nujoueur=j.nujoueur
join gain g2 on g2.nujoueur=j.nujoueur
             and g1.sponsor!=g2.sponsor
group by j.nujoueur;

\! echo Question 1.j
select j.nujoueur from joueurtennis j
join gain g on g.nujoueur=j.nujoueur
group by j.nujoueur
having count(distinct sponsor)=2;

\! echo ou
select distinct j.nujoueur from joueurtennis j
join gain g1 on g1.nujoueur=j.nujoueur
join gain g2 on g2.nujoueur=j.nujoueur
             and g1.sponsor!=g2.sponsor
where not exists(
    select * from gain g3
    where g3.nujoueur=g1.nujoueur
        and g3.sponsor!=g2.sponsor
        and g3.sponsor!=g1.sponsor
);

\! echo Question 1.k
select g.annee, sum(g.prime)/count(g.prime)
from gain g
group by g.annee;

\! echo Question 1.l
select j.nom, g1.prime from joueurtennis j
join gain g1 on j.nujoueur=g1.nujoueur
where g1.annee=1992 and g1.prime in(
    select max(g2.prime) from gain g2
    where annee=1992
    group by g2.lieutournoi
);

\! echo Question 1.m
select j.nom, sum(g.prime) as s
from joueurtennis j
join gain g on j.nujoueur=g.nujoueur
where annee=1992
group by j.nom
order by s desc;

\! echo Question 1.n
select j.nationalite from joueurtennis j
join rencontre r on j.nujoueur=r.nugagnant
where (j.nujoueur, r.lieutournoi, r.annee) not in(
    select r2.nuperdant, r2.lieutournoi, r2.annee from rencontre r2
)
group by (j.nujoueur, r.lieutournoi, r.annee, j.nationalite)
having count(distinct r.annee)
=
all(select count(distinct r1.annee) from rencontre r1);