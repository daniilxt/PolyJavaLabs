package project.database.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    String dbURL; /*"jdbc:mariadb://localhost:3306/test";*/
    String user; /*"root";*/
    String password;/*= "root";*/
    Connection connection;
    String tableName;

    public DataBase(String dbURL, String user, String password, String tableName) throws SQLException {
        this.dbURL = dbURL;
        this.user = user;
        this.password = password;
        this.tableName = tableName;
        this.connection = DriverManager.getConnection(dbURL, user, password);
        if (connection.isValid(1)) {
            System.out.println("Connection successful");
        } else {
            System.out.println("Bad connection");
        }
    }

    public void setNewConnection() throws SQLException {
        String dbURL = "jdbc:mariadb://localhost:3306/test";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(dbURL, user, password);

        if (connection.isValid(1)) {
            System.out.println("Connection successful");
        }
    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS " + tableName + "(" +
              "id INT NOT NULL," +
              "prod_id INT NOT NULL UNIQUE AUTO_INCREMENT," +
              "title VARCHAR(50) NOT NULL UNIQUE ," +
              "price INT NOT NULL," +
              "PRIMARY KEY(prod_id));");

            this.tableName = tableName;
        } catch (SQLException ex) {
            throw new RuntimeException("Can't create table", ex);
        }
    }

    public void add(Product product) {
        String sql = "INSERT INTO " + tableName + " (id, title, price) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setInt(3, product.getPrice());
            statement.execute();
            System.out.println("Success");
        } catch (SQLIntegrityConstraintViolationException ex) {
            throw new IllegalArgumentException("Product already exists");
            //System.out.println("Product already exists");
        } catch (SQLException ex) {
            // throw new RuntimeException("Error with adding new product", ex);
            System.out.println("Error with adding new product");
        }
    }

    public void showAll() {
        try {
            String sql = "SELECT * FROM " + tableName;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Product> products = getFromResultSet(resultSet);
            products.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String product) {
        String sql = "DELETE FROM " + tableName + " WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product);
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

    public void clear() {
        String sql = "TRUNCATE TABLE " + tableName;
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException ex) {
            //throw new RuntimeException("Error with clearing table", ex);
            System.out.println("Error with clearing table");
        }
    }


    public int getByName(String product) {
        String sql = "SELECT * FROM " + tableName + " WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product);
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

    public void updatePrice(Product product) {
        if (product.getPrice() < 0) {
            System.out.println("Price should be > 0");
        }
        String sql = "UPDATE " + tableName + " SET price = ? WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getPrice());
            statement.setString(2, product.getName());
            if (statement.executeUpdate() == 0) {
                System.out.println("No such product");
            }
        } catch (SQLException ex) {
            System.out.println("Error with updating price");
        }
    }

    public List<Product> filterByPrice(final int priceFrom, final int priceTo) {
        if (priceTo < priceFrom) {
            //throw new IllegalArgumentException("From lower then to");
            System.out.println("From lower then to");
        }
        if (priceFrom < 0) {
            //throw new IllegalArgumentException("Price can't be negative.");
            System.out.println("Price can't be negative.");
        }
        String sql = "SELECT * FROM " + tableName + " WHERE price >= ? AND price <= ?";
        List<Product> products = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, priceFrom);
            statement.setInt(2, priceTo);
            try (ResultSet resultSet = statement.executeQuery()) {
                products = getFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            System.out.println(throwables);
        }
        return products;
    }

    public static int generateRandomInt() {
        return (int) ((Math.random() * 1234) / 5);
    }

    public void fillRandomData() {
        String sql = "INSERT INTO " + tableName + "(id, title, price) VALUES (?, ?, ?)";
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

    private List<Product> getFromResultSet(ResultSet resultSet) throws SQLException {
        List<Product> records = new ArrayList<>();
        if (!resultSet.next()) {
            System.out.println("No records");
        } else {
            do {
                Product product = new Product(resultSet.getInt("id"), resultSet.getInt("prod_id"),
                  resultSet.getString("title"), resultSet.getInt("price"));
                records.add(product);
            } while (resultSet.next());
        }
        return records;
    }

    public List<Product> getTableList() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM " + tableName;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            list = getFromResultSet(resultSet);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<Product> getFilteredList(int priceFrom, int priceTo) {
        if (priceTo < priceFrom) {
            System.out.println("From lower then to");
        }
        if (priceFrom < 0) {
            System.out.println("Price can't be negative.");
        }
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " WHERE price >= ? AND price <= ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, priceFrom);
            statement.setInt(2, priceTo);
            ResultSet resultSet = statement.executeQuery(sql);
            list = getFromResultSet(resultSet);
        } catch (SQLException throwables) {
            System.out.println(throwables);
        }
        return list;
    }
}
