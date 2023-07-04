import Clases.*;
import Conector.AccesoBaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws SQLException {
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");
        Sistema sistema = new Sistema(db,new HashSet<Vuelo>(),new HashSet<Idioma>(),new HashSet<Persona>(),new HashSet<Persona>(),new HashSet<Modelo>(),new HashSet<Avion>());
        sistema.cargarDatosPasajero(db.obtenerResultado("select dni, nombre,apellido,fecha_nacimiento,vip,necesidades_especiales from persona join pasajero on dni=persona_dni;"));
        sistema.cargarDatosModelo(db.obtenerResultado("Select * from modelo;"));
        sistema.cargarDatosAvion(db.obtenerResultado("select * from avion;"));
        sistema.cargarDatosTripulantes(db.obtenerResultado(("Select dni,nombre,apellido,fecha_nacimiento,modelo,cant_pasajeros,cant_trip_necesaria, ididioma, idioma from persona join tripulante on dni=persona_dni join idioma_has_tripulante on tripulante_persona_dni=tripulante.persona_dni join idioma on ididioma= idioma_ididioma join modelo_has_tripulante on modelo_has_tripulante.tripulante_persona_dni=persona_dni join modelo on modelo=modelo_modelo;")));
        sistema.cargarDatosVuelo(db.obtenerResultado("Select * from vuelo;"));
        System.out.println("Ejercicio 3.a | Muestra los Pasajeros por cada Vuelo en la DB: ");
        System.out.println(" ");
        db.PasajerosXVuelo();
        System.out.println(" ");
        System.out.println("Ejercicio 3.b | Muestra el Pasajero mas joven de cad Vuelo: ");
        System.out.println(" ");
        sistema.PasajeroMasJoven();
        System.out.println(" ");
        System.out.println("Ejercicio 3.c | Muestra los Vuelos que no alcanzan la Trip. Minima: ");
        System.out.println(" ");
        db.cantidadMinimaTripulantes();
        System.out.println(" ");
        System.out.println("Ejercicio 3.d | Muestra los Vuelos que tienen Personal (Tripulacion) no autorizada: ");
        System.out.println(" ");
        db.vuelosXtripNoAutorizados();
        System.out.println(" ");
        System.out.println("Ejercicio 3.e | Muestra los Tripulantes que rompen la regla de '1 Vuelo x/Dia': ");
        System.out.println(" ");
        db.reglaRota();
        System.out.println(" ");
        System.out.println("Ejercicio 3.f | Cambia el Vuelo del Pasajero pasado por Parametros: ");
        System.out.println(" ");
        //si llegas a ejecutar sin querer, en la base de datos ejecuta estas dos sentencias en orden para poder probarlo
        //delete from vuelo_has_pasajero where pasajero_persona_dni=46878279 and vuelo_idvuelo=3;
        //insert into vuelo_has_pasajero values(1,46878279);
        db.cambiarvueloPasajero(46878279,1);
        System.out.println(" ");
        System.out.println("Ejercicio 3.g | Muestra los idiomas que habla la Tripulacion de cada Vuelo: ");
        System.out.println(" ");
        sistema.idiomasHablados();
        System.out.println(" ");
        System.out.println("Ejercicio 3.h | Muestra el Avion mas nuevo: ");
        System.out.println(" ");
        sistema.avionMasNuevo();
    }
}

