public class User {
    private int id;
    private String login;
    private String email;
    private String password;
    
    public int getId(){
        return id;
    }

    public String getLogin(){
        return login;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public User(String login, String email, String password){
        setEmail(email);
        setLogin(login);
        setPassword(password);
        this.id = 1;
    }

    public User(int ID, String login, String email, String password){
        setEmail(email);
        setLogin(login);
        setPassword(password);
        this.id = ID;
    }

    public String toString(){
        String str = "id: ";
        str += Integer.toString(id);
        str += ",\n login: ";
        str += login;
        str += ",\n email: ";
        str += email;
        str += ",\n password: ";
        str += password;

        return str;
    }
}
