package datos.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static final String USUARIO = "sa";
    private static final String CLAVE   = "Ujcv.2021";

    public static Connection obtenerConexion(){
        try{
            String URL = "jdbc:sqlserver://192.168.1.3:1433;dataBaseName=Prueba;";
            Connection cn = DriverManager.getConnection(URL,USUARIO,CLAVE);
            return cn;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
