package Conector;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class                                    AccesoBaseDeDatos {

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

    public void imprimirDatos(ResultSet resultado1) throws SQLException {


        ArrayList<String>columnas=obtenerNombresColumnas(resultado1);
        try {

            while (resultado1.next()) {
                String fila="";
                for (int i=1; i<=columnas.size();i++){
                    String temp = resultado1.getString(i);
                    fila=fila + temp + " ";
                }
                System.out.println(fila);

            }

            resultado1.close();

        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }
    }

    public ArrayList<String> obtenerNombresColumnas(ResultSet resultadoConsulta) throws SQLException {
        ArrayList<String>columnas=new ArrayList<String>();
        ResultSetMetaData resultadoMeta= (ResultSetMetaData) resultadoConsulta.getMetaData();
        for (int i=1;i<=resultadoMeta.getColumnCount();i++){
            columnas.add(resultadoMeta.getColumnName(i));
        }
        return columnas;
    }



    public void llamarPasajeroMasJoven() throws SQLException {
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado=db.obtenerResultado("select nombre,dni,max(fecha_nacimiento), idvuelo from persona join pasajero on dni=persona_dni " +
                "join vuelo_has_pasajero on pasajero_persona_dni=persona_dni join vuelo on idvuelo=vuelo_idvuelo " +
                "group by idvuelo,dni,nombre;");
            db.imprimirDatos(resultado);

    } //hay que cambiar un par de cosas

    public void cambiarvueloPasajero() throws SQLException {
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        int idPasajero =46679230;
        int idVuelo = 1;
        ResultSet resultado=db.obtenerResultado("call CambioPasaje("+idPasajero + "," + idVuelo + ");" );
        db.imprimirDatos(resultado);
         /*no devuelve un resultado, cambiarlo para que solo ejecute en la base de  datos,
         o que devuelva un parametro de salida*/
    }

    public void avionMasNuevo() throws SQLException {
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado=db.obtenerResultado("select num_serie from avion where fecha_fabricacion=(select max(fecha_fabricacion) from avion);" );
        db.imprimirDatos(resultado);

    } /*No eciste el atributo fecha de fabricacion pero como no se si al agregarlo va a cambiar
        los inserts ya hechos x ahora lo dejo asi y luego lo modificamos*/

    public void idiomasHablados() throws SQLException {
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado=db.obtenerResultado("select distinct(idioma),idvuelo from idioma join " +
                "idioma_has_tripulante on idioma_ididioma = ididioma join tripulante on tripulante_persona_dni=persona_dni " +
                "join tripulante_has_vuelo on persona_dni=tripulante_has_vuelo.tripulante_persona_dni join vuelo " +
                "on vuelo_idvuelo=idvuelo order by idvuelo,idioma;" );
        db.imprimirDatos(resultado);

    }

    public void pasajerosXVuelo() throws SQLException{
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado = db.obtenerResultado("call listarPasajerosXvuelo();");
        db.imprimirDatos(resultado);
    }

    public void cantidadMinima() throws SQLException{
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado = db.obtenerResultado("call listarPasajerosXvuelo();");
        db.imprimirDatos(resultado);
    }

    public void vuelosXdia() throws SQLException{
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado = db.obtenerResultado("call vuelosXTripulante(current_date());");
        db.imprimirDatos(resultado);
    }

    /*
    Colocar mysql-connector-java-8.0.21.jar en una carpeta llamada lib

    File -> Project Structure -> + -> JARs y directorios ->
    seleccionar mongo-java-driver -> tildar -> aplicar -> ok
    */
    public static void main(String[] args) throws SQLException {
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");


        // db.llamarPasajeroMasJoven();


        // cambiarvueloPasajero();
        db.avionMasNuevo();
        //db.idiomasHablados();
    }
}
