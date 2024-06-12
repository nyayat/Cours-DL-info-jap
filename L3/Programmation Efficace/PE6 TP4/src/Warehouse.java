import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private final int id;
    private final Coordinates coordinates;
    private final HashMap<Integer, Integer> inventory;

    Warehouse(int id, int x, int y, HashMap<Integer, Integer> inventory) {
        this.id = id;
        this.coordinates = new Coordinates(x, y);
        this.inventory = inventory;
    }

    public int getId() {
        return id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Renvoie le nombre de produits d'un certain type
     *
     * @param productType - un type de produit
     * @return le nombre de produits disponible du type demandé
     */
    public int getType(int productType) {
        return inventory.getOrDefault(productType, 0);
    }

    /**
     * Retire n produits d'un certain type
     * On suppose qu'il y a au moins n produits de ce type
     *
     * @param productType - un type de produit
     * @param n           - le nombre de produits du type à retirer
     */
    public void removeType(int productType, int n) {
        int disponible = getType(productType);
        if (disponible == n)
            inventory.remove(productType);
        else inventory.put(productType, disponible - n);
    }

    /**
     * Détermine les produits correspondant à une commande
     *
     * @param order - une commande d'un client
     * @return une liste des produits présents dans la commande
     */
    public HashMap<Integer, Integer> has(Order order) {
        HashMap<Integer, Integer> res = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : inventory.entrySet()) {
            int type = entry.getKey();
            int nb = order.getType(type);
            if (nb > 0)
                res.put(type, Math.min(entry.getValue(), nb));
        }
        return res;
    }
}
