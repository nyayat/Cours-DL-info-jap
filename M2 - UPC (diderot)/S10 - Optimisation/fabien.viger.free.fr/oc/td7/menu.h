#ifndef __MENU_H
#define __MENU_H

#include <vector>

using std::vector;

// On va résoudre le problème du "menu le plus économique".
// Comme sur les paquets de céréales, on s'intéresse aux apport nutritifs
// de nos ingrédients en calories, protéines, lipides, vitamine B1, etc.
//
// On connait la valeur des Apports Journaliers Recommandés (AJR) pour chacun
// de ces éléments: elle est donnée dans le tableau "ajr".
// Par exemple, si on veut au moins 2000 calories par jour, au moins 60g de
// protéines, etc, on aura ajr = [2000, 60, ...].
//
// On a aussi une liste d'ingrédients: patates, riz, jus d'orange, poulet, etc.
// Pour chaque ingrédient, on connait la valeur de ses apport dans tous les
// éléments (pour 1kg de cet ingrédient), dans le même ordre que la liste "ajr".
// Par exemple, 1kg de patate apporte 2500 calories, 10g de proteines, etc;
// 1kg de riz apporte 4000 calories, 13g de proteines, etc; et ainsi de suite
// pour les autres ingrédients: le tableau "apports_par_ingredient" contiendra
// [[2500, 10, ...],  // 1kg de patates
//  [4000, 13, ...],  // 1kg de riz
//  ... ]             // .. autres ingrédients ..
// 
// Enfin, on a le prix au kg de chaque ingrédient dans "prix_par_ingredient".
// Les ingrédients sont listés dans le même ordre que dans
// "apports_par_ingredient". Par exemple, si 1kg de patate coûte 3.2€, 1kg de
// riz coûte 5.4€, etc, on aura prix_par_ingredient = [3.2, 5.4, ...]. 
//
// Notre but: trouver le menu le moins cher possible pour aujourd'hui! Quels
// ingrédients acheter, et en quelle quantité, pour couvrir *au moins* les AJR
// (on peut avoir 'trop' d'un élément, par exemple trop de calories: ce n'est
// pas grave!)
//
// Cette fonction doit trouver le menu journalier le moins cher, et renvoyer
// les quantités de chaque ingrédient dans ce menu, dans le même ordre que
// "prix_par_ingredient".
// Par exemple si le menu idéal journalier est:
// 0.3kg de patates, 0kg de riz, 0.2kg de jus d'orange, ...
// Le tableau renvoyé sera: [0.3, 0, 0.2, ...].
vector<double> MenuMoinsCher(const vector<double>& ajr,
                             const vector<vector<double>>& apports_par_ingredient,
                             const vector<double>& prix_par_ingredient);

#endif  // __MENU_H
