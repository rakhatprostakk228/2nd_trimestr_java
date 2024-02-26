import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            String url = "jdbc:postgresql://localhost:5432/User";
            String user = "postgres";
            String password = "NuOchenHard";
            Connection connection = DriverManager.getConnection(url, user, password);
            Scanner scanner = new Scanner(System.in);

            Map<Integer, GameAction> actions = new HashMap<>();
            actions.put(1, new AddGameAction());
            actions.put(2, new ViewAllGamesAction());
            actions.put(3, new UpdateGameAction());
            actions.put(4, new DeleteGameAction());

            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Add video game");
                System.out.println("2. View all video games");
                System.out.println("3. Update video game");
                System.out.println("4. Delete video game");
                System.out.println("5. Exit");
                System.out.print("Choose the number: ");

                int option = scanner.nextInt();
                scanner.nextLine();

                if (option == 5) {
                    System.out.println("Thanks for using our system.");
                    break;
                }

                GameAction action = actions.get(option);
                if (action != null) {
                    action.execute(connection, scanner);
                } else {
                    System.out.println("Invalid option. Please choose again.");
                }
            }
            connection.close();

        } catch (SQLException e) {
            System.err.println("Error accessing database!");
            e.printStackTrace();
        }
    }
}

class AddGameAction implements GameAction {
    @Override
    public void execute(Connection connection, Scanner scanner) throws SQLException {
        VideoGameManager videoGameManager = new VideoGameManager(connection);
        videoGameManager.addVideoGame(connection, scanner);
    }
}

class ViewAllGamesAction implements GameAction {
    @Override
    public void execute(Connection connection, Scanner scanner) throws SQLException {
        VideoGameManager videoGameManager = new VideoGameManager(connection);
        videoGameManager.viewAllVideoGames(connection);
    }
}

class UpdateGameAction implements GameAction {
    @Override
    public void execute(Connection connection, Scanner scanner) throws SQLException {
        VideoGameManager videoGameManager = new VideoGameManager(connection);
        videoGameManager.updateVideoGame(connection, scanner);
    }
}

class DeleteGameAction implements GameAction {
    @Override
    public void execute(Connection connection, Scanner scanner) throws SQLException {
        VideoGameManager videoGameManager = new VideoGameManager(connection);
        videoGameManager.deleteVideoGame(connection, scanner);
    }
}