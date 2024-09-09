package org.example.pt2024_30226_stoica_sergiu_assignment_3.Model;

/**
 * Products class represents a product with an ID, name, price, and quantity.
 */

public class Products {

    private int ID;
    private String Name;
    private int Price;
    private int Quantity;

    /**
     * Constructs a new Products instance with the specified ID, name, quantity, and price.
     *
     * @param ID       the product ID
     * @param name     the product name
     * @param quantity the product quantity
     * @param price    the product price
     */

    public Products(int ID, String name, int quantity, int price) {
        this.ID = ID;
        Name = name;
        Price = price;
        Quantity = quantity;
    }

    /**
     * Gets the product ID.
     *
     * @return the product ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the product ID.
     *
     * @param ID the product ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets the product name.
     *
     * @return the product name
     */
    public String getName() {
        return Name;
    }

    /**
     * Sets the product name.
     *
     * @param name the product name
     */
    public void setName(String name) {
        this.Name = name;
    }

    /**
     * Gets the product price.
     *
     * @return the product price
     */
    public int getPrice() {
        return Price;
    }

    /**
     * Sets the product price.
     *
     * @param price the product price
     */
    public void setPrice(int price) {
        this.Price = price;
    }

    /**
     * Gets the product quantity.
     *
     * @return the product quantity
     */
    public int getQuantity() {
        return Quantity;
    }

    /**
     * Sets the product quantity.
     *
     * @param quantity the product quantity
     */
    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }
}
