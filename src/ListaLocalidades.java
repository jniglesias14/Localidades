import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaLocalidades {
    protected ArrayList<Localidad> lista=new ArrayList<>();

    public void aleatorio(Connection con){
        int c=1;
        añadir(con);
        int n=(int)(Math.random()* lista.size());
        for(Localidad l:lista){
            if(c==n){
                System.out.println(l.toString());
            }
            c++;
        }
    }

    public void añadir(Connection con){
        String sql;
        try{
            sql="select * from localidades";
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Localidad l=new Localidad();
                l.id=rs.getInt("id_localidad");
                l.nombre=rs.getString("nombre");
                l.poblacion=rs.getInt("poblacion");
                l.n_provincia=rs.getInt("n_provincia");
                lista.add(l);
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }



}
