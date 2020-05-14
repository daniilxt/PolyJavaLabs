package project.database.db;

public class Product {
    private int id;
    private String name;
    private int price;
    private int prod_id;


    public Product(int id, String productName, int price) {
        if (price < 0) {
            System.out.println("Price should be > 0");
        }

        this.id = id;
        this.name = productName;
        this.price = price;
    }

    public Product(String productName, int price) {
        this(DataBase.generateRandomInt(), productName, price);
    }

    public Product(int id, int prod_id, String title, int price) {
        this(id, title, price);
        this.prod_id = prod_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String productName) {
        this.name = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%10d %10d %20s %10d", id, prod_id, name, price);
    }

    public int getProd_id() {
        return prod_id;
    }

    public void setProd_id(int prod_id) {
        this.prod_id = prod_id;
    }
}
