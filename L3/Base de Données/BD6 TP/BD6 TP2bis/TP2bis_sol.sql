\! echo Question 2.1
select distinct region
from country;

\! echo Question 2.2
select distinct region
from country
where continent='Europe';

\! echo Question 2.3
select name_country
from country
where region='Southern Europe';

\! echo Question 2.4 --capital est une clé étrangère de type entier
select capital
from country
where region='Western Europe';

\! echo Question 2.5
select distinct language
from countrylanguage
where isofficial;

\! echo Question 2.6
select countrycode
from countrylanguage
where language='French'
    and isofficial;

\! echo Question 2.7
select indepyear
from country
where name_country='France';

\! echo Question 2.8
select indepyear
from country
where continent='Europe';

\! echo Question 2.9
select distinct name_city
from city
where countrycode='FRA'
    and population_city>200000;

\! echo Question 2.10
select name_country,
    population_country/surfacearea as density,
    gnp/population_country as npperhab,
    lifeexpectancy
from country
where continent='Europe'
order by density desc;

\! echo Question 2.11
select name_country
from country
where not lifeexpectancy<77
    and not gnp/population_country>0.01;

\! echo Question 2.12
select name_country
from country
where not(
    lifeexpectancy>=77
    or gnp/population_country<0.01
);

\! echo Question 2.13
select distinct countrycode
from countrylanguage
where isofficial
    and not percentage>=50.0;

\! echo Question 2.14
select distinct countrycode
from countrylanguage
where isofficial;

\! echo Question 2.15
select name_country
from country
where population_country>100000000;



\! echo Question 3.1
select name_city
from country join city
    on capital=id
where region='South America';

\! echo Question 3.2
select name_country
from country join countrylanguage
    on country.countrycode=countrylanguage.countrycode
where language='French'
    and isofficial;

\! echo Question 3.3
select name_country
from country join countrylanguage
    on country.countrycode=countrylanguage.countrycode
where language='Spanish'
    and isofficial
    and governmentform='Federal Republic';

\! echo Question 3.4
select distinct name_country
from country join countrylanguage as l1
    on country.countrycode=l1.countrycode
join countrylanguage as l2
    on country.countrycode=l2.countrycode
        and l1.language!=l2.language
where l1.isofficial
    and l2.isofficial;

\! echo Question 3.5
select name_country
from country
where countrycode not in(
    select distinct countrycode
    from countrylanguage
    where isofficial
);

\! echo Question 3.6
select distinct name_country
from country join city c1
    on country.countrycode=c1.countrycode
join city c2
    on country.countrycode=c2.countrycode
        and c1.name_city!=c2.name_city
where c1.population_city>1000000
    and c2.population_city>1000000;

\! echo Question 3.7
select distinct region
from country c1
where not exists(
    select distinct *
    from country c2
    where c1.region=c2.region
        and c1.governmentform!=c2.governmentform
);

\! echo Question 3.8
select distinct region
from country
where region not in(
    select distinct region
    from country
    where governmentform like '%Monarchy%'
);

\! echo Question 3.9
select name_city
from city
where id in(
    select distinct capital
    from country
    where region='South America'
);

--\! echo Question 3.10 --idem que 3.8

--\! echo Question 3.11 --idem que 3.5

\! echo Question 3.12
select distinct l1.countrycode
from countrylanguage l1, countrylanguage l2
where l1.language='French'
    and l1.isofficial
    and l1.countrycode not in(
        select countrycode
        from countrylanguage l2
        where l2.language!='French'
            and l2.isofficial
    );

\! echo Question 3.13
select name_country
from country natural join countrylanguage
where language='French'
    and isofficial
    and countrycode not in(
        select distinct countrycode
        from countrylanguage
        where language!='French'
            and isofficial
    );

--\! echo Question 3.14 --idem que 3.7

\! echo Question 3.15
select distinct language
from countrylanguage 
where countrycode in(
        select country.countrycode
        from country join city
            on capital=id
        where population_city>5000000
    )
    and isofficial;

\! echo Question 3.16
select distinct name_country
from country
where countrycode in(
    select distinct l1.countrycode
    from countrylanguage l1 join countrylanguage l2
        on l2.countrycode=l1.countrycode
            and l2.language!=l1.language
            and l2.percentage>10
    join countrylanguage l3
        on l3.countrycode=l2.countrycode
            and l3.language!=l1.language
            and l3.language!=l2.language
            and l3.percentage>10
    where l1.percentage>10
);

\! echo Question 3.17
select distinct c1.region
from country c1 join country c2
    on c1.region=c2.region
where abs(c1.lifeexpectancy-c2.lifeexpectancy)>=10;

\! echo Question 3.18
select name_country
from country
where countrycode in(
    select l1.countrycode
    from countrylanguage l1 join countrylanguage l2
        on l1.countrycode=l2.countrycode
    where l1.language='French'
        and l2.language='English'
        and l1.isofficial
        and l2.isofficial
);

\! echo Question 3.19
select name_country
from country
where governmentform like '%Monarchy%';

\! echo Question 3.20
select name_country
from country
where continent='Europe'
    and governmentform like '%Monarchy%'
    and capital in(
        select id
        from city
        where population_city<1000000
    );

\! echo Question 3.21
select distinct language
from countrylanguage
where language not in(
    select language
    from countrylanguage
    where isofficial
);