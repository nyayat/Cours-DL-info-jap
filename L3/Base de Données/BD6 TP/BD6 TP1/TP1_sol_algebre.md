## Question 3
$\pi_{ville}(usine)$

## Question 4
$\pi_{nomProd, couleur}(produit)$

## Question 5
$\pi_{refProd, quantite}(\sigma_{refMag='14'}(provenance))$

## Question 6
$\pi(\sigma_{ville='Marseille'}(usine))$

## Question 7
$\pi_{refMag}(\sigma_{refUsine='109'}(\sigma_{refProd='1'}(provenance)))$

## Question 8
$\pi_{refProd, nomProd}(\sigma_{couleur='rouge'}(produit))$

## Question 9
$\pi_{nomMag}(\sigma_{ville='L*'}(magasin))$

## Question 10
$\pi_{refProd, nomProd}(\sigma_{nomProd='casse*'}(produit))$

## Question 11
$\pi_{refMag}(provenance)$

## Question 12
$\pi_{nomProd, couleur}(\sigma_{poids>15 \wedge poids<45}(produit))$

## Question 13
$\pi_{nomProd}(\sigma_{poids<20}(\sigma_{couleur='rouge'} \cup \sigma_{couleur='bleu'}(produit)))$

## Question 14
$\pi_{nomProd}(\sigma_{couleur='jaune'} \cup \sigma_{couleur='bleu' \wedge poids<20}(produit))$

## Question 15
$\pi_{nomProd}(\sigma_{nomProd='lampe*'} \cup \sigma_{poids>30}(produit))$

## Question 16
$\pi_{refMag}(\sigma_{nomMag='NULL'}(magasin))$

## Question 18
$\pi_{refProd}(\sigma_{nomUsine='martin' \wedge ville='Nantes'}(usine \Join_{provenance.refUsine=usine.refUsine} provenance))$

## Question 19
$\pi_{nomUsine}(usine \Join_{usine.ville=magasin.ville} magasin)$

## Question 20
$\pi_{refProd, quantite}(\sigma_{magasin.nomMag='P*'}(provenance \Join_{provenance.refMag=magasin.refMag} magasin))$

## Question 21
$\pi_{nomUsine}(\sigma_{produit.nom='ordinateur'}(usine \Join_{usine.refUsine=provenance.refUsine} provenance \Join_{provenance.refProd=produit.refProd} produit))$

## Question 22
$\pi_{p1.nomProd}(\sigma_{p1.poids=p2.refProd}(\rho_{p1 \larr produit} \times \rho_{p2 \larr produit}))$