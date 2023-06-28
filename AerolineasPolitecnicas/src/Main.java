import Clases.*;
import Conector.AccesoBaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws SQLException {
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");
        Sistema sistema = new Sistema(db,new HashSet<Vuelo>(),new HashSet<Idioma>(),new HashSet<Persona>(),new HashSet<Persona>(),new HashSet<Modelo>(),new HashSet<Avion>());
        sistema.cargarDatosPasajero(db.obtenerResultado("select dni, nombre,apellido,fecha_nacimiento,vip,necesidades_especiales from persona join pasajero on dni=persona_dni;"));
        sistema.cargarDatosIdiomas(db.obtenerResultado("select * from idioma;"));
        sistema.cargarDatosModelo(db.obtenerResultado("Select * from modelo;"));
        sistema.cargarDatosAvion(db.obtenerResultado("select * from avion;"));
        sistema.cargarDatosTripulantes(db.obtenerResultado(("Select dni,nombre,apellido,fecha_nacimiento,modelo,cant_pasajeros,cant_trip_necesaria, ididioma, idioma from persona join tripulante on dni=persona_dni join idioma_has_tripulante on tripulante_persona_dni=tripulante.persona_dni join idioma on ididioma= idioma_ididioma join modelo_has_tripulante on modelo_has_tripulante.tripulante_persona_dni=persona_dni join modelo on modelo=modelo_modelo;")));
        sistema.cargarDatosVuelo(db.obtenerResultado("Select * from vuelo;"));


        // a sistema.PasajerosXVuelo();
        // b sistema.PasajeroMasJoven();
        // c db.cantidadMinimaTripulantes();
        // d sistema.vuelosXtripNoAutorizados(); NO FUNCIONA
        // e sistema.reglaRota(new Date()); NO FUNCIONA
        // f db.cambiarvueloPasajero();
        // g sistema.idiomasHablados();
        // h sistema.avionMasNuevo();
    }
}

