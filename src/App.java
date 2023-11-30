import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        String mail = "toto@gmail.com";        
        String name = "toto";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "toto", "bob");
        try (connexion) {
            String req = "SELECT * from User"; // WHERE email=?;";
            try (PreparedStatement statement = connexion.prepareStatement(req)) {
                //statement.setString(1, mail);
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        String firstname = rs.getString("login");
                        String email = rs.getString("email");
                        String password = rs.getString("password");
                        System.out.println("user.name = " + firstname);
                        System.out.println("user.email =  " + email);                        
                        System.out.println("user.password =  " + password);
                        System.out.println("-----------------");
                    }
                }
            }
        }
    }
}
