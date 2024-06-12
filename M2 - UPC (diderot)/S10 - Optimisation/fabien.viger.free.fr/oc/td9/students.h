#include <vector>

using std::vector;

/* 
DESCRIPTION DU PROBLÈME

* Les étudiants sont numérotés de 0 à num_etudiants-1
* Les cours sont numérotés de 0 à num_cours-1, chacun a une limite en nombre d'étudiants
* Ces limites sont stockées dans l'entrée "cours"
* Chaque étudiant assigne des valeurs réelles aux différents cours: 
    0 s'il ne veut pas s'inscrire, 
    jusqu'à 10 s'il veut absolument s'inscrire
* La somme de ces valeurs vaut toujours 10.

* Ces valeurs sont stockées dans l'entrée "valeurs": 
    valeurs[j][i] contient la valeur que l'étudiant j assigne au cours i
* La "valeur totale" d'un étudiant j est la somme des valeurs[j][i] pour tous
    les cours auxquels il a pu être assigné.

* On ne peut pas assigner tout le monde à ses cours préférés, mais on souhaite
    que le moins bien loti ne se plaigne pas: il faut MAXIMISER la "proportion de satisfaction"
    MINIMALE parmi tous les étudiants.

* On renverra cette "valeur du moins bien loti".
*/

double StudentsAssignment(const vector<int>& cours,
                               const vector<vector<double>> valeurs);


