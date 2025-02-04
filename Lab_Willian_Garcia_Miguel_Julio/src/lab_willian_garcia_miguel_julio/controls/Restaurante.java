package lab_willian_garcia_miguel_julio.controls;
import java.io.File;
import java.io.FileWriter;
import lab_willian_garcia_miguel_julio.models.Mesa;
import lab_willian_garcia_miguel_julio.models.Mesero;
import lab_willian_garcia_miguel_julio.models.Orden;
public class Restaurante {
    private static Mesero empleados=null;    
    private static Mesa mesas=new Mesa();
    public static Orden ordenes=null;
    private final int  numerom;
    public Restaurante(int m) {
        mesas.crearmesas(m);
        this.numerom=m;
    }

    public int getNumerom() {
        return numerom;
    }

    public  Mesero getEmpleados() {
        return empleados;
    }

    public  Orden getOrden() {
        return ordenes;
    }
     
    public void addMesero(String name,long cc){
            if (empleados == null)  empleados=new Mesero(name,cc);
             else empleados.addMesero(name, cc);
    }
    public String getML(){
        String msg=mesas.busML();
        if(!msg.equals(""))return msg.substring(0, msg.length()-1);
        else return "";
    }

    public  Mesa getMesas() {
        return mesas;
    }
    
    public void printemple(){
        if (empleados != null) empleados.printMesero();
    }
    
    public String busCC(long cc){
        if (empleados != null) {
                if(empleados.busC(cc)){
                return "Acceso exitoso";
                }else{
                return "Acceso denegado\nUsuario no registrado";
                }
         } else {
                return "Acceso denegado\nNo hay meseros registrados";
            }
    }
    public Mesa busMes(int id){
            return this.getMesas().busMxid(id);
      }
    public Mesero busM(long cc){
            return empleados.busM(cc);
    }
    
}
