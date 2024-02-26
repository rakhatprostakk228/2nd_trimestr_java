import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

interface GameAction {
    void execute(Connection connection, Scanner scanner) throws SQLException;
}