import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "alumno", "alumnoipm");
            System.out.println(con);
        } catch (Exception e){

        }


    }
}

