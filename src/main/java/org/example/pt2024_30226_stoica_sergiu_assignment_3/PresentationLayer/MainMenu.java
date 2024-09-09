package org.example.pt2024_30226_stoica_sergiu_assignment_3.PresentationLayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.BusinessLogic.TableUtil;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.DataAccess.LogDAO;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Bill;
import org.example.pt2024_30226_stoica_sergiu_assignment_3.Model.Client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MainMenu class serves as the main entry point for the application and handles the loading of different views.
 */

public class MainMenu extends Application {

    /**
     * Starts the JavaFX application and sets up the main stage.
     *
     * @param stage the primary stage for this application
     * @throws IOException if the FXML file cannot be loaded
     */

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("/org/example/pt2024_30226_stoica_sergiu_assignment_3/main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Orders Management!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Displays the clients view in a new stage.
     *
     * @throws IOException if the FXML file cannot be loaded
     */

    public static void showClientsView() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("/org/example/pt2024_30226_stoica_sergiu_assignment_3/client-view.fxml"));
        AnchorPane clientsView = fxmlLoader.load();

        Stage clientStage = new Stage();
        clientStage.setTitle("Clients View!");

        Scene clientScene = new Scene(clientsView);
        clientStage.setScene(clientScene);
        clientStage.show();
    }

    /**
     * Displays the products view in a new stage.
     *
     * @throws IOException if the FXML file cannot be loaded
     */

    public static void showProductsView() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("/org/example/pt2024_30226_stoica_sergiu_assignment_3/product-view.fxml"));
        AnchorPane productView = fxmlLoader.load();

        Stage productStage = new Stage();
        productStage.setTitle("Products View!");

        Scene clientScene = new Scene(productView);
        productStage.setScene(clientScene);
        productStage.show();
    }

    /**
     * Displays the orders view in a new stage.
     *
     * @throws IOException if the FXML file cannot be loaded
     */

    public static void showOrderSView() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("/org/example/pt2024_30226_stoica_sergiu_assignment_3/order-view.fxml"));
        AnchorPane orderView = fxmlLoader.load();

        Stage orderStage = new Stage();
        orderStage.setTitle("Orders View!");

        Scene clientScene = new Scene(orderView);
        orderStage.setScene(clientScene);
        orderStage.show();
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        launch();
    }
}