import java.sql.*;
import java.util.Scanner;

public class VideoGameManager {
    private Connection connection;

    public VideoGameManager(Connection connection) {
        this.connection = connection;
    }

    public void addVideoGame(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter free ID: ");
        int newId = scanner.nextInt();
        scanner.nextLine();

        PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        checkStatement.setInt(1, newId);
        ResultSet resultSet = checkStatement.executeQuery();
        if (resultSet.next()) {
            System.err.println("This id already exists.");
            return;
        }

        System.out.print("Enter game title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter game genre: ");
        String newGenre = scanner.nextLine();
        System.out.print("Enter release year: ");
        int newReleaseYear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter game developer: ");
        String newDeveloper = scanner.nextLine();
        System.out.print("Enter game price: ");
        int newPrice = scanner.nextInt();

        String query = "INSERT INTO users (id, title, genre, release_year, developer, price) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, newId);
        preparedStatement.setString(2, newTitle);
        preparedStatement.setString(3, newGenre);
        preparedStatement.setInt(4, newReleaseYear);
        preparedStatement.setString(5, newDeveloper);
        preparedStatement.setInt(6, newPrice);

        preparedStatement.executeUpdate();
        System.out.println("The game has been successfully added.");
    }

    public void viewAllVideoGames(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

        System.out.printf("%-5s %-25s %-20s %-20s %-25s %-10s%n", "ID", "Title", "Genre", "Release Year", "Developer", "Price");
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        while (resultSet.next()) {
            System.out.printf("%-5s %-25s %-20s %-20s %-25s %-10s%n",
                    resultSet.getInt("id"),
                    resultSet.getString("title"),
                    resultSet.getString("genre"),
                    resultSet.getInt("release_year"),
                    resultSet.getString("developer"),
                    resultSet.getBigDecimal("price"));
        }
    }

    public void updateVideoGame(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter game ID: ");
        int idToUpdate = scanner.nextInt();
        scanner.nextLine();

        PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        checkStatement.setInt(1, idToUpdate);
        ResultSet resultSet = checkStatement.executeQuery();
        if (!resultSet.next()) {
            System.err.println("No game found with that ID: " + idToUpdate);
            return;
        }

        System.out.print("Enter game title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter game genre: ");
        String newGenre = scanner.nextLine();
        System.out.print("Enter release year: ");
        int newReleaseYear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter game developer: ");
        String newDeveloper = scanner.nextLine();
        System.out.print("Enter game price: ");
        int newPrice = scanner.nextInt();

        String updateQuery = "UPDATE users SET title=?, genre=?, release_year=?, developer=?, price=? WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setString(1, newTitle);
        preparedStatement.setString(2, newGenre);
        preparedStatement.setInt(3, newReleaseYear);
        preparedStatement.setString(4, newDeveloper);
        preparedStatement.setInt(5, newPrice);
        preparedStatement.setInt(6, idToUpdate);

        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println("The game has been successfully updated.");
    }

    public void deleteVideoGame(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter the ID of the game to delete: ");
        int idToDelete = scanner.nextInt();
        scanner.nextLine();

        PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
        checkStatement.setInt(1, idToDelete);
        ResultSet resultSet = checkStatement.executeQuery();
        if (!resultSet.next()) {
            System.err.println("No game found with ID: " + idToDelete);
            return;
        }

        String deleteQuery = "DELETE FROM users WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
        preparedStatement.setInt(1, idToDelete);
        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println("The game has been successfully deleted.");
    }
}