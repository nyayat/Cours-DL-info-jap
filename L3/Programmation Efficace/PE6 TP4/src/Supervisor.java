import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Supervisor {
    int nbTurns;
    int[] productWeight;
    ArrayList<Drone> drones = new ArrayList<>();
    ArrayList<Warehouse> warehouses = new ArrayList<>();
    ArrayList<Order> orders = new ArrayList<>();
    ArrayList<String> commands = new ArrayList<>();

    Supervisor(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner sc = new Scanner(file);

        int nbRows = sc.nextInt();
        int nbColumns = sc.nextInt();
        int nbDrones = sc.nextInt();
        nbTurns = sc.nextInt();
        int maxLoadDrone = sc.nextInt();

        int nbProductTypes = sc.nextInt();
        productWeight = new int[nbProductTypes];
        for (int i = 0; i < nbProductTypes; i++) {
            productWeight[i] = sc.nextInt();
        }

        int nbWarehouses = sc.nextInt();
        for (int i = 0; i < nbWarehouses; i++) {
            int row = sc.nextInt();
            int column = sc.nextInt();
            HashMap<Integer, Integer> inventory = new HashMap<>();
            for (int j = 0; j < nbProductTypes; j++) {
                int nbItems = sc.nextInt();
                if (nbItems != 0) inventory.put(j, nbItems);
            }
            warehouses.add(new Warehouse(i, row, column, inventory));
        }

        for (int i = 0; i < nbDrones; i++) {
            drones.add(new Drone(this, i, maxLoadDrone, warehouses.get(0).getCoordinates()));
        }

        int nbOrders = sc.nextInt();
        for (int i = 0; i < nbOrders; i++) {
            int row = sc.nextInt();
            int column = sc.nextInt();
            int nbItems = sc.nextInt();
            HashMap<Integer, Integer> products = new HashMap<>();
            for (int j = 0; j < nbItems; j++) {
                int productType = sc.nextInt();
                products.put(productType, products.getOrDefault(productType, 0) + 1);
            }
            orders.add(new Order(i, row, column, products));
        }

        Comparator<Order> c = Comparator.comparing(Order::getNbProduit);
        orders.sort(c.thenComparing(o -> o.getPoids(productWeight)));
        sc.close();
    }

    /**
     * Calcul une solution au problème dans la liste commands
     */
    public void getSolution() {
        while (nbTurns > 0 && !orders.isEmpty()) {
            searchDronesCommand();
            // passe au tour suivant
            for (Drone d : drones) {
                d.nextTurn();
            }
            nbTurns--;
        }
    }

    /**
     * Recherche les commandes pour les drones
     */
    public void searchDronesCommand() {
        while (!orders.isEmpty()) {
            List<Drone> disponibles = drones.stream().filter(Drone::isAvailable).toList();
            if (disponibles.size() == 0) break;

            // le client à traiter
            Order o = orders.get(0);

            // trie les entrepôts selon la distance avec le client
            warehouses.sort(Comparator.comparing(w -> o.getCoordinates().distance(w.getCoordinates())));

            // cherche un entrepôt qui possède des produits de la commande et le nombre de produits à prendre
            Warehouse w = null;
            HashMap<Integer, Integer> toLoad = null;
            for (int i = 0; (toLoad == null || toLoad.isEmpty()) && i < warehouses.size(); i++) {
                w = warehouses.get(i);
                toLoad = w.has(o);
            }

            // cherche un drone disponible qui est le plus proche de l'entrepôt
            Warehouse finalW = w;
            Drone d = disponibles.stream().min(Comparator.comparing(x -> x.getCoordinates().distance(finalW.getCoordinates()))).get();

            int nbDroneTurn = 0;
            // charge le drone
            for (Map.Entry<Integer, Integer> entry : toLoad.entrySet()) {
                int n = Math.min(entry.getValue(), d.getMaxLoad(entry.getKey()));
                if (n > 0) nbDroneTurn += d.load(w, entry.getKey(), n);
            }

            // décharge le drone
            HashMap<Integer, Integer> toDeliver = new HashMap<>(d.getInventory());
            for (Map.Entry<Integer, Integer> entry : toDeliver.entrySet()) {
                nbDroneTurn += d.deliver(o, entry.getKey(), entry.getValue());
            }

            // si le client est complètement livré on peut le supprimer
            if (o.isDelivered()) orders.remove(o);

            // si la commande peut être complètement livré à temps, on enregistre les commandes du drone
            if (nbDroneTurn <= nbTurns) commands.addAll(d.getAndRemoveCommands());
        }
    }

    /**
     * écrit dans un fichier la solution trouvée
     *
     * @param filename - le nom du fichier d'entré
     */
    public void save(String filename) {
        try {
            FileWriter f = new FileWriter(filename.substring(0, filename.length() - 2) + "out");
            f.write(commands.size() + "\n");
            for (String s : commands)
                f.write(s + "\n");
            f.close();
        } catch (IOException e) {
        }
    }
}
