package graphic;

import graphic.controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.database.db.DataBase;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
//5 лаба
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            String password1 = "root";
            String user1 = "root";
            String dbURL1 = "jdbc:mariadb://localhost:3306/test";

            DataBase db = new DataBase(dbURL1, user1, password1, "goodsTable");
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));

            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.setTitle("Goods");
            primaryStage.show();

            Controller controller = loader.getController();
            controller.setDataBase(db);
            controller.setTableGUI();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

}