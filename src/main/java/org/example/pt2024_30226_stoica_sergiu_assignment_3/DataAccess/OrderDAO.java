package org.example.pt2024_30226_stoica_sergiu_assignment_3.DataAccess;

import org.example.pt2024_30226_stoica_sergiu_assignment_3.Connection.DatabaseConnection;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Bill;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Client;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderDAO class provides methods to manage orders in the database.
 */

public class OrderDAO {

    private Connection connection;

    /**
     * Instantiates a new OrderDAO and initializes the database connection.
     */

    public OrderDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    private LogDAO logDAO = new LogDAO();

    /**
     * Creates a new order in the database and generates a corresponding bill.
     *
     * @param order the order to be created
     * @throws SQLException if a database access error occurs
     */

    public void createOrder(Order order) throws SQLException {
        String createQuery = "INSERT INTO orders (id, client_id, product_id, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(createQuery)) {
            stmt.setInt(1, order.getID());
            stmt.setInt(2, order.getClient_ID());
            stmt.setInt(3, order.getProduct_ID());
            stmt.setInt(4, order.getQuantity());
            logDAO.generateBill(order.getID(), order.getClient_ID(), order.getProduct_ID(), order.getQuantity());
            stmt.executeUpdate();
        }
    }

    /**
     * Validates client and product IDs and checks product stock.
     * If valid and stock is sufficient, updates the product stock.
     *
     * @param clientId the client id
     * @param productId the product id
     * @param quantity the quantity to be checked
     * @return true if validation and stock check are successful, false otherwise
     * @throws SQLException if a database access error occurs
     */

    public boolean validateIdsAndCheckStock(int clientId, int productId, int quantity) throws SQLException {
        String checkClientQuery = "SELECT COUNT(1) FROM clients WHERE id = ?";
        String checkProductQuery = "SELECT quantity FROM products WHERE id = ?";
        String updateProductQuery = "UPDATE products SET quantity = quantity - ? WHERE id = ? AND quantity >= ?";

        try (PreparedStatement checkClientStmt = connection.prepareStatement(checkClientQuery);
             PreparedStatement checkProductStmt = connection.prepareStatement(checkProductQuery);
             PreparedStatement updateProductStmt = connection.prepareStatement(updateProductQuery)) {

            connection.setAutoCommit(false);

            checkClientStmt.setInt(1, clientId);
            ResultSet clientResult = checkClientStmt.executeQuery();
            if (!clientResult.next() || clientResult.getInt(1) == 0) {
                connection.rollback();
                return false;
            }

            // Check if product ID exists and has enough stock
            checkProductStmt.setInt(1, productId);
            ResultSet productResult = checkProductStmt.executeQuery();
            if (productResult.next() && productResult.getInt("quantity") >= quantity) {
                // Update stock
                updateProductStmt.setInt(1, quantity);
                updateProductStmt.setInt(2, productId);
                updateProductStmt.setInt(3, quantity);
                int affectedRows = updateProductStmt.executeUpdate();
                if (affectedRows == 1) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
