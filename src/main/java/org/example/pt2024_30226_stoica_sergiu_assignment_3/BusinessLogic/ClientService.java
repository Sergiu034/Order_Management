package org.example.pt2024_30226_stoica_sergiu_assignment_3.BusinessLogic;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Connection.DatabaseConnection;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.DataAccess.ClientDAO;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Client;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClientService class provides the business logic for managing clients.
 */

public class ClientService {

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private Label helpMessage;

    @FXML
    private Button submitButton;

    @FXML
    private TableView<Client> clientTableView;

    private ClientDAO clientDAO = new ClientDAO();

    private int Case = 0;

    /**
     * Handles the action of adding a new client.
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onAddClient() throws IOException {
        System.out.println("Add a new Client !");


        helpMessage.setText("Add the details of the new client bellow !");
        helpMessage.setTextFill(Color.BLACK);
        idTextField.setVisible(true);
        nameTextField.setVisible(true);
        nameTextField.setPromptText("Name");
        surnameTextField.setVisible(true);
        surnameTextField.setPromptText("Surname");
        ageTextField.setVisible(true);
        ageTextField.setPromptText("Age");
        submitButton.setVisible(true);

        Case = 1;
    }

    /**
     * Handles the action of editing an existing client.
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onEditClient() throws IOException {
        System.out.println("Edit a Client !");

        helpMessage.setText("Enter the ID of the client you wish to modify, and the new details bellow !");
        helpMessage.setTextFill(Color.BLACK);
        idTextField.setVisible(true);
        nameTextField.setVisible(true);
        nameTextField.setPromptText("New Name");
        surnameTextField.setVisible(true);
        surnameTextField.setPromptText("New Surname");
        ageTextField.setVisible(true);
        ageTextField.setPromptText("New Age");
        submitButton.setVisible(true);

        Case = 2;
    }

    /**
     * Handles the action of deleting a client.
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onDeleteClient() throws IOException {
        System.out.println("Delete a Client !");

        helpMessage.setText("Enter the ID of the client you wish to delete !");
        helpMessage.setTextFill(Color.BLACK);
        idTextField.setVisible(true);
        nameTextField.setVisible(false);
        surnameTextField.setVisible(false);
        ageTextField.setVisible(false);
        submitButton.setVisible(true);

        Case = 3;
    }

    /**
     * Handles the action of viewing all clients.
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onViewClients() throws IOException {
        System.out.println("View all clients !");

        helpMessage.setText("This action will retrieve all clients from the database !");
        helpMessage.setTextFill(Color.BLACK);
        nameTextField.setVisible(false);
        idTextField.setVisible(false);
        surnameTextField.setVisible(false);
        ageTextField.setVisible(false);

        submitButton.setVisible(true);
        clientTableView.setVisible(true);
        Case = 4;
    }

    /**
     * Handles the action of submitting the form based on the current case (add, edit, delete, view).
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onSubmitButton() throws IOException, SQLException {
        switch (Case){

            case 1:
                int ID = Integer.parseInt(idTextField.getText());
                String Name = nameTextField.getText();
                String Surname = surnameTextField.getText();
                int Age = Integer.parseInt(ageTextField.getText());

                Client client = new Client(ID, Name, Surname, Age);

                try {
                    clientDAO.addClient(client);
                    System.out.println("Client added successfully!");
                    helpMessage.setText("Client added successfully !");
                    helpMessage.setTextFill(Color.GREEN);
                    refreshTableView();
                } catch (SQLException e) {
                    System.err.println("Error adding client to the database.");
                    helpMessage.setText("Error adding client to the database !");
                    helpMessage.setTextFill(Color.RED);
                    e.printStackTrace();
                }

                break;

            case 2:
                int editID = Integer.parseInt(idTextField.getText());
                Client existingClient = clientDAO.findClientById(editID);

                if (existingClient != null) {

                    String newName = nameTextField.getText();
                    String newSurname = surnameTextField.getText();
                    int newAge = Integer.parseInt(ageTextField.getText());

                    Client editedClient = new Client(editID, newName, newSurname, newAge);

                    try {
                        clientDAO.updateClient(editedClient);
                        System.out.println("Client updated successfully!");
                        helpMessage.setText("Client updated successfully !");
                        helpMessage.setTextFill(Color.GREEN);
                        refreshTableView();
                    } catch (SQLException e) {
                        System.err.println("Error updating client to the database.");
                        helpMessage.setText("Error updating client to the database. !");
                        helpMessage.setTextFill(Color.RED);
                        e.printStackTrace();
                    }
                }
                else {
                    helpMessage.setText("Client ID not found. Cannot edit.");
                    helpMessage.setTextFill(Color.RED);
                }

                break;

            case 3:
                int deleteID = Integer.parseInt(idTextField.getText());
                Client clientToDelete = clientDAO.findClientById(deleteID);

                if (clientToDelete != null) {

                    try {
                        clientDAO.deleteClient(deleteID);
                        System.out.println("Client deleted successfully!");
                        helpMessage.setText("Client deleted successfully !");
                        helpMessage.setTextFill(Color.GREEN);
                        refreshTableView();
                    } catch (SQLException e) {
                        System.err.println("Error deleting client to the database.");
                        helpMessage.setText("Error deleting client to the database !");
                        helpMessage.setTextFill(Color.RED);
                        e.printStackTrace();
                    }
                } else {
                    helpMessage.setText("Client ID not found. Cannot delete.");
                    helpMessage.setTextFill(Color.RED);
                }

                break;

            case 4:
                List<Client> clients = new ArrayList<>();

                try {
                    clients = clientDAO.getAllClients();
                    System.out.println("All clients retrieved successfully!");

                    for(Client retrievedClient : clients){
                        System.out.println(retrievedClient.getID() + " " + retrievedClient.getName() + " " + retrievedClient.getSurname() + " " + retrievedClient.getAge());
                    }

                    TableUtil.populateTableWithObjects(clientTableView, clients);

                } catch (SQLException e) {
                    System.err.println("Error retrieving clients from the database.");
                    e.printStackTrace();
                }

                break;

            default:
                System.out.println("No action needed !");
        }
    }

    /**
     * Refreshes the TableView with the latest client data from the database.
     *
     * @throws SQLException If there is an error fetching client data from the database,
     * the error is caught within the method and printed to the standard error stream.
     */

    private void refreshTableView() {
        try {
            ObservableList<Client> clients = FXCollections.observableArrayList(clientDAO.getAllClients());
            clientTableView.setItems(clients);
        } catch (SQLException e) {
            System.err.println("Error refreshing the table view.");
            e.printStackTrace();
        }
    }
}
