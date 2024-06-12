\! echo Question 1.a
select adresseid from adresse where nom='Adalberto';

\! echo Question 1.b
select * from entree where txid=1;

\! echo Question 1.c
select txid from transfert natural join bloc
where bloctime>='2013/07/01' and bloctime<='2013/07/31';

\! echo Question 1.d
select count(nom) from personne
where nom like 'A%';

\! echo Question 1.e
select count(distinct t.txid) from transfert t
join entree e on t.txid=e.txid
join sortie s on t.txid=s.txid
where e.adresseid='1FpqQn' or s.adresseid='1FpqQn';

\! echo Question 1.f
select sum(e.montant) from entree e
join transfert t on e.txid=t.txid
join bloc b on t.blocid=b.blocid
where b.bloctime>='2014/01/01' and b.bloctime<='2014/12/31';

\! echo Question 1.g
select t.txid, sum(e.montant) from transfert t
join entree e on t.txid=e.txid
group by t.txid;

\! echo Question 1.h
select t.txid from transfert t
join entree e on t.txid=e.txid
where t.txid>100
group by t.txid
having sum(e.montant)>10000;

\! echo Question 1.i
select a.soldeinitial
-
(select sum(e.montant) from entree e
where e.adresseid='1FpqQn')
+
(select sum(s.montant) from sortie s
where s.adresseid='1FpqQn')
from adresse a
where a.adresseid='1FpqQn';

\! echo Question 1.j
with transferts2013 as(
    (select e.adresseid from entree e
    join transfert t on e.txid=t.txid
    join bloc b on t.blocid=b.blocid
    where b.bloctime>='2013/01/01' and b.bloctime<='2013/12/31'
        and (b.bloctime<'2014/10/01' or b.bloctime>'2014/10/31')
    )
    union
    (select s.adresseid from sortie s
    join transfert t on s.txid=t.txid
    join bloc b on t.blocid=b.blocid
    where b.bloctime>='2013/01/01' and b.bloctime<='2013/12/31'
        and (b.bloctime<'2014/10/01' or b.bloctime>'2014/10/31')
    )
)
select count(distinct adresseid) from transferts2013;

\! echo Question 1.k
select e.txid from entree e
group by e.txid
having count(e.txid)
>
all(select count(s.txid) from sortie s
where e.txid=s.txid
group by s.txid)
order by e.txid asc;

\! echo Question 1.l
select t.txid from transfert t
where (select sum(e.montant) from entree e 
    where e.txid=t.txid)
!=
(select sum(s.montant) from sortie s
    where s.txid=t.txid);

\! echo Question 1.m
select (select sum(montant) from sortie s join adresse a
    on a.adresseid=s.adresseid
    where nom='Abby')
-
(select sum(montant) from entree e join adresse a
    on a.adresseid=e.adresseid
    where nom='Abby')
+
sum(soldeinitial) as "soldeabby"
from adresse
where nom='Abby';

\! echo Question 1.n
select distinct nom,
    sum(a.soldeinitial)
    +
    (select sum(montant) from sortie s join adresse a1
        on a1.adresseid=s.adresseid
        where a1.nom=a.nom)
    -
    (select sum(montant) from entree e join adresse a2
        on a2.adresseid=e.adresseid
        where a2.nom=a.nom)
    as "total"
from adresse a
group by nom
order by total desc;

\! echo Question 1.o
--Comment on sait que deux personnes sont concernées par un transfert ?
--Supposition : toutes les personnes dans le même txid se transfèrent de l'argent
    --Mais non, les chiffres ne collent pas avec le test suivant...
    --Donc, je ne sais pas :(

/*select * from
    (select p.nom, e.txid from entree e
    join adresse a on e.adresseid=a.adresseid
    join personne p on a.nom=p.nom) as e,
    (select p.nom, s.txid from sortie s
    join adresse a on s.adresseid=a.adresseid
    join personne p on a.nom=p.nom) as s
where e.txid=s.txid
    and (e.nom='Karyl' or s.nom='Karyl')
    and (e.nom='Reggie' or s.nom='Reggie');*/

--C'est pas ça mais boooon, on passe au TP suivant :)
select e.nom as a, s.nom as b, count((e.nom, s.nom)) as n from
    (select p.nom, e.txid from entree e
    join adresse a on e.adresseid=a.adresseid
    join personne p on a.nom=p.nom) as e,
    (select p.nom, s.txid from sortie s
    join adresse a on s.adresseid=a.adresseid
    join personne p on a.nom=p.nom) as s
where e.txid=s.txid
group by (e.nom, s.nom)
order by n desc;