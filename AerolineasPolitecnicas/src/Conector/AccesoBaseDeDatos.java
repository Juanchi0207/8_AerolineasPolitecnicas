package Conector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AccesoBaseDeDatos {

    private Connection conexion;
    private String nombreBaseDeDatos;
    private List<String> nombreTabla;

    public AccesoBaseDeDatos(String nombreBaseDeDatos, List<String> nombreTabla) {
        this.nombreBaseDeDatos = nombreBaseDeDatos;
        this.nombreTabla = nombreTabla;
    }
    public AccesoBaseDeDatos(String nombreBaseDeDatos) {
        this.nombreBaseDeDatos = nombreBaseDeDatos;
    }

    public void conectar(String user, String password) {

        String url = "jdbc:mysql://localhost:3306/" + this.nombreBaseDeDatos;
        /* línea alternativa para los que usan windows:
        String url = "jdbc:mysql://localhost/" + this.nombreBaseDeDatos + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        */

        try {

            conexion = DriverManager.getConnection(url, user, password);

            if (conexion != null) {
                System.out.println("Se ha conectado exitosamente con la base de datos");
            } else {
                System.out.println("No se ha podido conectar con la base de datos");
            }

        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }

    }

    public void modificarTabla(String consulta) {
        /* INSERT, UPDATE, DELETE */
        try {
            Statement sentencia = this.conexion.createStatement();
            sentencia.executeUpdate(consulta);
            sentencia.close();

        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }
    }

    public ResultSet obtenerResultado(String consulta){

        ResultSet resultado = null;

        try {

            Statement sentencia = this.conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);


        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }

        return resultado;
    }

    public ResultSet seleccionarTodo(){
        String consulta = "SELECT * FROM " + this.nombreTabla;
        ResultSet resultado = this.obtenerResultado(consulta);
        return resultado;
    }

    public void imprimirDatos() {

        ResultSet resultado = this.seleccionarTodo();

        try {

            while (resultado.next()) {

                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                int edad = resultado.getInt("edad");

                System.out.println(id + " " + nombre + " " + apellido + " " + edad);
            }

            resultado.close();

        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }
    }

    public ResultSet mostrarColumnas(){
        String consulta = "SHOW COLUMNS FROM " + this.nombreTabla;
        ResultSet resultado = this.obtenerResultado(consulta);
        return resultado;
    }

    public ArrayList<String> colocarCamposEnLista() {

        ArrayList<String> nombresDeCampos = new ArrayList<>();
        ResultSet resultado = this.mostrarColumnas();

        try {

            while (resultado.next()) {
                String nombreDeColumna = resultado.getString("field");
                nombresDeCampos.add(nombreDeColumna);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return nombresDeCampos;
    }


    /*
    Colocar mysql-connector-java-8.0.21.jar en una carpeta llamada lib

    File -> Project Structure -> + -> JARs y directorios ->
    seleccionar mongo-java-driver -> tildar -> aplicar -> ok
    */
    public static void main(String[] args){
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        db.obtenerResultado("call listarPasajerosXvuelo();");

    }
}
