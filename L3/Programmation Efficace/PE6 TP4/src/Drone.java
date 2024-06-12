import java.util.ArrayList;
import java.util.HashMap;

public class Drone {
    private final Supervisor supervisor;
    private final int id;
    private final Coordinates coordinates;
    private final int maxLoadDrone;
    private int weight;
    private int nbTurnsLocked;
    private final HashMap<Integer, Integer> inventory;
    private ArrayList<String> commands;

    public Drone(Supervisor supervisor, int id, int maxLoadDrone, Coordinates coordinate) {
        this.supervisor = supervisor;
        this.id = id;
        this.maxLoadDrone = maxLoadDrone;
        this.coordinates = new Coordinates(coordinate);
        this.inventory = new HashMap<>();
        this.commands = new ArrayList<>();
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public HashMap<Integer, Integer> getInventory() {
        return inventory;
    }

    public ArrayList<String> getAndRemoveCommands() {
        ArrayList<String> res = commands;
        commands = new ArrayList<>();
        return res;
    }

    public void nextTurn() {
        if (nbTurnsLocked > 0)
            nbTurnsLocked--;
    }

    public boolean isAvailable() {
        return (this.nbTurnsLocked == 0);
    }

    /**
     * Détermine le nombre maximum de produits d'un certain type que le drone peut charger
     *
     * @param productType - un type de produit
     * @return le nombre de produits qu'il peut charger
     */
    public int getMaxLoad(int productType) {
        return (maxLoadDrone - weight) / supervisor.productWeight[productType];
    }

    /**
     * Charge n produits d'un certain type depuis l'entrepôt w
     * Le drone est supposé disponible et pouvoir charger les n produits
     *
     * @param w           - un entrepôt
     * @param productType - un type de produit
     * @param n           - le nombre de produits à charger
     * @return le nombre de tours nécessaire
     */
    public int load(Warehouse w, int productType, int n) {
        // ajoute la ligne de commande
        commands.add(id + " L " + w.getId() + " " + productType + " " + n);
        // déplace le drone
        int d = coordinates.distance(w.getCoordinates());
        nbTurnsLocked += d + 1;
        coordinates.set(w.getCoordinates());
        // charge le drone
        inventory.put(productType, inventory.getOrDefault(productType, 0) + n);
        w.removeType(productType, n);
        weight += n * supervisor.productWeight[productType];
        return d + 1;
    }

    /**
     * Livre n produits d'un certain type à un client
     * Le drone est supposé disponible et pouvoir livrer les n produits
     *
     * @param o           - une commande
     * @param productType - un type de produit
     * @param n           - le nombre de produits à livrer
     * @return le nombre de tours nécessaire
     */
    public int deliver(Order o, int productType, int n) {
        // ajoute la ligne de commande
        commands.add(id + " D " + o.getId() + " " + productType + " " + n);
        // déplace le drone
        int d = coordinates.distance(o.getCoordinates());
        nbTurnsLocked += d + 1;
        coordinates.set(o.getCoordinates());
        // livre le client
        int disponible = inventory.get(productType);
        if (disponible == n)
            inventory.remove(productType);
        else inventory.put(productType, disponible - n);
        o.deliver(productType, n);
        weight -= n * supervisor.productWeight[productType];
        return d + 1;
    }
}
