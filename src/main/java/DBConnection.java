import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private final String DBURL="jdbc:postgresql://localhost:5432/Product_management_db";
    private final String DBUSER="product_manager_user";
    private final String DBPASS="123456";

    private Connection connection;

    public void connect() {
        try{
            connection = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
            System.out.println("you are connected to database product_management");

        }
        catch (Exception e){
            System.out.println("connection filled");
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
