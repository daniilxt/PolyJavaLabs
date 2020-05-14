package project.database.db;

import javafx.util.Pair;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


@FunctionalInterface
interface Command {
    void apply(Scanner line);
}

public class Handler {
    private final DataBase db;
    private Scanner in;
    private PrintStream out;

    public Handler(DataBase db, InputStream in) {
        this.db = db;
        this.in = new Scanner(in);
    }

    private final Map<String, Command> commands = Map.of(
      "/add", this::add,
      "/delete", this::delete,
      "/show_all", this::showAll,
      "/price", this::price,
      "/change_price", this::changePrice,
      "/filter_by_price", this::filterByPrice,
      "/random", this::random,
      "/clear", this::clear
    );

    private void random(Scanner scanner) {
        db.fillRandomData();
    }

    private void clear(Scanner scanner) {
        db.clear();
    }

    private void filterByPrice(Scanner scanner) {
        int filterFrom = scanner.nextInt();
        int filterTo = scanner.nextInt();
        List<Product> products = db.filterByPrice(filterFrom, filterTo);
        products.forEach(System.out::println);
    }

    private void changePrice(Scanner scanner) {
        Pair<String, Integer> pair = getLongNameAndPrice(scanner);
        db.updatePrice(new Product(pair.getKey(), pair.getValue()));
    }

    public void run() {
        while (in.hasNextLine()) {
            try {
                final String command = in.next();
                if (command.equals("/exit")) {
                    break;
                }
                execute(command + in.nextLine());
            } catch (RuntimeException e) {
//                out.println(e.getMessage());
                System.out.println("Invalid command?");
            }
        }
    }

    public void execute(String commandLine) {
        final Scanner line = new Scanner(commandLine);
        System.out.println("Command is:" + commandLine);

        if (line.hasNext()) {
            commands.getOrDefault(line.next(), (v) -> out.println("Invalid command")).apply(line);
        }
    }

    private void add(Scanner scanner) {
        Pair<String, Integer> pair = getLongNameAndPrice(scanner);
        db.add(new Product(generateRandomInt(), pair.getKey(), pair.getValue()));
    }

    private void delete(Scanner scanner) {
        String productName = scanner.nextLine().trim();
        System.out.println("I tut");
        System.out.println("DELETE IS: " + productName);
        db.delete(productName);
    }

    private void showAll(Scanner scanner) {
        db.showAll();
    }

    private void price(Scanner scanner) {
        String productName = scanner.nextLine().trim();
        System.out.println("price of " + productName + " : " + db.getByName(productName));
    }

    private Pair<String, Integer> getLongNameAndPrice(Scanner scanner) {
        String productName = scanner.nextLine().trim();
        int price = Integer.parseInt(productName.substring(productName.lastIndexOf(" ")).trim());
        productName = productName.substring(0, productName.lastIndexOf(" ")).trim();
        System.out.println("Name: " + productName + "Price: " + price);
        return new Pair<>(productName, price);
    }

    public static int generateRandomInt() {
        return (int) ((Math.random() * 1234) / 5);
    }

}