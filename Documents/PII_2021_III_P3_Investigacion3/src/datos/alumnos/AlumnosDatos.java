package datos.alumnos;
//es el acceso a una base de datos
import datos.conexion.Conexion;
import recursos.clases.Alumnos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnosDatos {
    //se encargar de hacer las operaciones CRUD
    //Create-Read-Update-Delete
    //metodos del tipo static
    public static String InsertarAlumnos(Alumnos pAlumno)throws SQLException{
        try{
            Connection  cn = Conexion.obtenerConexion();
            //primer comando que se utiliza en el motor de base de datos
            //SQL: lenguaje de consulta estructurado
            String sql = "INSERT INTO Alumnos VALUES(?,?,?,?)";
            //preparamos una sentencia
            PreparedStatement ps = cn.prepareStatement(sql);
            //esta sentencia esperara 4 parametros//tenemos que setear los valores de esos parametros
            ps.setLong(  1, pAlumno.getNumeroCuenta());
            ps.setLong(  2, pAlumno.getDNI());
            ps.setDate(  3, new java.sql.Date(pAlumno.getFechaNacimiento().getTime()));
            ps.setString(4, pAlumno.getNombre());
            //asi puedo ejecutar la sentencia
            ps.execute();
            //cerramos el Statement
            ps.close();
            //simpre que habramos la conexion ,la tenemos que cerrar
            cn.close();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        //que funciono ese null
        return null;
    }
    public static List<Alumnos> LeerAlumnos()throws SQLException{
        List<Alumnos>listaAlumnos = new ArrayList<>();
        try {
            Connection cn = Conexion.obtenerConexion();
            Statement st = cn.createStatement();
            String sql = "SELECT NumeroCuenta,DNI,Nombre,FechaNacimiento FROM Alumnos ";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                Alumnos alumno = new Alumnos();
                alumno.setNumeroCuenta(rs.getLong(1));
                alumno.setDNI(rs.getLong(2));
                alumno.setNombre(rs.getString(3));
                alumno.setFechaNacimiento(rs.getDate(4));
                listaAlumnos.add(alumno);
            }
            //cerramos
            rs.close();
            //cerramos la conexion
            cn.close();
            st.close();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return listaAlumnos;
    }
    public static String ActualizarAlumnos(Alumnos pAlumnos)throws SQLException{
        try{
            //una llave primaria nunca se va actualizar
            //la llave primaria sirve para poder filtar
            Connection cn = Conexion.obtenerConexion();
            String sql = "UPDATE Alumnos SET Nombre = ?, DNI = ?, FechaNacimiento = ? WHERE NumeroCuenta =?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,pAlumnos.getNombre());
            ps.setLong(2,pAlumnos.getDNI());
            ps.setDate(3,new java.sql.Date(pAlumnos.getFechaNacimiento().getTime()));
            ps.setLong(4,pAlumnos.getNumeroCuenta());
            ps.execute();
            ps.close();
            //cerrar siempre la conexion
            cn.close();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return null;
    }
    public static String EliminarAlumnos(Alumnos pAlumno)throws SQLException{
        try{
            Connection cn = Conexion.obtenerConexion();
            String sql = "DELETE FROM Alumnos WHERE NumeroCuenta = ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setLong(1,pAlumno.getNumeroCuenta());
            ps.execute();
            ps.close();
            cn.close();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
        return null;
    }
    //si solo quisiera un alumno o varios pero no todos
    public static List<Alumnos>     BuscarAlumnos(Alumnos pAlumno) throws SQLException{
        List<Alumnos> listaAlumnos = new ArrayList<>();
        try{
            Connection cn = Conexion.obtenerConexion();
            String sql = "SELECT NumeroCuenta, DNI, Nombre, FechaNacimiento FROM Alumnos WHERE UPPER(Nombre) LIKE ?";
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1,"%"+pAlumno.getNombre().toUpperCase()+"%");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                do{
                    Alumnos alumno = new Alumnos();
                    alumno.setNumeroCuenta(rs.getLong(1));
                    alumno.setDNI(rs.getLong(2));
                    alumno.setNombre(rs.getString(3));
                    alumno.setFechaNacimiento(rs.getDate(4));
                    listaAlumnos.add(alumno);
                }while (rs.next());
            }
            else{
                throw  new SQLException("Error no se encontro coincidencia");
            }
            cn.close();
            rs.close();
            ps.close();
        }catch (SQLException e){
            throw new SQLException(e.getMessage());
        }
         return listaAlumnos;
    }
}
//la capa de negocio utiliza esta capa de datos
//arquitectura de software que se llama capas (N capas)
