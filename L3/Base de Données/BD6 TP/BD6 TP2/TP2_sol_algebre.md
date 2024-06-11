# Exercice 1
## Question 1.2
$\pi_{annee}(\sigma_{pays='Nouvelle-Zélande'}(tournois))$

## Question 1.3
$\pi_{pays}(\sigma_{nom='XV de France'}(equipes))$

## Question 1.4
$\pi_{gagnant}(matchs)$


# Exercice 2
## Question 2.1
$\pi_{nom}(\sigma_{equipes.eid=gagnant}(matchs \times equipes))$

## Question 2.2
$\pi_{nom, annee}(\sigma_{perdant=2}(tournois \Join_{tournois=tid} matchs))$

## Question 2.3
$\pi_{mid}(\sigma_{nom='Wallabies' \wedge eid=perdant}(matchs \times equipes))$

## Question 2.4
$\pi_{mid}(\sigma_{nom='All Blacks'}(\sigma_{eid=perdant \cup eid=gagnant}(matchs \times equipes)))$

## Question 2.5
$\pi_{eid}(\sigma_{tournois.nom='Coupe du Monde' \wedge annee=1991}(tournois \Join_{tournois.tid=participation.tid} participation))$


# Exercice 3
## Question 3.1
$\pi_{equipes.nom}(\sigma_{tournois.nom='Coupe du Monde' \wedge annee=1991}(equipes \Join_{equipes.eid=participation.eid} participation \Join_{participation.tid=tournois.tid} tournois))$

## Question 3.2
$\pi_{tournois.nom, annee}(\sigma_{equipes.nom='XV de France' \wedge equipes.eid=perdant}(tournois \Join_{tid=tournois} matchs \Join equipes))$

## Question 3.3
$\pi_{equipes.nom, annee}(\sigma_{tournois.nom='Coupe du Monde' \wedge tour='finale' \wedge gagnant=eid}(equipes \Join tournois \Join_{tid=tournois} matchs))$

## Question 3.4
$\pi_{equipes.nom}(\sigma_{tour='finale' \wedge perdant=eid}(equipes \times matchs))$

## Question 3.5
$\pi_{equipes.nom}(\sigma_{m1.tour='finale'}(\sigma_{m2.tour='finale'}(\sigma_{m1.tournois!=m2.tournois}(\sigma_{eid=m1.gagnant \cup eid=m1.perdant}(\sigma_{eid=m2.gagnant \cup eid=m2.perdant}(equipes \times \rho_{m1 \larr matchs} \times \rho_{m2 \larr matchs}))))))$


# Exercice 4
## Question 4.1
$\pi_{equipes.nom, equipes.pays}(equipes) - \pi_{tournois.pays}(tournois)$

## Question 4.2
$\pi_{nom}(equipes) - \pi_{gagnant}(\sigma_{tour='finale'}(matchs)) - \pi_{perdant}(\sigma_{tour='finale'}(matchs))$

## Question 4.3
$\pi_{tournois.nom, tournois.annee}(tournois) - \pi(\sigma_{equipes.nom='XV de France' \wedge tour='demi-finale' \wedge eid=gagnant}(tournois \Join_{tournois.tid=matchs.tournois} matchs \Join equipes))$

## Question 4.4
$\pi_{equipes.nom}(equipes) - \pi(\sigma_{eid=perdant}(matchs \Join equipes))$

## Question 4.5
$\pi_{equipes.nom}(equipes) - \pi_{participation.eid}(participation)$

## Question 4.6
$\pi_{tournois.nom}(tournois) - \pi_{matchs.tournois}(matchs)$