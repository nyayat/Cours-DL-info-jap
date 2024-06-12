import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Algo {
    private final int nbSommets;
    private final int nbJoueurs;
    private final int[][] distance;
    private final ArrayList<Joueur> joueurs = new ArrayList<>();
    private final ArrayList<Equipe> equipes = new ArrayList<>();

    Algo(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);

        this.nbSommets = sc.nextInt();
        this.distance = new int[nbSommets][nbSommets];
        this.nbJoueurs = sc.nextInt();
        for (int i = 0; i < nbJoueurs; i++) {
            joueurs.add(new Joueur());
        }
        int nbEquipes = sc.nextInt();
        for (int i = 0; i < nbEquipes; i++) {
            equipes.add(new Equipe());
        }
        int nbArcs = sc.nextInt();
        for (int i = 0; i < nbSommets; i++) {
            for (int j = 0; j < nbSommets; j++) {
                distance[i][j] = -1;
            }
        }
        for (int i = 0; i < nbArcs; i++) {
            distance[sc.nextInt() - 1][sc.nextInt() - 1] = sc.nextInt();
        }
    }

    static int getMin(HashMap<Integer, Long> file) {
        Map.Entry<Integer, Long> minElem = file.entrySet().stream().min(Map.Entry.comparingByValue()).get();
        int min = minElem.getKey();
        file.remove(min);
        return min;
    }

    long[] dijkstra(boolean reversed) {
        HashMap<Integer, Long> filePriority = new HashMap<>();
        long[] d = new long[nbSommets];
        for (int i = 0; i < nbSommets; i++) {
            d[i] = Long.MAX_VALUE;
        }
        d[nbJoueurs] = 0;
        filePriority.put(nbJoueurs, 0L);
        while (!filePriority.isEmpty()) {
            int u = getMin(filePriority);
            for (int v = 0; v < nbSommets; v++) {
                long poids = reversed ? distance[u][v] : distance[v][u];
                if (v != u && poids != -1 && d[v] > d[u] + poids) {
                    d[v] = d[u] + poids;
                    filePriority.put(v, d[v]);
                }
            }
        }
        return d;
    }

    void repartition() {
        long[] arbitreJoueur = dijkstra(false);
        long[] joueurArbitre = dijkstra(true);
        for (int i = 0; i < nbJoueurs; i++) {
            joueurs.get(i).setPoids(arbitreJoueur[i] + joueurArbitre[i]);
        }
        joueurs.sort(Comparator.comparing(Joueur::getPoids).reversed());
        for (Joueur j : joueurs) {
            Equipe e = equipes.stream().min(Comparator.comparing(x -> x.calculeCoutWith(j.getPoids()) - x.getCout())).get();
            e.ajouterJoueur(j);
        }
    }

    long getCout(){
        long cout = 0;
        for (Equipe e : equipes)
            cout += e.getCout();
        return cout;
    }
}
