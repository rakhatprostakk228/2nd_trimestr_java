import java.sql.*;
import java.util.Scanner;

public class GameShop {

    public static void main(String[] args) {

            try {
                while (true) {
                    String url = "jdbc:postgresql://localhost:5432/User";
                    String user = "postgres";
                    String password = "NuOchenHard";
                    Connection connection = DriverManager.getConnection(url, user, password);
                    Scanner scanner = new Scanner(System.in);
                    VideoGameManager videoGameManager = new VideoGameManager(connection);


                    System.out.println("Choose an option:");
                    System.out.println("1. Add video game");
                    System.out.println("2. View all video games");
                    System.out.println("3. Update video game");
                    System.out.println("4. Delete video game");
                    System.out.println("5. Exit");
                    System.out.print("Choose the number: ");

                    int option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            videoGameManager.addVideoGame(connection, scanner);
                            break;
                        case 2:
                            videoGameManager.viewAllVideoGames(connection);
                            break;
                        case 3:
                            videoGameManager.updateVideoGame(connection, scanner);
                            break;
                        case 4:
                            videoGameManager.deleteVideoGame(connection, scanner);
                            break;
                        case 5:
                            System.out.println("Thanks for using our system.");
                            return;
                        default:
                            System.out.println("Invalid option. Please choose again.");
                    }

                }
            } catch (SQLException e) {
                System.err.println("Error accessing database!");
                e.printStackTrace();
            }
        }
    }
