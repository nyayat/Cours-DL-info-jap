import java.util.HashMap;
import java.util.Map;

public class Order {
    private final int id;
    private final Coordinates coordinates;
    private final HashMap<Integer, Integer> products;

    Order(int id, int x, int y, HashMap<Integer, Integer> products) {
        this.id = id;
        this.coordinates = new Coordinates(x, y);
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public boolean isDelivered() {
        return products.isEmpty();
    }

    public int getNbProduit() {
        int res = 0;
        for (int nb : products.values()) {
            res += nb;
        }
        return res;
    }

    public int getPoids(int[] productWeight) {
        int res = 0;
        for (Map.Entry<Integer, Integer> entry : products.entrySet()) {
            res += productWeight[entry.getKey()] * entry.getValue();
        }
        return res;
    }

    /**
     * Renvoie le nombre de produits d'un certain type
     *
     * @param productType - un type de produit
     * @return le nombre de produits disponible du type demandé
     */
    public int getType(int productType) {
        return products.getOrDefault(productType, 0);
    }

    /**
     * Livre n produits d'un certain type
     * On suppose qu'il y a au moins n produits de ce type à livrer
     *
     * @param productType - un type de produit
     * @param n           - le nombre de produits du type à livrer
     */
    public void deliver(int productType, int n) {
        int restant = getType(productType);
        if (restant == n)
            products.remove(productType);
        else products.put(productType, restant - n);
    }
}
