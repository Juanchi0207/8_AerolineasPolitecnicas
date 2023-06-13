package Clases;
import java.util.Date;

public class Persona {
    private String nombre;
    private String apellido;
    private Date nacimiento;
    private int dni;

    public Persona() {
        this.nombre="Dummy";
        this.apellido="Test";
        this.nacimiento = null;
        this.dni = 1;
    }

    public Persona(String nombre, String apellido, Date nacimiento, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nacimiento = nacimiento;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", nacimiento=" + nacimiento +
                ", dni=" + dni +
                '}';
    }


}
