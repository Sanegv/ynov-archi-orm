import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SqlOrm {
    private Connection connection;

    public SqlOrm(String url, String user, String password) throws Exception{
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "toto", "bob");
        } catch (Exception e) {
            System.out.println("An error was raised during connection:");
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> requestStrings(String table, String column, int ID) throws Exception{       
        String req = "SELECT * from " + table + " WHERE ID=?";
        ArrayList<String> result = new ArrayList<String>();

        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.setString(1, Integer.toString(ID));

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String str = rs.getString(column);
                    result.add(str);
                }
            }
        }

        return result;
    }
}
