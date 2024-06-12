// -*- coding: utf-8 -*-
// Time-stamp: <date.cpp  26 mar 2020 07:46:42> 

#include "date.hpp"

// Initialisation de la date de référence : 01/01/1970
// La date d'origine doit toujours être un premier janvier.
const Date Date::origin(01, 01, 1970);

// Opérateur de sortie
ostream& operator<<(ostream& out, const Date& date) {
  // Jour dans le mois avec un '0' en tête si nécessaire
  if (date.d <= 9)
    out << '0';
  out << date.d << "/";
  // Mois avec un '0' en tête si nécessaire
  if (date.m <= 9)
    out << '0';
  // Année
  out << date.m << "/" << date.y;
  // Jour dans la semaine
  // out << " [" << date.weekDay() << "]";
  return out;
}

// Passage au jour suivant
Date& Date::operator++(int i) {
  d++;                  // Jour suivant dans le mois
  if (d > monthLength(m, y)) {
    d = 1;                      // Premier jour
    m++;                        // du mois suivant
    if (m > 12) {
      m = 1;                    // Premier janvier
      y++;                      // de l'année suivante
      }
  }
  return *this;
}

// Passage au jour précédent
Date& Date::operator--(int i) {
  d--;                  // Jour précédent dans le mois
  if (d < 1) {
    m--;                        // du mois précédent
    if (m < 1) {
      m = 12;                   // 31 décembre
      y--;                      // de l'année précédente
    }
    // Le jour est calculé après la mise à jour du mois et de l'année
    d = monthLength(m, y); // Dernier jour du mois précédent
  }
  return *this;
}

// Décalage d'un nombre de jours positif ou négatif
Date& Date::operator+=(int n) { 
  setdmy(*this - origin + n); 
  return *this;
} 

// Nouvelle date décalée d'un nombre de jours positif ou négatif
Date Date::operator+(int n) {
  Date d(*this); 
  d += n; 
  return d;
}

// Égalité 
bool Date::operator==(Date date) const {
  return y == date.y && m == date.m && d == date.d;
}

// Ordre strict
bool Date::operator<(Date date) const {
  // Si les années sont différentes, elles déterminent l'ordre
  if (y != date.y)
    return y < date.y;
  // Si les années sont égales et les mois différents 
  // les mois déterminent l'ordre
  if (m != date.m)
    return m < date.m;
  // Si les années et les mois sont égaux, les jours déterminent l'ordre
  return d < date.d;
}

// Ordre large
// Cet opérateur pourrait être écrit 
//    return *this < date || *this == date
// mais cette écriture est un peu moins efficace qu'une écriture
// directe qui n'effectue qu'une seule comparaison des années
// des mois et des jours.
bool Date::operator<=(Date date) const {
  // Si les années sont différentes, elles déterminent l'ordre
  if (y != date.y)
    return y < date.y;
  // Si les années sont égales et les mois différents 
  // les mois déterminent l'ordre
  if (m != date.m)
    return m < date.m;
  // Si les années et les mois sont égaux, les jours déterminent l'ordre
  return d <= date.d;
}

// Nombre de jour entre deux dates
int Date::operator-(Date date) const {
  // Nombre de jours du premier janvier au premier janvier
  // en tenant compte des années bissextiles.
  int diff = (y - date.y) * 365 + leap(date.y, y);
  // Du premier du mois m au premier au mois date.m
  diff += monthAcc(m, y) - monthAcc(date.m, date.y);
  // On ajoute les jours dans les mois
  diff += d - date.d;
  return diff;
}

// Année bissextile
int Date::leap(int year) {
  if ((year % 4) != 0)
    return 0;                   // Pas multiple de 4 
  else if ((year % 100) != 0)
    return 1;                   // Multiple de 4 mais pas multiple de 100
  else 
    return (year % 400) == 0 ? 1 : 0;
}

// Division par valeur supérieure
// Cette fonction retourne 
// + n/k     si n est divisible par k
// + n/k + 1 sinon
int Date::divup(int n, int k) {
  if (n % k == 0) 
    return n/k;
  else
    return n/k+1;
}
 
