package recursos.clases;
//representamos la tabla que hemos creado en la bd
import java.util.Date;

public class Alumnos {
    private long NumeroCuenta;
    private long DNI;
    private String Nombre;
    private Date FechaNacimiento;

    public Alumnos(long pNumeroCuenta,long pDNI,String pNombre,Date pFechaNacimiento){
        this.NumeroCuenta =pNumeroCuenta;
        this.DNI = pDNI;
        this.Nombre = pNombre;
        this.FechaNacimiento = pFechaNacimiento;
    }

    public Alumnos() {

    }

    public long getNumeroCuenta() {
        return NumeroCuenta;
    }

    public void setNumeroCuenta(long numeroCuenta) {
        this.NumeroCuenta = numeroCuenta;
    }

    public long getDNI() {
        return DNI;
    }

    public void setDNI(long DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.FechaNacimiento = fechaNacimiento;
    }
}
