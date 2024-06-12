#include "menu.h"
#include "ortools/linear_solver/linear_solver.h"

using namespace operations_research;

vector<double> MenuMoinsCher(const vector<double>& ajr,
                             const vector<vector<double>>& apports_par_ingredient,
                             const vector<double>& prix_par_ingredient) {
  MPSolver solver("Mon solveur", MPSolver::GLOP_LINEAR_PROGRAMMING);
  const int num_ingredients = apports_par_ingredient.size();
  const int num_elements = ajr.size();

  // CONSEILS -- BIEN LES LIRE EN ENTIER!
  // - Regarder le fichier "../examples/cpp/linear_programming.cc" pour voir
  //   comment définir des variables, des contraintes, et l'objectif.
  // - Contrairement a l'exemple, on a pas un nombre constant, prédéfini, de
  //   variables et de contraintes: elles dépendent de l'entrée. Il peut
  //   être utile de créér un tableau de MPVariable* stockant les variables
  //   créées pour modéliser le problème. Idem pour les contraintes.
  //   Et on les crééra dans des boucles "for" a priori.
  // - N'oubliez pas qu'après Solve() il faut encore renvoyer la solution
  //   dans le format demandé!
  TODO
}
