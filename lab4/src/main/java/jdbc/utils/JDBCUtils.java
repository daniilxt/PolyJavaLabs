package jdbc.utils;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class JDBCUtils {


    public static Connection getNewConnection() throws SQLException {
        String dbURL = "jdbc:mariadb://localhost:3306/test";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(dbURL, user, password);

        if (connection.isValid(1)) {
            System.out.println("Connection successful");
        }
        return connection;
    }

    public static void createTable(Connection connection) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS " + "goodsTable" + "(" +
              "id INT NOT NULL," +
              "prod_id INT NOT NULL UNIQUE AUTO_INCREMENT," +
              "title VARCHAR(50) NOT NULL UNIQUE ," +
              "price INT NOT NULL," +
              "PRIMARY KEY(prod_id));");
        } catch (SQLException ex) {
            throw new RuntimeException("Can't create table", ex);
        }
    }

    public static void add(Connection connection, int id, String productName, int price) {
        String sql = "INSERT INTO " + "goodsTable" + " (id, title, price) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, productName);
            statement.setInt(3, price);
            statement.execute();
            System.out.println("Success");
        } catch (SQLIntegrityConstraintViolationException ex) {
            //throw new IllegalArgumentException("Product already exists");
            System.out.println("Product already exists");
        } catch (SQLException ex) {
           // throw new RuntimeException("Error with adding new product", ex);
            System.out.println("Error with adding new product");
        }
    }

    public static void delete(Connection connection, String productName) {
        String sql = "DELETE FROM " + "goodsTable" + " WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productName);
            if (statement.executeUpdate() == 0) {
                //throw new IllegalArgumentException("No such product");
                System.out.println("No such product");
            } else {
                System.out.println("Success");
            }
        } catch (SQLException ex) {
            //throw new RuntimeException("Error with removing product", ex);
            System.out.println("Error with removing product");
        }
    }

    public static void clear(Connection connection) {
        String sql = "TRUNCATE TABLE " + "goodsTable";
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException ex) {
            //throw new RuntimeException("Error with clearing table", ex);
            System.out.println("Error with clearing table");
        }
    }

    public static void showAll(Connection connection) {
        try {
            String sql = "SELECT * FROM goodsTable";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            printFromResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getByName(Connection connection, String productName) {
        String sql = "SELECT * FROM " + "goodsTable" + " WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, productName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("price");
                }
                //throw new IllegalArgumentException("No such product name.");
                System.out.println("No such product name");
            }
        } catch (SQLException ex) {
            // throw new RuntimeException("Error get product by name", ex);
            System.out.println("Error get product by name");
        }
        return -1;
    }

    public static void updatePrice(Connection connection, String productName, int price) {
        if (price < 0) {
            //throw new IllegalArgumentException("Price can't be negative");
            System.out.println("Price can't be negative");
        }
        String sql = "UPDATE " + "goodsTable" + " SET price = ? WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, price);
            statement.setString(2, productName);
            if (statement.executeUpdate() == 0) {
                //throw new IllegalArgumentException("No such product");
                System.out.println("No such product");
            }
        } catch (SQLException ex) {
            //throw new RuntimeException("Error with updating price", ex);
            System.out.println("Error with updating price");
        }
    }

    public static void fillRandomData(Connection connection) {
        String sql = "INSERT INTO " + "goodsTable" + "(id, title, price) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (int i = 1; i <= 10; i++) {
                statement.setInt(1, i + generateRandomInt());
                statement.setString(2, "Товар" + i + generateRandomInt());
                statement.setInt(3, generateRandomInt());
                statement.execute();
            }
            // connection.commit();
        } catch (SQLException ex) {
            //throw new RuntimeException("Error with filling table", ex);
            System.out.println("Error with filling table");
        }
    }

    public static void filterByPrice(Connection connection, int priceFrom, int priceTo) {
        if (priceTo < priceFrom) {
            //throw new IllegalArgumentException("From lower then to");
            System.out.println("From lower then to");
        }
        if (priceFrom < 0) {
            //throw new IllegalArgumentException("Price can't be negative.");
            System.out.println("Price can't be negative.");
        }
        String sql = "SELECT * FROM " + "goodsTable" + " WHERE price >= ? AND price <= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, priceFrom);
            statement.setInt(2, priceTo);
            try (ResultSet resultSet = statement.executeQuery()) {
                printFromResultSet(resultSet);
                //throw new IllegalArgumentException("Empty.");

            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            System.out.println(throwables);
        }
    }

    public static void handle(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input command:");
        String line = scanner.nextLine();
        String[] textArray = line.split(" ");

        try {
            if (textArray[0].startsWith("/")) {
                String productName;
                int id;
                int price;
                int priceFrom;

                switch (textArray[0]) {
                    case "/price": {
                        productName = textArray[1];
                        getByName(connection, productName);
                        break;
                    }
                    case "/change_price": {
                        price = Integer.parseInt(textArray[2]);
                        updatePrice(connection, textArray[1], price);
                        break;
                    }
                    case "/add": {
                        productName = textArray[1];
                        price = Integer.parseInt(textArray[2]);
                        id = JDBCUtils.generateRandomInt();
                        add(connection, id, productName, price);
                        break;
                    }
                    case "/delete": {
                        productName = textArray[1];
                        delete(connection, productName);
                        break;
                    }
                    case "/random": {
                        fillRandomData(connection);
                        break;
                    }
                    case "/show_all": {
                        showAll(connection);
                        break;
                    }
                    case "/clear": {
                        System.out.println("Are you sure? Y|N");
                        line = scanner.nextLine();
                        if (line.equals("Y")) {
                            clear(connection);
                        }
                        System.out.println("Complete");
                        break;
                    }
                    case "/filter_by_price": {
                        priceFrom = Integer.parseInt(textArray[1]);
                        price = Integer.parseInt(textArray[2]);
                        filterByPrice(connection, priceFrom, price);
                        break;
                    } case"/exit":{
                        System.exit(0);
                        break;
                    }
                    default: {
                        break;
                    }
                }
            } else {
                System.out.println("info: Not command");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("info: Error count arguments");
            handle(connection);
        }
    }

    public static int generateRandomInt() {
        return (int) ((Math.random() * 1234) / 5);
    }

    private static void printFromResultSet(ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            System.out.println("No records");
        } else {
            int id;
            int prodID;
            int price;
            String title;

            // System.out.println("id: \t prod_id: \t title: \t\t\t price:");
            do {
                id = resultSet.getInt("id");
                prodID = resultSet.getInt("prod_id");
                title = resultSet.getString("title");
                price = resultSet.getInt("price");
                System.out.println(String.format("%10d %10d %20s %10d", id, prodID, title, price));
            } while (resultSet.next());
        }
    }
}
