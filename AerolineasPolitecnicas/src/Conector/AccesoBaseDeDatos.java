package Conector;

import Clases.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
                //System.out.println("Se ha conectado exitosamente con la base de datos");
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

    public ResultSet obtenerResultado(String consulta) {
        ResultSet resultado = null;
        try {
            Statement sentencia = this.conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }
        return resultado;
    }

    public ResultSet seleccionarTodo() {
        String consulta = "SELECT * FROM " + this.nombreTabla;
        ResultSet resultado = this.obtenerResultado(consulta);
        return resultado;
    }

    public void imprimirDatos(ResultSet resultado1) throws SQLException {
        ArrayList<String> columnas = obtenerNombresColumnas(resultado1);
        String cols = "";
        for (int i = 0; i < columnas.size(); i++) {
            cols = cols + columnas.get(i) + " ";
        }
        System.out.println(cols);
        try {

            while (resultado1.next()) {
                String fila = "";
                for (int i = 1; i <= columnas.size(); i++) {
                    String temp = resultado1.getString(i);
                    fila = fila + temp + " ";
                }
                System.out.println(fila);

            }

            resultado1.close();

        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }
    }


    public ArrayList<String> obtenerNombresColumnas(ResultSet resultadoConsulta) throws SQLException {
        ArrayList<String> columnas = new ArrayList<String>();
        ResultSetMetaData resultadoMeta = (ResultSetMetaData) resultadoConsulta.getMetaData();
        for (int i = 1; i <= resultadoMeta.getColumnCount(); i++) {
            columnas.add(resultadoMeta.getColumnName(i));
        }
        return columnas;
    }


    public void llamarPasajeroMasJoven() throws SQLException {
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado = db.obtenerResultado("select idvuelo,pasajeroMasJoven(idvuelo) from vuelo where pasajeroMasJoven(idvuelo)>0;");
        db.imprimirDatos(resultado);

    }

    public void vuelosXtripNoAutorizados() throws SQLException {
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        db.obtenerResultado("call vuelosXtripNoAutorizado(@vuelos)");
        ResultSet resultado= db.obtenerResultado("select @vuelos as vuelos_con_personas_no_autorizadas;");
        db.imprimirDatos(resultado);
    }

    // EJ e

    public void reglaRota() throws SQLException {
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        db.obtenerResultado("call reglaRota(@tripulantes)");
        ResultSet resultado= db.obtenerResultado("select @tripulantes as Tripulantes_que_no_cumplen;");
        db.imprimirDatos(resultado);
    }


    // EJ f
    public void cambiarvueloPasajero(int dni, int idVuelo) throws SQLException {
        Vuelo vuelo = new Vuelo();
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado = db.obtenerResultado("call CambioPasaje(" + dni + "," + idVuelo + ");");
    }

    public void avionMasNuevo() throws SQLException {
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado = db.obtenerResultado("select num_serie from avion where fecha_fabricacion=(select max(fecha_fabricacion) from avion);");
        db.imprimirDatos(resultado);

    }

    public void idiomasHablados() throws SQLException {
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado = db.obtenerResultado("select distinct(idioma),idvuelo from idioma join " +
                "idioma_has_tripulante on idioma_ididioma = ididioma join tripulante on tripulante_persona_dni=persona_dni " +
                "join tripulante_has_vuelo on persona_dni=tripulante_has_vuelo.tripulante_persona_dni join vuelo " +
                "on vuelo_idvuelo=idvuelo order by idvuelo,idioma;");
        db.imprimirDatos(resultado);

    }



    // EJ c
    public void cantidadMinimaTripulantes() throws SQLException {
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado = db.obtenerResultado("select idvuelo as IDvuelo ,cant_trip_necesaria as TripulacionNecesaria ,count(*) as TripulacionActual from vuelo join avion on patente=avion_patente1 \n" +
                "join modelo on modelo.modelo=avion.modelo_modelo\n" +
                "join tripulante_has_vuelo on vuelo_idvuelo=idvuelo group by idvuelo\n" +
                "having count(*)<cant_trip_necesaria;");
        db.imprimirDatos(resultado);
    }
}


    /*
    Colocar mysql-connector-java-8.0.21.jar en una carpeta llamada lib

    File -> Project Structure -> + -> JARs y directorios ->
    seleccionar mongo-java-driver -> tildar -> aplicar -> ok
    */

