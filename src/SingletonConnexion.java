import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnexion {
    private Connection connection;

    public Connection connect() {
        String BDD = "produit_database";
        String url = "jdbc:mysql://localhost:3306/" + BDD;
        String user = "root";
        String passwd = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error connecting to the database");
            System.exit(0);
        }

        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}


