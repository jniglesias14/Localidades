import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Localidad {
    protected int id;
    protected String nombre;
    protected int poblacion;
    protected int n_provincia;

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public int getN_provincia() {
        return n_provincia;
    }

    public int getPoblacion() {
        return poblacion;
    }

    @Override
    public String toString() {
        return id+" "+nombre+" "+poblacion+" "+n_provincia;
    }

    public static void buscarLocalidad(Connection con){
        Scanner teclado=new Scanner(System.in);
        String nombre,sql;
        String nombreCom,nombrePro,nombreLoc,poblacionLoc;
        System.out.println("dime el nombre de la localidad");
        nombre=teclado.nextLine();
        if(comprobarLocalidad(con,nombre)==1){
            try {
                sql = "select c.nombre,p.nombre,l.nombre,l.poblacion from localidades l join provincias p using(n_provincia) join comunidades c using(id_comunidad) where l.nombre=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1,nombre);
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    nombreCom= rs.getString("c.nombre");
                    nombrePro= rs.getString("p.nombre");
                    nombreLoc= rs.getString("l.nombre");
                    poblacionLoc= rs.getString("l.poblacion");
                    System.out.println(nombreCom+" "+nombrePro+" "+nombreLoc+" "+poblacionLoc);

                }
            }catch (SQLException sqle){
                sqle.printStackTrace();
            }
        }else if(Localidad.comprobarLocalidad(con,nombre)==0){
            System.out.println("esa localidad no existe");
        }else{
            System.out.println("error");
        }


    }
    public static int comprobarLocalidad(Connection con,String n){
        String sql;
        try{
            sql="select id_localidad from localidades where nombre=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,n);
            ResultSet rs= ps.executeQuery();
            if(rs.next()) {
                return 1;
            }else{
                return 0;
            }
        }catch (SQLException sqle){
            return -1;

        }


    }

    public static String masYmenos(Connection con){
        Scanner teclado=new Scanner(System.in);
        String sql1,sql2;
        int n,cont1=0,cont2=0;
        try {
            System.out.println("dime un numero de habitantes");
            n=Integer.parseInt(teclado.nextLine());
            sql1="select nombre from localidades where poblacion<?";
            PreparedStatement ps=con.prepareStatement(sql1);
            ps.setInt(1,n);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                cont1++;
            }

            sql2="select nombre from localidades where poblacion>?";
            PreparedStatement ps1=con.prepareStatement(sql2);
            ps1.setInt(1,n);
            ResultSet rs1=ps1.executeQuery();
            while(rs1.next()){
                cont2++;
            }
            return "Hay "+cont1+" localidades con menos habitantes de "+n+", y "+cont2+" localidades con mas habitantes de "+n;

        }catch (SQLException sqle){
            sqle.printStackTrace();
        }catch (NumberFormatException nfe){
            return "has introducido letras";
        }
        return null;
    }
}
