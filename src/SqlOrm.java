import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.lang.reflect.*;
import java.util.Hashtable;

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

    public ArrayList<String> requestStrings(String table, String column, String field, String value) throws Exception{       
        String req = "SELECT * from " + table + " WHERE " + field + "=?";
        ArrayList<String> result = new ArrayList<String>();

        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.setString(1, value);
            System.out.println(statement);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    String str = rs.getString(column);
                    result.add(str);
                }
            }
        }

        return result;
    }

    public ArrayList<Integer> requestInts(String table, String column, String field, String value) throws Exception{       
        String req = "SELECT * from " + table + " WHERE " + field + "=?";
        ArrayList<Integer> result = new ArrayList<Integer>();

        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.setString(1, value);
            System.out.println(statement);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    int i = rs.getInt(column);
                    result.add(i);
                }
            }
        }

        return result;
    }

    public <T> ArrayList<T> request(String table, String column, String field, String value) throws Exception{       
        String req = "SELECT * from " + table + " WHERE " + field + "=?";
        ArrayList<T> result = new ArrayList<T>();

        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.setString(1, value);
            System.out.println(statement);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    T res = (T)rs.getObject(column);
                    result.add(res);
                }
            }
        }

        return result;
    }

    public Field[] getFields(Object object){        
        return object.getClass().getDeclaredFields();
    }

    public ArrayList<String> getFieldNames(Object object){
        Field[] fields = getFields(object);

        ArrayList<String> list = new ArrayList<String>();
        for(Field field : fields){
            list.add(field.getName());
        }

        return list;
    }

    public Hashtable<String, Object> getFieldValues(Object object) throws Exception{
        Hashtable<String, Object> map = new Hashtable<String, Object>();

        Field[] fields = getFields(object);
        for(Field field : fields){
            field.setAccessible(true);
            map.put(field.getName(), field.get(object));
        }
        return map;
    }

    public void insert(String table, Object object) throws Exception{
        String req = "INSERT INTO " + table + "(";
        ArrayList<String> fieldNames = getFieldNames(object);

    }
}
