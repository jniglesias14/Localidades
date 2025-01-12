import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Provincia {

    public static int poblacionProvincia(Connection con){
        Scanner teclado=new Scanner(System.in);
        String sql,nombre;
        int n;
        System.out.println("dime el nombre de la provincia");
        nombre=teclado.nextLine();
        try{
            sql="select sum(poblacion) as poblacion from localidades l join provincias p using(n_provincia) where p.nombre=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,nombre);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                n=rs.getInt("poblacion");
                return n;
            }else{
                return 0;
            }

        }catch (SQLException sqle){
            sqle.printStackTrace();
            return -1;
        }

    }
}
