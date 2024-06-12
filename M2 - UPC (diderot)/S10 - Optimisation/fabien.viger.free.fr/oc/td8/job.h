#ifndef __JOB_H
#define __JOB_H

#include <vector>

using std::vector;

// On veut affecter des tâches (job) à des machines.
// On a un ensemble de machines hétérogènes, et des descriptions de jobs.
// Chaque job utilise une quantité prédéfinie de CPU, RAM et disque de la
// machine sur laquelle il tournera, et chaque machine a une quantité
// prédéfinie des CPU, RAM et disque.
//
// On veut maximiser le nombre de jobs affectés.
//
// Cette fonction doit renvoyer un assignment optimal des jobs à chaque
// machine: l'élement #i sera le numero de machine à laquelle le job #i
// sera affecté, où -1 si on décide de ne pas affecter le job.
//
// Exemple:
// jobs = {{1, 10, 0}, {2, 30, 5}, {5, 30, 0}, {10, 2, 100}}
// machines = {{ 5, 40,  10},  // Peu de CPU, plein de RAM, peu de Disque 
//             {12, 10, 200}}  // Bcp de CPU, peu de RAM, plein de Disque
//
// Valeur de retour attendue:  {0, 0, -1, 1}.
// Explication: On s'attend à ce que les jobs #0 et #1 tournent sur la machine
// #0 et que le job #3 tourne sur la machine #1. Le job #2 ne tournera pas.
struct Resources {
  double cpu;
  double ram;
  double disk;
};
vector<int> BestJobAssignment(const vector<Resources>& jobs,
                              const vector<Resources>& machines);

#endif  // __JOB_H
