\! echo Question 1:
\d projet_daia.provenance;
\d projet_daia.magasin;
\d projet_daia.usine;
\d projet_daia.produit;

\! echo Question 2:
select * from produit;
select * from usine;
select * from magasin;
select * from provenance;

\! echo Question 3:
select ville
from usine;

select distinct ville
from usine;

\! echo Question 4:
select nom_prod, couleur
from produit;

\! echo Question 5:
select ref_prod, quantite
from provenance
where ref_mag='14';

\! echo Question 6:
select *
from usine
where ville='Marseille';

\! echo Question 7:
select ref_mag
from provenance
where ref_usine='109'
    and ref_prod='1';

\! echo Question 8:
select ref_prod, nom_prod
from produit
where couleur='rouge';

\! echo Question 9:
select nom_mag
from magasin
where ville like 'L%';

\! echo Question 10:
select ref_prod, nom_prod
from produit
where nom_prod like 'casse%';

\! echo Question 11:
select ref_mag
from provenance;

select distinct ref_mag
from provenance;

\! echo Question 12:
select nom_prod, couleur
from produit
where poids between 15 and 45;

\! echo Question 13:
select nom_prod
from produit
where (couleur='bleu' or couleur='jaune')
    and poids<20;

\! echo Question 14:
select nom_prod
from produit
where couleur='jaune'
    or (couleur='bleu' and poids<20);

\!echo Question 15:
select nom_prod
from produit
where nom_prod like 'lampe%'
    or poids>30;

\! echo Question 16:
select ref_mag
from magasin
where nom_mag='NULL';

\! echo Question 17:
select ref_mag
from magasin
where nom_mag is null;

\! echo Question 18:
select ref_prod
from provenance
where ref_usine=(
    select ref_usine
    from usine
    where nom_usine='martin'
        and ville='Nantes'
);

select ref_prod
from provenance, usine
where usine.nom_usine='martin'
    and usine.ville='Nantes'
    and provenance.ref_usine=usine.ref_usine;

\! echo Question 19:
/*select nom_usine from usine where ville in (select ville from magasin);*/
select nom_usine
from usine, magasin
where usine.ville=magasin.ville;

\! echo Question 20:
select ref_prod, quantite
from provenance, magasin
where magasin.nom_mag like 'P%'
    and provenance.ref_mag=magasin.ref_mag;

\! echo Question 21:
select nom_usine
from usine, produit, provenance
where produit.nom_prod='ordinateur'
    and provenance.ref_prod=produit.ref_prod
    and usine.ref_usine=provenance.ref_usine;

\! echo Question 22:
select p1.nom_prod
from produit p1, produit p2
where p1.poids=p2.ref_prod;