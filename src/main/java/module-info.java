module org.example.pt2024_30226_stoica_sergiu_assignment_3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.pt2024_30226_stoica_sergiu_assignment_3 to javafx.fxml;
    exports org.example.pt2024_30226_stoica_sergiu_assignment_3.PresentationLayer;
    opens org.example.pt2024_30226_stoica_sergiu_assignment_3.PresentationLayer to javafx.fxml;
    exports org.example.pt2024_30226_stoica_sergiu_assignment_3.BusinessLogic;
    opens org.example.pt2024_30226_stoica_sergiu_assignment_3.BusinessLogic to javafx.fxml;
}