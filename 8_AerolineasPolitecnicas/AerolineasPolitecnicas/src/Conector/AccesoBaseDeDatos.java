package Conector;

import Clases.*;

import java.sql.*;
import java.util.*;
import java.util.Date;


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

    public HashMap<Integer, HashMap<String,Object>>obtenerDatosPasajero(ResultSet resultado) throws SQLException {
        ArrayList<String>cols=obtenerNombresColumnas(resultado);
        HashMap<Integer,HashMap<String,Object>>lista=new HashMap<>();
        while (resultado.next()){
            HashMap<String,Object>aux1=new HashMap<>();
            for(int i=1;i<cols.size();i++){
                if (cols.get(i).equals("fecha_nacimiento")){
                    aux1.put(cols.get(i),resultado.getDate(cols.get(i)));
                }
                else {
                    aux1.put(cols.get(i), resultado.getString(cols.get(i)));
                }
            }
            lista.put(resultado.getInt(cols.get(0)),aux1);
        }
        return lista;
    }

    public HashMap<Integer, HashMap<String,Object>>obtenerDatosTripulantes(ResultSet resultado) throws SQLException {
        ArrayList<String>cols=obtenerNombresColumnas(resultado);
        HashMap<Integer,HashMap<String,Object>>lista=new HashMap<>();
        while (resultado.next()){
            HashMap<String,Object>aux1=new HashMap<>();
            for(int i=1;i<cols.size();i++){
                if (cols.get(i).equals("fecha_nacimiento")){
                    aux1.put(cols.get(i),resultado.getDate(cols.get(i)));
                }
                else {
                    aux1.put(cols.get(i), resultado.getString(cols.get(i)));
                }
            }
            lista.put(resultado.getInt(cols.get(0)),aux1);
        }
        return lista;
    }

    public ResultSet obtenerResultado(String consulta) {
        // metodo el cual realiza una consulta que se le pase por parametro y devuelve un resultSet
        // el cual es una variable que tiene todos los datos de la consulta "comprimidos" por ais decirlo
        ResultSet resultado = null;
        try {
            Statement sentencia = this.conexion.createStatement();
            resultado = sentencia.executeQuery(consulta);
        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }
        return resultado;
    }

    public void imprimirDatos(ResultSet resultado1) throws SQLException {
        // funcion automatizada para imprimir datos de un resultSet
        ArrayList<String> columnas = obtenerNombresColumnas(resultado1); //llamamos a un metodo
        // para obtener los nombres de las columnas de este resultSet
        String cols = "";
        for (int i = 0; i < columnas.size(); i++) {
            cols = cols + columnas.get(i) + " "; // imprimimos los nombres de las columnas
            // para mantener un ordren y que se sepa que dato es que
        }
        System.out.println(cols);
        try {

            while (resultado1.next()) {
                String fila = "";
                for (int i = 1; i <= columnas.size(); i++) {
                    String temp = resultado1.getString(i); // obtenemos los datos de una fila
                    // respetando la cantidad de columnas que existen y la imprimimos, para asi
                    // pasar a la siguiente fila
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
        // Creamos un array con los nombres de las columnas
        ResultSetMetaData resultadoMeta = (ResultSetMetaData) resultadoConsulta.getMetaData();
        // instanciamos el resultSet a resultSetMetaData, variable la cual tiene mas metodos que nos
        // sirven para los nombres y columnas
        for (int i = 1; i <= resultadoMeta.getColumnCount()/*cantidad de columnas */; i++) {
            // cabe aclarar que en este caso un for sirve, ya que la cantidad de columnas es una especifica
            // y se respeta el orden en que aparecen en la consulta, lo unico es que en este caso no se
            // arranca en 0, sino que se arranca en 1, ya que para las columnas, el 0 no era entre los valores
            // posibles que puede tomar
            columnas.add(resultadoMeta.getColumnName(i)/*Nombre de la columna en la pos actual*/);
        }
        return columnas;
    }

    public void PasajerosXVuelo() throws SQLException{
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado = db.obtenerResultado("call listarPasajerosXvuelo();");
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

    @Override
    public String toString() {
        return "AccesoBaseDeDatos{" +
                "conexion=" + conexion +
                ", nombreBaseDeDatos='" + nombreBaseDeDatos + '\'' +
                ", nombreTabla=" + nombreTabla +
                '}';
    }
}


    /*
    Colocar mysql-connector-java-8.0.21.jar en una carpeta llamada lib

    File -> Project Structure -> + -> JARs y directorios ->
    seleccionar mongo-java-driver -> tildar -> aplicar -> ok
    */

