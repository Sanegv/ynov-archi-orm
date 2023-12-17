import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.lang.reflect.*;
import java.util.Hashtable;
import java.lang.Exception;
import com.mysql.cj.exceptions.ExceptionFactory;
import com.mysql.cj.x.protobuf.Mysqlx.Error;

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
        String req = "INSERT INTO " + table + " (";
        ArrayList<String> names = getFieldNames(object);
        Hashtable<String, Object> map = getFieldValues(object);

        for(int i = 0; i < names.size()-1; i++) req += names.get(i) + ", ";
        req += names.get(names.size()-1) + ") VALUES (";
        for(int i = 0; i < names.size()-1; i++) req += "?, ";
        req += "?)";

        try (PreparedStatement statement = connection.prepareStatement(req)) {
            for(int i = 0; i < names.size(); i++){
                statement.setString(i+1, map.get(names.get(i)).toString());
            }
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createTable(Object object) throws Exception{
        String table = object.getClass().toString().substring(6); //ignoring the "class " part
        Field[] fields = getFields(object);

        createTable(table, fields);
    }

    public void createTable(String table, Field[] fields) throws Exception{
        String req = "CREATE TABLE " + table + " (";
            for(Field field : fields){
                req += field + " " + getOrmType(field) + " NOT NULL , ";
            }
            req += "PRIMARY KEY (" + fields[0] + "))";
        
        /*try (PreparedStatement statement = connection.prepareStatement(req)) {
            System.out.println(statement);
            statement.executeUpdate();
        }*/
        System.out.println(req);
    }

    public String getOrmType(Field field) throws Exception{
        /*switch(field){
            case String.class:
                return "INT";
                break;
            default:
                return field.getType().toString();
        }*/
        return "TODO";
    }

    public void deleteElementByID(String table, int id) throws Exception{
        String req = "DELETE FROM " + table + " WHERE ID = ?"; 
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public void deleteTable(String table) throws Exception{
        String req = "DROP TABLE " + table;
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.executeUpdate();
        }
    }

    public void updateElem(String table, String column, int ID, Object newValue) throws Exception{
        String req = "UPDATE " + table + " SET " + column + " = ";
        boolean insertString  = false;
        switch(newValue){
            case Integer i:
                req += i.toString();
                break;
            case String s:
                req += "?";
                insertString = true;
                break;
            default:
                return;
        }
        req += " WHERE ID = ?"; 
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            if(insertString) {
                statement.setString(1, newValue.toString());
                statement.setInt(2, ID);
            } else {
                statement.setInt(1, ID);
            }
            statement.executeUpdate();
        }        
    }

    public void deleteColumn(String table, String column) throws Exception {
        String req = "ALTER TABLE " + table + " DROP " + column;
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.executeUpdate();
        }
    }

    public void createVarcharColumn(String table, String column) throws Exception{
        String req = "ALTER TABLE " + table + " ADD " + column + " VARCHAR(256) NOT NULL";
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.executeUpdate();
        }
    }

    public void createIntColumn(String table, String column) throws Exception{
        String req = "ALTER TABLE " + table + " ADD " + column + " INT NOT NULL";
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.executeUpdate();
        }
    }

    public void changeVarcharColumnName(String table, String column, String newName)  throws Exception{        
        String req = "ALTER TABLE " + table + " CHANGE " + column + " " + newName + " VARCHAR(256)";
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.executeUpdate();
        }
    }

    public void changeIntColumnName(String table, String column, String newName)  throws Exception{        
        String req = "ALTER TABLE " + table + " CHANGE " + column + " " + newName + " INT";
        try (PreparedStatement statement = connection.prepareStatement(req)) {
            statement.executeUpdate();
        }
    }
}
