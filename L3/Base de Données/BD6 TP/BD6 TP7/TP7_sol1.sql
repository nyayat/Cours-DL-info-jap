\! echo Question 1.1
select * from coureurs where codepays is null;

\! echo Question 1.2
select count(numeroCoureur) as "coureurs",
       count(codePays) as "coureurs avec pays"
from coureurs;

\! echo Question 1.3
select c.numeroCoureur, c.nomCoureur, t.numeroEtape from coureurs c
    join temps t on t.numeroCoureur=c.numeroCoureur
order by t.numeroEtape;

\! echo Question 1.4
select count(numeroCoureur) from coureurs;

\! echo Question 1.5
select sum(t.tempsRealise)/count(t.numeroEtape) as "temps moyen",
       sum(e.nbKm) as "nombre kimometres"
from temps t join etapes e on t.numeroEtape=e.numeroEtape
where numeroCoureur=31;

\! echo Question 1.6
select * from etapes e
where e.nbKm=(select max(nbKm) from etapes);

\! echo Question 1.7
select c.nomCoureur from coureurs c
    join temps t on c.numeroCoureur=t.numeroCoureur
where t.numeroEtape=2
    and t.tempsRealise=(select min(tempsRealise)
                        from temps
                        where numeroEtape=2);

\! echo Question 1.8
select c.nomCoureur,
       round((e.nbKm/extract(epoch from t.tempsRealise)*3600.0)::numeric, 4) as "vitesse moyenne"
from coureurs c
    join temps t on t.numeroCoureur=c.numeroCoureur
    join etapes e on e.numeroEtape=t.numeroEtape
where e.numeroEtape=(select max(numeroEtape) from etapes)
order by "vitesse moyenne";

\! echo Question 1.9
select sum(date_part('year', age(current_date, dateNaiss)))
       /count(numeroCoureur) as "age moyen"
from coureurs;

\! echo Question 1.10
select count(c.numeroCoureur) from coureurs c
where date_part('year', age(current_date, dateNaiss))
      >
      (select sum(date_part('year', age(current_date, dateNaiss)))
              /count(numeroCoureur)
      from coureurs);

\! echo Question 1.11
select count(c.numeroCoureur), c.codeEquipe
from coureurs c
group by c.codeEquipe;

\! echo Question 1.12
select count(c.numeroCoureur), c.codeEquipe, e.nomEquipe
from coureurs c join equipestour e on c.codeEquipe=e.codeEquipe
group by (c.codeEquipe, e.nomEquipe);

\! echo Question 1.13
select c.codeEquipe, min(t.tempsRealise)
from coureurs c join temps t on c.numeroCoureur=t.numeroCoureur
where t.numeroEtape=2
group by c.codeEquipe;

\! echo Question 1.14
select c.codeEquipe, min(t.tempsRealise)
from coureurs c join temps t on c.numeroCoureur=t.numeroCoureur
where t.numeroEtape=(select numeroEtape
                     from etapes
                     where nbKm=(select min(nbKm) from etapes))
group by c.codeEquipe;

\! echo Question 1.15
select e.numeroEtape, c.codeEquipe, min(t.tempsRealise)
from coureurs c
    join temps t on c.numeroCoureur=t.numeroCoureur
    join etapes e on t.numeroEtape=e.numeroEtape
group by c.codeEquipe, e.numeroEtape
order by e.numeroEtape;

\! echo Question 1.16
select e.numeroEtape, max(t.tempsRealise)-min(t.tempsRealise) as "ecart"
from temps t join etapes e on t.numeroEtape=e.numeroEtape
group by e.numeroEtape
order by e.numeroEtape;

\! echo Question 1.17
select count(numeroCoureur), codeEquipe
from coureurs
group by codeEquipe
having count(numeroCoureur)>1;

\! echo Question 1.18
select c.numeroCoureur, c.nomCoureur
from coureurs c
    join temps t on c.numeroCoureur=t.numeroCoureur
    join etapes e on t.numeroEtape=e.numeroEtape
group by c.numeroCoureur
having count(e.numeroEtape)=(select max(numeroEtape) from etapes);

\! echo Question 1.19
select c.numeroCoureur, c.nomCoureur,
    round((sum(e.nbKm)/sum(extract(epoch from t.tempsRealise))*3600.0)::numeric, 5) as "vitesse moyenne"
from coureurs c
    join temps t on t.numeroCoureur=c.numeroCoureur
    join etapes e on e.numeroEtape=t.numeroEtape
group by c.numeroCoureur
having count(e.numeroEtape)>2;

\! echo Question 1.20
create view courseFinie as(
    select t.numeroCoureur, sum(tempsRealise)
    from temps t join etapes e on e.numeroEtape=t.numeroEtape
    group by t.numeroCoureur
    having max(e.numeroEtape)=(select max(numeroEtape) from etapes)
);

--drop view courseFinie;

select * from courseFinie;

\! echo Question 1.21
select c.numeroCoureur
from courseFinie c
where c.sum=(select min(sum) from courseFinie);

\! echo Question 1.22
select t.numeroCoureur
from temps t join etapes e on e.numeroEtape=t.numeroEtape
group by t.numeroCoureur
having max(e.numeroEtape)=(select max(numeroEtape) from etapes)
    and sum(t.tempsRealise)<=all(select sum(tempsRealise) from temps
                                 group by numeroCoureur
                                 having max(numeroEtape)
                                        =
                                        (select max(numeroEtape) from etapes));

\! echo Question 1.23
--TODO
select t.numeroCoureur, t.numeroEtape, 

with recursive sumEtape(numeroCoureur, numeroEtape, sum+00:00:00) as(
    select t.numeroCoureur, t.numeroEtape, t.tempsRealise
    from temps t
    union
    select t.numeroCoureur, t.numeroEtape, t.tempsRealise+s.sum
    from temps t
        join sumEtape s on t.numeroCoureur=s.numeroCoureur
        where t.numeroEtape=s.numeroEtape+1
)
select * from sumEtape;

\! echo Question 1.24


\! echo Question 1.25


\! echo Question 1.26


\! echo Question 1.27


\! echo Question 1.28
