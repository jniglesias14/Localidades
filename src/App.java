import java.sql.Connection;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner teclado =new Scanner(System.in);
        Connection con;
        int opcion;
        ListaLocalidades lista=new ListaLocalidades();
        boolean lectura=true;
        if (GestorConexion.crearConexion("geografia", "geografia", "A12345a")) {
            con = GestorConexion.getConexion();
            while(lectura){
                System.out.println("1) Buscar localidad \n2) Población de provincia \n3) Localidad aleatoria \n4) Localidades con más y menos habitantes \n5) Salir ");
                opcion=Integer.parseInt(teclado.nextLine());
                if(opcion==1){
                    Localidad.buscarLocalidad(con);
                }else if(opcion==2){
                    int n=Provincia.poblacionProvincia(con);
                    if(n>0){
                        System.out.println(n);
                    }
                    else if(n==0){
                        System.out.println("provincia no encontrada");
                    }else {
                        System.out.println("error");
                    }
                }else if(opcion==3){
                    lista.aleatorio(con);
                }else if(opcion==4){
                    System.out.println(Localidad.masYmenos(con));
                }else if(opcion==5){
                    lectura=false;
                }else{
                    System.out.println("numero erroneo");
                }
            }
        }else{
            System.out.println(GestorConexion.getError());
        }
    }
}
