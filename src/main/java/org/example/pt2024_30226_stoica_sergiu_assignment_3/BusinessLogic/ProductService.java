package org.example.pt2024_30226_stoica_sergiu_assignment_3.BusinessLogic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.DataAccess.GenericDAO;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.DataAccess.ProductDAO;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Client;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Products;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductService class provides the business logic for managing products.
 */

public class ProductService {

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField priceTextField;

    @FXML
    private Label helpMessage;

    @FXML
    private Button submitButton;

    @FXML
    private TableView<Products> productTableView;


    private int Case = 0;

    private ProductDAO specificProductDAO = new ProductDAO();
    private GenericDAO<Products> productDAO = new GenericDAO<>(Products.class);

    /**
     * Handles the action of adding a new product.
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onAddProduct() throws IOException {
        System.out.println("Add a new Product !");

        helpMessage.setText("Add the details of the new product bellow !");
        helpMessage.setTextFill(Color.BLACK);

        idTextField.setVisible(true);
        nameTextField.setVisible(true);
        nameTextField.setPromptText("Name");
        quantityTextField.setVisible(true);
        quantityTextField.setPromptText("Quantity");
        priceTextField.setVisible(true);
        priceTextField.setPromptText("Price");

        submitButton.setVisible(true);

        Case = 1;
    }

    /**
     * Handles the action of editing an existing product.
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onEditProduct() throws IOException {
        System.out.println("Edit a Product !");

        helpMessage.setText("Enter the ID of the product you wish to modify, and the new details bellow !");
        helpMessage.setTextFill(Color.BLACK);

        idTextField.setVisible(true);
        nameTextField.setVisible(true);
        nameTextField.setPromptText("New Name");
        quantityTextField.setVisible(true);
        quantityTextField.setPromptText("New Quantity");
        priceTextField.setVisible(true);
        priceTextField.setPromptText("New Price");

        submitButton.setVisible(true);

        Case = 2;
    }

    /**
     * Handles the action of deleting a product.
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onDeleteProduct() throws IOException {
        System.out.println("Delete a Product !");

        helpMessage.setText("Enter the ID of the product you wish to delete !");
        helpMessage.setTextFill(Color.BLACK);

        idTextField.setVisible(true);
        nameTextField.setVisible(false);
        quantityTextField.setVisible(false);
        priceTextField.setVisible(false);

        submitButton.setVisible(true);

        Case = 3;
    }

    /**
     * Handles the action of viewing all products.
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onViewProducts() throws IOException {
        System.out.println("View all products !");

        helpMessage.setText("This action will retrieve all products from the database !");
        helpMessage.setTextFill(Color.BLACK);

        nameTextField.setVisible(false);
        idTextField.setVisible(false);
        quantityTextField.setVisible(false);
        priceTextField.setVisible(false);

        submitButton.setVisible(true);
        productTableView.setVisible(true);
        Case = 4;
    }

    /**
     * Handles the action of submitting the form based on the current case (add, edit, delete, view).
     *
     * @throws IOException if an I/O error occurs
     */

    @FXML
    protected void onSubmitButton() throws IOException, SQLException {
        switch (Case) {

            case 1:
                int ID = Integer.parseInt(idTextField.getText());
                String Name = nameTextField.getText();
                int Quantity = Integer.parseInt(quantityTextField.getText());
                int Price = Integer.parseInt(priceTextField.getText());

                Products product = new Products(ID, Name, Quantity, Price);

                try {
                    //productDAO.addProduct(product);
                    productDAO.create(product);
                    System.out.println("Product added successfully!");
                    helpMessage.setText("Product added successfully !");
                    helpMessage.setTextFill(Color.GREEN);
                    refreshTableView();
                } catch (SQLException e) {
                    System.err.println("Error adding product to the database.");
                    helpMessage.setText("Error adding product to the database !");
                    helpMessage.setTextFill(Color.RED);
                    e.printStackTrace();
                }

                break;

            case 2:
                int editID = Integer.parseInt(idTextField.getText());

                if (productDAO.doesIdExist(editID)) {

                    String newName = nameTextField.getText();
                    int newQuantity = Integer.parseInt(quantityTextField.getText());
                    int newPrice = Integer.parseInt(priceTextField.getText());

                    Products editProducts = new Products(editID, newName, newQuantity, newPrice);

                    try {
                        //productDAO.updateProduct(editProduct);
                        productDAO.update(editProducts);
                        System.out.println("Product updated successfully!");
                        helpMessage.setText("Product updated successfully !");
                        helpMessage.setTextFill(Color.GREEN);
                        refreshTableView();
                    } catch (SQLException e) {
                        System.err.println("Error updating product to the database.");
                        helpMessage.setText("Error updating product to the database !");
                        helpMessage.setTextFill(Color.RED);
                        e.printStackTrace();
                    }
                } else {
                    helpMessage.setText("Product ID not found. Cannot edit.");
                    helpMessage.setTextFill(Color.RED);
                }
                break;

            case 3:
                int deleteID = Integer.parseInt(idTextField.getText());

                if (productDAO.doesIdExist(deleteID)) {

                    try {
                        //productDAO.deleteProduct(deleteID);
                        productDAO.delete(deleteID);
                        System.out.println("Product deleted successfully!");
                        helpMessage.setText("Product deleted successfully !");
                        helpMessage.setTextFill(Color.GREEN);
                        refreshTableView();
                    } catch (SQLException e) {
                        System.err.println("Error deleting product to the database.");
                        helpMessage.setText("Error deleting product to the database !");
                        helpMessage.setTextFill(Color.RED);
                        e.printStackTrace();
                    }
                } else {
                    helpMessage.setText("Product ID not found. Cannot delete.");
                    helpMessage.setTextFill(Color.RED);
                }

                break;

            case 4:
                List<Products> products = new ArrayList<>();

                try {
                    //products = productDAO.getAllProducts();
                    products = specificProductDAO.getAllProducts();
                    System.out.println("All products retrieved successfully!");

                    for(Products retrievedProducts : products){
                        System.out.println(retrievedProducts.getID() + " " + retrievedProducts.getName() + " " + retrievedProducts.getQuantity() + " " + retrievedProducts.getPrice());
                    }

                    TableUtil.populateTableWithObjects(productTableView, products);

                } catch (SQLException e) {
                    System.err.println("Error retrieving products from the database.");
                    e.printStackTrace();
                }

                break;

            default:
                System.out.println("No action needed !");
        }
    }

    /**
     * Refreshes the TableView with the latest product data from the database.
     *
     * @throws SQLException If there is an error fetching product data from the database,
     * the error is caught within the method and printed to the standard error stream.
     */

    private void refreshTableView() {
        try {
            ObservableList<Products> products = FXCollections.observableArrayList(specificProductDAO.getAllProducts());
            productTableView.setItems(products);
        } catch (SQLException e) {
            System.err.println("Error refreshing the table view.");
            e.printStackTrace();
        }
    }
}
