package org.example.pt2024_30226_stoica_sergiu_assignment_3.BusinessLogic;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.DataAccess.ClientDAO;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.DataAccess.LogDAO;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Bill;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Client;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.PresentationLayer.MainMenu;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MainMenuService class provides the business logic for the main menu actions.
 */

public class MainMenuService {

    @FXML
    private TableView<Bill> billsTableView;

    private LogDAO logDAO = new LogDAO();

    /**
     * Handles the action of displaying the client view.
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onClientButton() throws IOException {
        System.out.println("Client View");
        MainMenu.showClientsView();
    }

    /**
     * Handles the action of displaying the product view.
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onProductView() throws IOException {
        System.out.println("Product View");
        MainMenu.showProductsView();
    }

    /**
     * Handles the action of displaying the order view.
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onOrderView() throws IOException {
        System.out.println("Order View");
        MainMenu.showOrderSView();
    }

    /**
     * Handles the action of showing all bills.
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onShowBills() throws IOException {
        System.out.println("Show Bills !");

        List<Bill> bills = new ArrayList<>();

        billsTableView.setVisible(true);

        try {
            bills = logDAO.getAllBills();
            System.out.println("All bills retrieved successfully!");

            TableUtil.populateTableWithObjects(billsTableView, bills);

        } catch (SQLException e) {
            System.err.println("Error retrieving clients from the database.");
            e.printStackTrace();
        }
    }
}