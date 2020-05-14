package project.database;

import project.database.db.DataBase;
import project.database.db.Handler;

import java.sql.SQLException;

public class Main {
// 4 на частично объектах (одобрено)
    public static void main(String[] args) {
        String dbURL1 = "jdbc:mariadb://localhost:3306/test";
        String user1 = "root";
        String password1 = "root";
        try {
            DataBase db1 = new DataBase(dbURL1,user1,password1,"goodsTable");
            Handler handler = new Handler(db1,System.in);
            handler.run();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
