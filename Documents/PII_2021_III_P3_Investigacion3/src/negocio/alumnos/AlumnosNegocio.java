package negocio.alumnos;
//aqui colocamos los metodos que se van a conectar o van a ser el llamado a la capa de datos
//debemos de dividir las responsabilidades
//metodos que se van a ser el llamado a la capa de datos
//la capa de gui llamara esta capa de negocio
import datos.alumnos.AlumnosDatos;
import recursos.clases.Alumnos;
import java.util.ArrayList;
import java.util.List;

public class AlumnosNegocio {
    public List<Alumnos> leer(){
        List<Alumnos> listaAlumnos = new ArrayList<>();
        try{
            listaAlumnos = AlumnosDatos.LeerAlumnos();

        }catch(Exception e){
        }
        return listaAlumnos;
    }
    public String Insertar(Alumnos pAlumno) throws Exception{
        String respuesta ="Error";
        //hacer todas las validaciones posibles
        if (pAlumno.getNumeroCuenta()<=0){
            //asi detonamos una excepcion
            throw new Exception("Error: El Numero de cuenta no debe ser menor a 0");
        }
        if (pAlumno.getNombre().isEmpty()){
            throw new Exception("Error: El Nombre no debe estar vacio");
        }
        if (pAlumno.getDNI()<=0){
            throw new Exception("Error: El DNI no debe ser menor a 0");
        }
        if (pAlumno.getFechaNacimiento().toString().isEmpty()){
            throw new Exception("Error: Fecha Incorrecta");
        }
        if (pAlumno.getNumeroCuenta()<=0){
            //asi detonamos una excepcion
            throw new Exception("Error: El Numero de cuenta no debe ser menor a 0");
        }
        if (pAlumno.getNombre().isEmpty()){
            throw new Exception("Error: El Nombre no debe estar vacio");
        }
        if (pAlumno.getDNI()<=0){
            throw new Exception("Error: El DNI no debe ser menor a 0");
        }
        if (pAlumno.getFechaNacimiento().toString().isEmpty()){
            throw new Exception("Error: Fecha Incorrecta");
        }
        try{
            respuesta = AlumnosDatos.InsertarAlumnos(pAlumno);
            if (respuesta==null){
                respuesta = "Guardado Exitosamente";
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }//el finally se va a ejectuar siempre
        finally {
            return respuesta;
        }
    }
    public void   Actualizar(Alumnos pAlumno) throws Exception{
        //deben ir mas validaciones
        try{
            if (pAlumno.getNumeroCuenta()<=0){
                //asi detonamos una excepcion
                throw new Exception("Error: El Numero de cuenta no debe ser menor a 0");
            }
            AlumnosDatos.ActualizarAlumnos(pAlumno);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public void   Eliminar(Alumnos pAlumno) throws Exception{
        //deben ir validaciones
        try{
            if (pAlumno.getNumeroCuenta()<=0){
                //asi detonamos una excepcion
                throw new Exception("Error: El Numero de cuenta no debe ser menor a 0");
            }
            AlumnosDatos.EliminarAlumnos((pAlumno));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public List<Alumnos> Buscar(Alumnos pAlumno) throws Exception{
        List<Alumnos> listaAlumnos = new ArrayList<>();
        try{
            listaAlumnos = AlumnosDatos.BuscarAlumnos(pAlumno);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return listaAlumnos;
    }
}
