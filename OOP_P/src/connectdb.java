import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class connectdb {
    public Connection conn(String dbname, String user, String password ){
        Connection conn= null;
        Statement state = null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname, user, password);
            if(conn!=null){
                System.out.println("Connection Established");
            }
            else{
                System.out.println("Connection Failed");
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }
}
