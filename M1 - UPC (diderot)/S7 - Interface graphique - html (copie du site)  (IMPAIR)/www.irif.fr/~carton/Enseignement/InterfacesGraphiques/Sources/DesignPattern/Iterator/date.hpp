// -*- coding: utf-8 -*-
// Time-stamp: <date.hpp  26 mar 2020 07:46:27> 

// Classe date avec représentation jour/mois/année

#ifndef DATE_HPP
#define DATE_HPP

#include <iostream>
using namespace std;

class Date {
  // Opérateur de sortie
  friend ostream& operator<<(ostream&, const Date&);
public:
  // Constructeur 
  Date(int day, int month, int year) : d(day), m(month), y(year) {}
  // Constructeur à partir d'un nombre de jours depuis le 01/01/1970.
  Date(int n = 0) { setdmy(n); }
  // Accesseurs
  int day()   const { return d; }
  int month() const { return m; }
  int year()  const { return y; }
  // Jour dans la semaine : Lundi <-> 1, Mardi <-> 2, ..., Dimanche <-> 7
  // Le 01/01/1970 est un jeudi <-> 4
  int weekDay() const {
    // Explications 
    // - "1 +"  donne une valeur entre 1 et 7
    // - "+ 10" ajuste pour que le 01/01/1970 soit un jeudi
    // -        donne une valeur positive car -6 % 7 == -6 (et non pas 1)
    // -        10 = 3 + 7 car Jeudi <-> 4 = 1 + 3
    return 1 + ((*this - origin) % 7 + 10)  % 7; 
  }
  // Passage au jour suivant
  Date& operator++(int i);
  // Passage au jour précédent
  Date& operator--(int i);
  // Nombre de jours entre deux dates
  int operator-(Date date) const;
  // Décalage d'un nombre de jours positif ou négatif
  Date& operator+=(int n);
  // Nouvelle date décalée d'un nombre de jours positif ou négatif
  Date operator+(int n);
  // Égalité 
  bool operator==(Date date) const;
  bool operator!=(Date date) const { return !(*this == date); }
  // Ordres
  bool operator<(Date date) const;
  bool operator<=(Date date) const;
  // Retourne true si l'année de la date est bissextile
  bool leap() const { return leap(y) == 1; }
  // Méthode de test
  static void test();
private:
  int d;                // Jour dans le mois : 1..31
  int m;                // Mois  : 1..12
  int y;                // Année : 1592.. (début du calendirer Grégorien)
  // Année de référence : objet statique constant
  static const Date origin;
  // Année bissextile
  // Cette fonction retourne 
  // + 1 si l'année year est bissextile
  // + 0 sinon
  // Cette fonction retourne un entier plutôt qu'un booléen 
  // pour que cette valeur puisse être ajoutée
  static int leap(int year);
  // Division par valeur supérieure
  // Cette fonction retourne 
  // + n/k     si n est divisible par k
  // + n/k + 1 sinon
  static int divup(int n, int k);
  // Nombre d'années bissextiles entre deux années
  // Cette fonction retourne 
  // + Si year0 == year1
  //   0
  // + Si year0 < year1
  //   Le nombre d'années bissextiles entre year0 et year1 où
  //   l'année year0 est incluse et l'année year1 est exluse.
  // + Si year0 > year1
  //   L'opposé du nombre d'années bissextiles entre year0 et year1 où
  //   l'année year1 est incluse et l'année year0 est excluse.
  // C'est toujours l'extrémité gauche de l'intervalle qui est incluse
  // et l'extrémité droite qui est excluse.
  // Quelque soit l'ordre des années year0, year1 et year2, on a 
  //   leap(year0, year1) + leap(year1, year2) == leap(year0, year2).
  // En particulier
  //   leap(year0, year1) = - leap(year1, year0)
  static int leap(int year0, int year1);
  // Nombre de jours d'un mois d'une année
  // L'année est nécessaire pour savoir si celle-ci est bissextile.
  static int monthLength(int month, int year);
  // Nombre de jours entre le 1er janvier et le premier jour d'un mois
  // L'année est nécessaire pour savoir si celle-ci est bissextile.
  // Cette fonction est utilisée par l'opérateur operator-
  static int monthAcc(int month, int year);
  // Calcul de l'année pour un nombre de jours écoulés depuis l'origine
  // Retourne le nombre de jours restants entre le premier janvier 
  // de l'année calculée et le jour passé en passé en paramètre.
  // Cette fonction positionne aussi le jour et le mois au 1er janvier
  int sety(int n);
  // Calcul du jour et du mois pour un nombre de jours écoulés depuis
  // le 1er janvier
  // Cette méthode suppose que l'attribut y contient l'année.
  // L'entier n doit être strictement inférieur au nombre de jours de
  // l'année.
  void setdm(int n);
  // Calcul de la date pour un nombre de jours écoulés depuis le 1er janvier
  void setdmy(int n);
};
#endif 
