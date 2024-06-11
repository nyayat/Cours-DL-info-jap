\! echo Question 1.1:
select * from tournois;
select * from equipes;
select * from matchs;
select * from participation;

\! echo Question 1.2:
select annee
from tournois
where pays='Nouvelle-Zélande';

\! echo Question 1.3:
select pays
from equipes
where nom='XV de France';

\! echo Question 1.4:
select distinct gagnant
from matchs;



\! echo Question 2.1:
select distinct nom
from matchs, equipes
where equipes.eid=gagnant;

select distinct nom
from matchs join equipes
on equipes.eid=gagnant;

\! echo Question 2.2:
select nom, annee
from tournois, matchs
where tournois=tid
    and perdant=2;

\! echo Question 2.3:
select mid
from matchs, equipes
where nom='Wallabies'
    and eid=perdant;

\! echo Question 2.4:
select mid
from matchs, equipes
where nom='All Blacks'
    and (eid=perdant or eid=gagnant);

\! echo Question 2.5:
select eid
from tournois, participation
where tournois.tid=participation.tid
    and tournois.nom='Coupe du Monde'
    and annee=1991;



\! echo Question 3.1:
select equipes.nom
from equipes, participation, tournois
where tournois.tid=participation.tid
    and equipes.eid=participation.eid
    and tournois.nom='Coupe du Monde'
    and annee=1991;


\! echo Question 3.2:
select tournois.nom, annee
from tournois, matchs, equipes
where tournois=tid
    and equipes.nom='XV de France'
    and equipes.eid=perdant;

\! echo Question 3.3:
select equipes.nom, annee
from equipes, tournois, matchs
where tournois=tid
    and tournois.nom='Coupe du Monde'
    and tour='finale'
    and gagnant=eid;

\! echo Question 3.4:
select distinct equipes.nom
from equipes, matchs as m1, matchs as m2
where m1.tournois=m2.tournois
    and m1.gagnant=m2.perdant
    and m1.gagnant=eid;
\! echo --------------------
select distinct equipes.nom
from equipes, matchs
where tour='finale'
    and perdant=eid;

\! echo nQuestion 3.5:
select distinct equipes.nom
from equipes, matchs as m1, matchs as m2
where m1.tour='finale'
    and m2.tour='finale'
    and m1.tournois!=m2.tournois
    and (eid=m1.gagnant or eid=m1.perdant)
    and (eid=m2.gagnant or eid=m2.perdant);



\! echo Question 4.1:
select equipes.nom, equipes.pays
from equipes
where pays not in(
    select distinct tournois.pays
    from tournois
);

\! echo Question 4.2:
select nom
from equipes
where
    eid not in(
        select distinct gagnant
        from matchs
        where tour='finale'
    )
    and eid not in(
        select distinct perdant
        from matchs
        where tour='finale'
    );

\! echo Question 4.3:
select tournois.nom, tournois.annee
from tournois
where not exists(
    select *
    from matchs, equipes
    where tournois.tid=matchs.tournois
        and equipes.nom='XV de France'
        and tour='demi-finale'
        and eid=gagnant
);

\! echo Question 4.4:
select equipes.nom
from equipes
where not exists(
    select *
    from matchs
    where eid=perdant
);

\! echo Question 4.5:
select equipes.nom
from equipes
where equipes.eid not in(
    select participation.eid
    from participation
);

\! echo Question 4.6:
select tournois.nom
from tournois
where tid not in(
    select distinct matchs.tournois
    from matchs
);



\! echo Question 5.1:
delete from tournois
where not exists(
    select *
    from matchs
    where tid=matchs.tournois
);

select * from tournois;

\! echo Question 5.2:
delete from equipes
where not exists(
    select *
    from participation
    where equipes.eid=participation.eid
);

select * from equipes;

\! echo Question 5.3:
delete from participation
where exists(
    select participation.eid
    from equipes, tournois
    where equipes.eid=participation.eid
        and tournois.tid=participation.tid
        and equipes.pays='Afrique du Sud'
        and tournois.nom='Coupe du Monde'
        and (tournois.annee=1987 or tournois.annee=1991)
);

select equipes.eid
from equipes, tournois, participation
where equipes.eid=participation.eid
    and tournois.tid=participation.tid
    and equipes.pays='Afrique du Sud'
        and tournois.nom='Coupe du Monde'
    and (tournois.annee=1987 or tournois.annee=1991);

\! echo Question 5.4:
select tid
from tournois
where not exists(
    select *
    from matchs m1, matchs m2, matchs m3
    where tournois.tid=m1.tournois
        and tournois.tid=m2.tournois
        and tournois.tid=m3.tournois
        and m1.tour='finale'
        and m2.tour='demi-finale'
        and m3.tour='demi-finale'
);

insert into matchs
values (20, 7, 2, 3, 'demi-finale'),
    (21, 7, 1, 2, 'finale');

select * from matchs;