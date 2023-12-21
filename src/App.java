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

        SqlOrm orm = new SqlOrm("jdbc:mysql://localhost:3306/testdb", "toto", "bob");
        ArrayList<String> list = orm.request("User", "login","email", "toto@gmail.com");
        for(int i = 0; i < list.size(); i++){
            System.out.println("login: " + list.get(i));
        }

        User u = new User(4, "test", "toto@gmail.com", "admin");
        orm.insert("user", u);
        
        list = orm.request("User", "login","email", "toto@gmail.com");
        for(int i = 0; i < list.size(); i++){
            System.out.println("login: " + list.get(i));
        }

        orm.deleteElementByID("user", 4);list = orm.request("User", "login","email", "toto@gmail.com");
        for(int i = 0; i < list.size(); i++){
            System.out.println("login: " + list.get(i));
        }
    }
}
