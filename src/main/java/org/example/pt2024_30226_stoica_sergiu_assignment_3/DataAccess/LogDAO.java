package org.example.pt2024_30226_stoica_sergiu_assignment_3.DataAccess;

import org.example.pt2024_30226_stoica_sergiu_assignment_3.Connection.DatabaseConnection;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Bill;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * LogDAO class provides methods to generate bills and retrieve them from the database.
 */

public class LogDAO {

    private Connection connection;

    /**
     * Instantiates a new LogDAO and initializes the database connection.
     */

    public LogDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    /**
     * Generates a bill and logs it in the database.
     *
     * @param orderId   the order id
     * @param clientId  the client id
     * @param productId the product id
     * @param quantity  the quantity of the product
     * @throws SQLException if a database access error occurs
     */

    public void generateBill(int orderId, int clientId, int productId, int quantity) throws SQLException {
        String query = "SELECT p.name AS product_name, p.price, c.name AS client_name " +
                "FROM products p, clients c WHERE p.id = ? AND c.id = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, productId);
        stmt.setInt(2, clientId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String productName = rs.getString("product_name");
            double price = rs.getDouble("price");
            String clientName = rs.getString("client_name");
            double amount = price * quantity;

            String insertLogQuery = "INSERT INTO Log (order_id, amount, client_name, product_name, quantity) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement logStmt = connection.prepareStatement(insertLogQuery);
            logStmt.setInt(1, orderId);
            logStmt.setDouble(2, amount);
            logStmt.setString(3, clientName);
            logStmt.setString(4, productName);
            logStmt.setInt(5, quantity);
            logStmt.executeUpdate();
            logStmt.close();
        }
        stmt.close();
    }

    /**
     * Retrieves all bills from the database.
     *
     * @return a list of bills
     * @throws SQLException if a database access error occurs
     */

    public List<Bill> getAllBills() throws SQLException {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT id, order_id, amount, client_name, product_name, quantity FROM log";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Bill bill = new Bill(
                        resultSet.getInt("order_id"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("client_name"),
                        resultSet.getString("product_name"),
                        resultSet.getInt("quantity")
                );
                bills.add(bill);
            }
        }
        return bills;
    }
}
