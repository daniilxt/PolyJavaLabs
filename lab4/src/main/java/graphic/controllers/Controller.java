package graphic.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import project.database.db.DataBase;
import project.database.db.Product;

import java.util.Optional;

public class Controller {
    DataBase db;

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, Integer> columnID;

    @FXML
    private TableColumn<Product, Integer> columnProdID;

    @FXML
    private TableColumn<Product, String> columnTitle;

    @FXML
    private TableColumn<Product, Integer> columnPrice;

    @FXML
    private TextField formName;

    @FXML
    private TextField formPrice;

    @FXML
    private TextField formPriceFrom;

    @FXML
    private TextField formPriceTo;

    @FXML
    void add() {
        if (!formPrice.getText().isEmpty() && !formName.getText().isEmpty()) {
            try {
                int price = Integer.parseInt(formPrice.getText());
                db.add(new Product(formName.getText(), price));
                showAll();
            } catch (Exception ex) {
                alert();
            }
            clearForms();
        }
    }


    private void clearForms() {
        formName.clear();
        formPrice.clear();
        formPriceFrom.clear();
        formPriceTo.clear();
    }

    @FXML
    void clear() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete all records from Data Base");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            db.clear();
            clearForms();
            showAll();
        } else {
            System.out.println("");
        }
    }

    @FXML
    void delete() {
        if (!formName.getText().isEmpty()) {
            db.delete(formName.getText());
            clearForms();
            showAll();
        }
    }

    @FXML
    void find() {
        if (!formName.getText().isEmpty()) {
            int price = db.getByName(formName.getText());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Price of " + formName.getText() + ":   " + price);
            alert.showAndWait();
            clearForms();
        }
    }

    @FXML
    void edit() {
        if (!formPrice.getText().isEmpty() && !formName.getText().isEmpty()) {
            try {
                int price = Integer.parseInt(formPrice.getText());
                db.updatePrice(new Product(formName.getText(), price));
                showAll();
            } catch (Exception ex) {
                alert();
            }
            clearForms();
        }
    }

    @FXML
    void filter() {
        if (!formPriceFrom.getText().isEmpty() && !formPriceTo.getText().isEmpty()) {
            try {
                int from = Integer.parseInt(formPriceFrom.getText());
                int to = Integer.parseInt(formPriceTo.getText());
                tableView.getItems().clear();
                tableView.getItems().addAll(db.filterByPrice(from, to));
            } catch (Exception ex) {
                alert();
            }
            clearForms();
        }
    }

    @FXML
    void random() {
        db.fillRandomData();
        showAll();
    }

    @FXML
    public void showAll() {
        tableView.getItems().clear();
        tableView.getItems().addAll(db.getTableList());
    }

    @FXML
    public void setDataBase(DataBase db) {
        this.db = db;
    }

    @FXML
    public void setTableGUI() {
        columnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnProdID.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
        columnTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    private void alert() {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Attention");
        alert.setContentText("Incorrect input");

        alert.showAndWait();
    }

}
