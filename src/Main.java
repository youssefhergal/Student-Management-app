import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Main() {
        Ui ui = new Ui(new Controller());
    }

    public static void main(String[] args) {
        new Main();
    }
}


