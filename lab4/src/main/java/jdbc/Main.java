package jdbc;

import jdbc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    // 4 на статиках (не рекомендуется)
    public static void main(String[] args) {
        try (Connection connection = JDBCUtils.getNewConnection()) {

            JDBCUtils.createTable(connection);
            JDBCUtils.fillRandomData(connection);

            while (true){
                JDBCUtils.handle(connection);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }

}
