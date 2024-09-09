package org.example.pt2024_30226_stoica_sergiu_assignment_3.BusinessLogic;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.DataAccess.ClientDAO;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.DataAccess.OrderDAO;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Order;

import java.io.IOException;
import java.sql.SQLException;

/**
 * OrderService class provides the business logic for managing orders.
 */

public class OrderService {

    @FXML
    private TextField idTextField;

    @FXML
    private TextField idClientTextField;

    @FXML
    private TextField idProductTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private Label helpMessage;

    private OrderDAO orderDAO = new OrderDAO();

    /**
     * Handles the action of creating a new order.
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onCreateOrder() throws IOException {
        System.out.println("Create a new Order !");
        helpMessage.setText("Checking stock !");
        helpMessage.setTextFill(Color.BLACK);

        int ID = Integer.parseInt(idTextField.getText());
        int clientId = Integer.parseInt(idClientTextField.getText());
        int productId = Integer.parseInt(idProductTextField.getText());
        int Quantity = Integer.parseInt(quantityTextField.getText());

        Order order = new Order(ID, clientId, productId, Quantity);

        try {
            if (orderDAO.validateIdsAndCheckStock(clientId, productId, Quantity)) {
                orderDAO.createOrder(order);
                helpMessage.setText("Order created successfully!");
                helpMessage.setTextFill(Color.GREEN);
            } else {
                helpMessage.setText("Invalid ID or not enough stock!");
                helpMessage.setTextFill(Color.RED);
            }
        } catch (SQLException e) {
            helpMessage.setText("Failed to create order, order ID already exists !");
            helpMessage.setTextFill(Color.RED);
            e.printStackTrace();
        }
    }
}
