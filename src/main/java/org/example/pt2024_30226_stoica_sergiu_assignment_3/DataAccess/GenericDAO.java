package org.example.pt2024_30226_stoica_sergiu_assignment_3.DataAccess;

import org.example.pt2024_30226_stoica_sergiu_assignment_3.Connection.DatabaseConnection;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GenericDAO class provides CRUD operations for any type of object.
 *
 * @param <T> the type parameter
 */

public class GenericDAO<T> {
    private final Class<T> type;

    /**
     * Instantiates a new GenericDAO.
     *
     * @param type the type of the object
     */

    public GenericDAO(Class<T> type) {
        this.type = type;
    }

    /**
     * Create a new record in the database.
     *
     * @param object the object to be created
     * @throws SQLException if a database access error occurs
     */

    public void create(T object) throws SQLException {
        StringBuilder sql = new StringBuilder("INSERT INTO ");
        sql.append(type.getSimpleName()).append(" (");
        Field[] fields = type.getDeclaredFields();

        for (Field field : fields) {
            sql.append(field.getName()).append(",");
        }

        sql.setLength(sql.length() - 1);
        sql.append(") VALUES (");

        for (int i = 0; i < fields.length; i++) {
            sql.append("?,");
        }

        sql.setLength(sql.length() - 1);
        sql.append(")");

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                ps.setObject(i + 1, fields[i].get(object));
            }

            ps.executeUpdate();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Update an existing record in the database.
     *
     * @param object the object to be updated
     * @throws SQLException if a database access error occurs
     */

    public void update(T object) throws SQLException {
        StringBuilder sql = new StringBuilder("UPDATE ");
        sql.append(type.getSimpleName()).append(" SET ");
        Field[] fields = type.getDeclaredFields();

        for (Field field : fields) {
            sql.append(field.getName()).append(" = ?,");
        }

        sql.setLength(sql.length() - 1);
        sql.append(" WHERE id = ?");

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                ps.setObject(i + 1, fields[i].get(object));
            }

            Field idField = type.getDeclaredField("ID");
            idField.setAccessible(true);
            ps.setObject(fields.length + 1, idField.get(object));

            ps.executeUpdate();
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Delete a record from the database by its ID.
     *
     * @param id the id of the record to be deleted
     * @throws SQLException if a database access error occurs
     */

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM " + type.getSimpleName() + " WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * Checks if an object with the specified ID exists in the database.
     *
     * @param id The ID of the object to check for existence.
     * @return true if the object exists, false otherwise.
     * @throws SQLException if a database access error occurs or this method is called on a closed connection.
     */

    public boolean doesIdExist(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String query = "SELECT COUNT(id) FROM " + type.getSimpleName() + " WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1) > 0;
        }
        return false;
    }
}