// Nombre d'années bissextiles entre deux années 
// year0 incluse
// year1 excluse
int Date::leap(int year0, int year1) {
  // Passage aux multiples de 4 par valeur supérieure
  year0 = divup(year0, 4);
  year1 = divup(year1, 4);
  // Nombres d'années multiples de 4
  int result = year1 - year0;

  // Passage aux multiples de 100 par valeur supérieure
  // On divise par 25 car on a déjà divisé par 4
  year0 = divup(year0, 25);
  year1 = divup(year1, 25);
  // Nombres d'années multiples de 100
  result -= year1 - year0;

  // Passage aux multiples de 400 par valeur supérieure
  // On divise par 4 car on a déjà divisé par 100
  year0 = divup(year0, 4);
  year1 = divup(year1, 4);
  // Nombres d'années multiples de 400
  result += year1 - year0;

  return result;
}

// Nombre de jours d'un mois d'une année
int Date::monthLength(int month, int year) {
  // Tableau du nombre de jours de chaque mois.
  static int length[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
  return month != 2 ? length[month-1] : length[month-1] + leap(year);
}

// Nombre de jours entre le 1er janvier et le premier jour d'un mois
// Cette valeur pourrait être calculée en utilisant monthLength
// mais il est plus efficace de mettre dans un tableau des valeurs
// calculées au préalable.
int Date::monthAcc(int month, int year) {
  // Tableau du nombre de jours avant le 1er de chaque mois
  static int length[] = { 0, 31, 59, 90, 120, 151, 181, 
                          212, 243, 273, 304, 334 };
  return month < 3 ? length[month-1] : length[month-1] + leap(year);
}

// Calcul de l'année pour un nombre de jours écoulés depuis l'origine
int Date::sety(int n) {
  int yearorig = origin.y;      // Année de la date d'origine
  if (n >= 0) {
    // Premier calcul approximatif de l'année 
    // Comme 365.245 est supérieur au nombre de jours moyen dans l'année,
    // l'année calculée est inférieure à l'année réelle.
    y = yearorig + n/365.245; // Année de départ
    n -= (y - yearorig) * 365 + leap(yearorig, y);
    // Ensuite, on ajuste l'année
    while (n >= (leap(y) == 1 ? 366 : 365)) {
      n -= leap(y) == 1 ? 366 : 365;
      y++;
    }
  } else {
    // Premier calcul approximatif de l'année 
    // Comme n est négatif, year est au plus yearorig
    y = yearorig + (n+1)/365.245;
    n += (yearorig - y) * 365 + leap(y, yearorig);
    // Ensuite, on ajuste l'année
    while (n < 0) {
      y--;
      n += leap(y) == 1 ? 366 : 365;
    }
  }
  d = 1;                        // 1er janvier
  m = 1;
  // On retourne le nombre de jour entre le 1er janvier et le jour 
  return n;
}

// Calcul du jour et du mois pour un nombre de jours écoulés depuis
// le 1er janvier
void Date::setdm(int n) {
  // Premier calcul approximatif du mois
  // Comme le nombre moyen de jours par mois est ~30.4, le mois trouvé
  // est avant le mois cherché.
  // Le +1 est dû au fait que les mois sont numérotés à partir de 1
  m = n/31 + 1;
  // On ajuste
  while (m < 12 && n >= monthAcc(m+1, y))
    m++;
  // Le +1 est dû au fait que les jours sont numérotés à partir de 1
  d = n - monthAcc(m, y) + 1;
}

// Calcul de la date pour un nombre de jours écoulés depuis le 1er janvier
void Date::setdmy(int n) {
  // On calcule d'abord l'année puis le jour et le mois
  setdm(sety(n));
}

// Méthode de test
void Date::test() {
  for (int i = -365*400; i < 365*200; i++) {
    Date d0 = i;
    Date d1;
    int n = d1.sety(i);
    d1.setdm(n);
    if (d0 == d1) 
      cout << i << " : " << n << " : " << d0 << " : " << d1 << endl;
  }
}
