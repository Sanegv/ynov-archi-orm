import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

public class App {
    public static void main(String[] args) throws Exception {
        /*System.out.println("Hello, World!");
        String mail = "toto@gmail.com";        
        String name = "toto";
        int id = 1;
        Class.forName("com.mysql.jdbc.Driver");
        Connection connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "toto", "bob");
        try (connexion) {
            String req = "SELECT * from User WHERE id=?;";
            try (PreparedStatement statement = connexion.prepareStatement(req)) {
                statement.setString(1, Integer.toString(id));
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
        }*/

        SqlOrm orm = new SqlOrm("jdbc:mysql://localhost:3306/testdb", "toto", "bob");
        /*ArrayList<String> list = orm.request("User", "login","email", "toto@gmail.com");
        for(int i = 0; i < list.size(); i++){
            System.out.println("login: " + list.get(i));
        }*/
        
        User user = new User(4, "test", "test@gmail.com", "test");
        Hashtable<String, Object> map = orm.getFieldValues(user);
        System.out.println(map.get("id"));
    }
}
