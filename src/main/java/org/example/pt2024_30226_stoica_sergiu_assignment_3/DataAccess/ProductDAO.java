package org.example.pt2024_30226_stoica_sergiu_assignment_3.DataAccess;

import org.example.pt2024_30226_stoica_sergiu_assignment_3.Connection.DatabaseConnection;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Client;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductDAO class provides CRUD operations for the Products model.
 */

public class ProductDAO {

    private Connection connection;

    /**
     * Instantiates a new ProductDAO and initializes the database connection.
     */

    public ProductDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    /**
     * Adds a new product to the database.
     *
     * @param products the product to be added
     * @throws SQLException if a database access error occurs
     */

    public void addProduct(Products products) throws SQLException {
        String query = "INSERT INTO products (id, name, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, products.getID());
            statement.setString(2, products.getName());
            statement.setInt(3, products.getQuantity());
            statement.setInt(4, products.getPrice());
            statement.executeUpdate();
        }
    }

    /**
     * Retrieves all products from the database.
     *
     * @return a list of products
     * @throws SQLException if a database access error occurs
     */

    public List<Products> getAllProducts() throws SQLException {
        List<Products> products = new ArrayList<>();
        String query = "SELECT id, name, quantity, price FROM products";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Products product = new Products(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("price")
                );
                products.add(product);
            }
        }
        return products;
    }

    /**
     * Updates an existing product in the database.
     *
     * @param products the product to be updated
     * @throws SQLException if a database access error occurs
     */

    public void updateProduct(Products products) throws SQLException {
        String query = "UPDATE products SET name = ?, quantity = ?, price = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, products.getName());
            statement.setInt(2, products.getQuantity());
            statement.setInt(3, products.getPrice());
            statement.setInt(4, products.getID());
            statement.executeUpdate();
        }
    }

    /**
     * Deletes a product from the database by its ID.
     *
     * @param productID the ID of the product to be deleted
     * @throws SQLException if a database access error occurs
     */

    public void deleteProduct(int productID) throws SQLException {
        String query = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, productID);
            statement.executeUpdate();
        }
    }

    /**
     * Retrieves a product from the database by their ID.
     *
     * @param id The unique identifier of the product to be retrieved.
     * @return A {@link Client} object containing the data of the found product, or null if no product is found.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */

    public boolean doesProductIdExist(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT COUNT(id) FROM products WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1) > 0;
        }
        return false;
    }
}
