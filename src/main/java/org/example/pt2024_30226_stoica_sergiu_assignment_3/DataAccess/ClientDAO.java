package org.example.pt2024_30226_stoica_sergiu_assignment_3.DataAccess;

import org.example.pt2024_30226_stoica_sergiu_assignment_3.Connection.DatabaseConnection;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ClientDAO class provides CRUD operations for the Client model.
 */

public class ClientDAO {

    private Connection connection;

    /**
     * Instantiates a new ClientDAO and initializes the database connection.
     */

    public ClientDAO() {
        this.connection = DatabaseConnection.getConnection();
    }

    /**
     * Adds a new client to the database.
     *
     * @param client the client to be added
     * @throws SQLException if a database access error occurs
     */

    public void addClient(Client client) throws SQLException {
        String query = "INSERT INTO clients (id, name, surname, age) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, client.getID());
            statement.setString(2, client.getName());
            statement.setString(3, client.getSurname());
            statement.setInt(4, client.getAge());
            statement.executeUpdate();
        }
    }

    /**
     * Retrieves all clients from the database.
     *
     * @return a list of clients
     * @throws SQLException if a database access error occurs
     */

    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT id, name, surname, age FROM clients";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Client client = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("age")
                );
                clients.add(client);
            }
        }
        return clients;
    }

    /**
     * Updates an existing client in the database.
     *
     * @param client the client to be updated
     * @throws SQLException if a database access error occurs
     */

    public void updateClient(Client client) throws SQLException {
        String query = "UPDATE clients SET name = ?, surname = ?, age = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getSurname());
            statement.setInt(3, client.getAge());
            statement.setInt(4, client.getID());
            statement.executeUpdate();
        }
    }

    /**
     * Deletes a client from the database by their ID.
     *
     * @param clientId the ID of the client to be deleted
     * @throws SQLException if a database access error occurs
     */

    public void deleteClient(int clientId) throws SQLException {
        String query = "DELETE FROM clients WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, clientId);
            statement.executeUpdate();
        }
    }

    /**
     * Retrieves a client from the database by their ID.
     *
     * @param id The unique identifier of the client to be retrieved.
     * @return A {@link Client} object containing the data of the found client, or null if no client is found.
     * @throws SQLException If a database access error occurs or this method is called on a closed connection.
     */

    public Client findClientById(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT * FROM clients WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Client(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getInt("age")
            );
        }
        return null;
    }
}
