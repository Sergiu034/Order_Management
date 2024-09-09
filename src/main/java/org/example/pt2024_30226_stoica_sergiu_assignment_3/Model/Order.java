package org.example.pt2024_30226_stoica_sergiu_assignment_3.Model;

/**
 * Order class represents an order with an ID, client ID, product ID, and quantity.
 */

public class Order {

    private int ID;
    private int client_ID;
    private int product_ID;
    private int Quantity;

    /**
     * Constructs a new Order with the specified ID, client ID, product ID, and quantity.
     *
     * @param ID        the order ID
     * @param client_ID the client ID
     * @param product_ID the product ID
     * @param quantity  the quantity
     */

    public Order(int ID, int client_ID, int product_ID, int quantity) {
        this.ID = ID;
        this.client_ID = client_ID;
        this.product_ID = product_ID;
        Quantity = quantity;
    }

    /**
     * Gets the order ID.
     *
     * @return the order ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the order ID.
     *
     * @param ID the order ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets the client ID.
     *
     * @return the client ID
     */
    public int getClient_ID() {
        return client_ID;
    }

    /**
     * Sets the client ID.
     *
     * @param client_ID the client ID
     */
    public void setClient_ID(int client_ID) {
        this.client_ID = client_ID;
    }

    /**
     * Gets the product ID.
     *
     * @return the product ID
     */
    public int getProduct_ID() {
        return product_ID;
    }

    /**
     * Sets the product ID.
     *
     * @param product_ID the product ID
     */
    public void setProduct_ID(int product_ID) {
        this.product_ID = product_ID;
    }

    /**
     * Gets the quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return Quantity;
    }

    /**
     * Sets the quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }
}
