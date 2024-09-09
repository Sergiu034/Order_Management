package org.example.pt2024_30226_stoica_sergiu_assignment_3.BusinessLogic;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.lang.reflect.Field;
import java.util.List;

/**
 * TableUtil class provides utility methods for populating JavaFX TableView with objects.
 */

public class TableUtil {

    /**
     * Populates a TableView with a list of objects.
     *
     * @param table   the TableView to be populated
     * @param objects the list of objects to populate the table with
     * @param <T>     the type of objects
     */

    public static <T> void populateTableWithObjects(TableView<T> table, List<T> objects) {
        if (objects == null || objects.isEmpty()) {
            return;
        }

        Class<?> clazz = objects.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();

        table.getColumns().clear();

        for (Field field : fields) {
            field.setAccessible(true);
            TableColumn<T, Object> column = new TableColumn<>(field.getName());
            column.setCellValueFactory(cellData -> {
                try {
                    return new ReadOnlyObjectWrapper<>(field.get(cellData.getValue()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                }
            });
            table.getColumns().add(column);
        }

        ObservableList<T> data = FXCollections.observableArrayList(objects);
        table.setItems(data);
    }
}