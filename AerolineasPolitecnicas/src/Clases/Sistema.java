package Clases;

import Conector.AccesoBaseDeDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class    Sistema {
    private AccesoBaseDeDatos baseDeDatos;
    private HashSet<Vuelo>listaVuelos;
    private HashSet<Persona>listaPasajeros;
    private HashSet<Persona>listaTripulantes;
    private HashSet<Modelo>listaModelos;
    private HashSet<Avion>listaAviones;

    public Sistema(AccesoBaseDeDatos baseDeDatos, HashSet<Vuelo> listaVuelos,HashSet<Persona> listaPasajeros, HashSet<Persona> listaTripulantes, HashSet<Modelo>listaModelos,HashSet<Avion> listaAviones) {
        this.baseDeDatos = baseDeDatos;
        this.listaVuelos = listaVuelos;
        this.listaPasajeros = listaPasajeros;
        this.listaTripulantes = listaTripulantes;
        this.listaModelos= listaModelos;
        this.listaAviones = listaAviones;
    }

    public AccesoBaseDeDatos getBaseDeDatos() {
        return baseDeDatos;
    }

    public void setBaseDeDatos(AccesoBaseDeDatos baseDeDatos) {
        this.baseDeDatos = baseDeDatos;
    }

    public HashSet<Vuelo> getListaVuelos() {
        return listaVuelos;
    }

    public void setListaVuelos(HashSet<Vuelo> listaVuelos) {
        this.listaVuelos = listaVuelos;
    }
    public HashSet<Persona> getListaPasajeros() {
        return listaPasajeros;
    }

    public void setListaPasajeros(HashSet<Persona> listaPasajeros) {
        this.listaPasajeros = listaPasajeros;
    }

    public HashSet<Persona> getListaTripulantes() {
        return listaTripulantes;
    }

    public void setListaTripulantes(HashSet<Persona> listaTripulantes) {
        this.listaTripulantes = listaTripulantes;
    }

    public HashSet<Modelo> getListaModelos() {
        return listaModelos;
    }

    public void setListaModelos(HashSet<Modelo> listaModelos) {
        this.listaModelos = listaModelos;
    }

    public HashSet<Avion> getListaAviones() {
        return listaAviones;
    }

    public void setListaAviones(HashSet<Avion> listaAviones) {
        this.listaAviones = listaAviones;
    }

    public void cargarDatosPasajero(ResultSet resultado1) throws SQLException {
        HashMap<Integer,HashMap<String,Object>>datosPasajero=baseDeDatos.obtenerDatosPasajero(resultado1);
        for(Map.Entry<Integer, HashMap<String,Object>>  pasajeroId: datosPasajero.entrySet()){
            int dni=pasajeroId.getKey();
            String nombre=pasajeroId.getValue().get("nombre").toString();
            String apellido=pasajeroId.getValue().get("apellido").toString();
            Date nacimiento= (Date) pasajeroId.getValue().get("fecha_nacimiento");
        }
    }

    public void cargarDatosVuelo(ResultSet resultado1) throws SQLException {
        try {
            while (resultado1.next()) {
                int idvuelo=resultado1.getInt("idvuelo");
                int patente=resultado1.getInt("avion_patente1");
                Date fecha=resultado1.getDate("fecha_vuelo");
                String origen=resultado1.getString("origen"); //obtenemos los datos de un vuelo especifico
                String destino=resultado1.getString("destino");
                ResultSet resultado2= baseDeDatos.obtenerResultado("select * from tripulante_has_vuelo where vuelo_idvuelo="+idvuelo+";"); //Obtenemos todos los tripulantes asignados al vuelo que estamos recorriendo
                ResultSet resultado3= baseDeDatos.obtenerResultado("select * from vuelo_has_pasajero where vuelo_idvuelo= "+idvuelo+";"); // Obtenemos todos los pasajeros asignados al vuelo que estamos recorriendo
                HashSet<Tripulante>tripulantesVuelo=new HashSet<Tripulante>();
                HashSet<Pasajero>pasajerosVuelo=new HashSet<Pasajero>();
                try {

                    while (resultado2.next()){
                        int dniTrip=resultado2.getInt("tripulante_persona_dni");
                        for(Persona trip:listaTripulantes){ //teniendo ya la PK de un triplante
                            // de este nuevo resultset con los asignados a este vuelo,
                            // recorremos la lista general de tripulantes, agarramos todos sus datos, y lo agregamos
                            // a la lista de tripulantes del vuelo
                            if (trip.getDni()==dniTrip){
                                Tripulante tripulante=(Tripulante) trip;
                                tripulantesVuelo.add(tripulante);
                            }
                        }
                    }
                    resultado2.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    while (resultado3.next()){
                        int dniPas=resultado3.getInt("pasajero_persona_dni");
                        Pasajero pasajero=new Pasajero();
                        for(Persona pas:listaPasajeros){ //Realizamos lo mismo que con los tripulantes
                            // con los pasajeros
                            if (pas.getDni()==dniPas){
                                pasajero=(Pasajero) pas;
                                pasajerosVuelo.add(pasajero);
                            }
                        }
                    }
                    resultado3.close();
                }
                catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Avion avion=new Avion();
                for (Avion avion1:listaAviones){ //Para el vuelo que estamos recorriendo,
                    // obtenemos los datos del avion que se le tiene asignado, buscandolo en
                    // la lista general de aviones
                    if (avion.getPatente()==patente){
                        avion=avion1;
                    }
                }
                Vuelo vuelo=new Vuelo(idvuelo,avion,fecha,origen,destino,tripulantesVuelo,pasajerosVuelo,new HashMap<>());
                listaVuelos.add(vuelo);
            }
            resultado1.close();
        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }
    }

    public void cargarDatosModelo(ResultSet resultado1) throws SQLException {
        try {
            while (resultado1.next()) {
                String nombreModelo=resultado1.getString("modelo");
                int cantPasajeros=resultado1.getInt("cant_pasajeros");
                int cantTripulantes=resultado1.getInt("cant_trip_necesaria");
                Modelo modelo = new Modelo(nombreModelo,cantPasajeros,cantTripulantes);
                listaModelos.add(modelo); // Obtenemos los datos de un modelo, y lo agregamos a la lista
                // general de modelos
            }
            resultado1.close();
        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }
    }

    public void cargarDatosAvion(ResultSet resultado1) throws SQLException {
        try {
            while (resultado1.next()) {
                int patente=resultado1.getInt("patente");
                String modelos=resultado1.getString("modelo_modelo");
                int numSerie=resultado1.getInt("num_serie");
                Date fechaFabricacion=resultado1.getDate("fecha_fabricacion");
                Modelo modelo=new Modelo(); // obtenemos los datos de un avion, y  buscamos el modelo
                // en la lista general de modelos, para asi agregar todos los datos correspondientes
                // a este
                for(Modelo modelo1:listaModelos){
                    if (modelo1.getModelo()==modelos){
                        modelo=modelo1;
                    }
                }
                Avion avion=new Avion(patente,modelo,numSerie,fechaFabricacion);
                listaAviones.add(avion); // agregamos el avion con todos sus datos.
            }
            resultado1.close();
        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }
    }

    public void cargarDatosTripulantes(ResultSet resultado1) throws SQLException {
        try {
            while (resultado1.next()) {
                int dni= resultado1.getInt("dni");
                String nombre=resultado1.getString("nombre");
                String apellido=resultado1.getString("apellido");
                Date fechaNacimiento=resultado1.getDate("fecha_nacimiento");
                String modelos =resultado1.getString("modelo"); // Obtenemos los datos que el tripulante tiene asignados
                ResultSet resultado2= baseDeDatos.obtenerResultado("Select * from modelo_has_tripulante where tripulante_persona_dni="+dni+";");
                // seleccionamos todos los modelos que tiene permitidos volar el tripulante actual
                int ididioma = resultado1.getInt("ididioma");
                String nombreIdioma =resultado1.getString("idioma");
                HashSet<Modelo> modeloNuevo=new HashSet<Modelo>();
                HashSet<Idioma>idiomaNuevo= new HashSet<Idioma>();
                Modelo modelo= new Modelo();
                Idioma idioma = new Idioma();
                try {
                    while (resultado2.next()){
                        String mod=resultado2.getString("modelo_modelo");
                        for(Modelo modelo1:listaModelos){ // recorremos los modelos que el tripulante
                            // puede tripular y los buscamos en la lista general de modelos
                            // asi agregamos todos los datos correspondientes al hashset modeloNuevo,
                            // el cual contiene los modelos permitidos
                            if (mod.equals(modelo1.getModelo())){
                                modeloNuevo.add(modelo1);
                            }
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


                for(Idioma idioma1:listaIdiomas){ // realizamos los mismo de antes con los idiomas, para este
                    //tripulante obtengo sus idiomas, los busco en la lista general y obtengo todos sus datos
                    if (idioma1.getIdioma()==ididioma){
                        idioma=idioma1;
                        idiomaNuevo.add(idioma);
                    }
                }
                Tripulante tripulante=new Tripulante(dni,nombre,apellido,fechaNacimiento,modeloNuevo,idiomaNuevo);
                listaTripulantes.add(tripulante); // creamos y añadimos al tripulante
            }
            resultado1.close();
        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }
    }

    // EJ b
    public void PasajeroMasJoven() throws SQLException{
        Pasajero pasajero = new Pasajero();
        for (Vuelo v: listaVuelos) { // recorremos los vuelos
            Date joven = null;
            for(Pasajero p: v.getPasajeros()){ // para cada vuelo, obtenemos la lista de pasajeros
                if(joven==null||p.getNacimiento().compareTo(joven)<-1){ // comparamos sus fechas y
                    // guardamos la fecha mas reciente, osea la de el mas joven, si es mas joven, redefinimos
                    // joven y pasajero por los datos del nuevo
                    joven = p.getNacimiento();
                    pasajero = p;
                }
            }
            System.out.println("Pasajero mas joven en el vuelo "+ v.getIdVuelo() + " es " + pasajero.getDni());
        }
    }
    // EJ g
    public void idiomasHablados(){
        for (Vuelo v: listaVuelos) {
            System.out.println("Vuelo: " + v.getIdVuelo());
            for(Persona t: v.getTripulantes()){
                System.out.println("Tripulante: " + t.getNombre());
                System.out.println("Idiomas que Habla: ");
                for(Idioma i : ((Tripulante)t).getIdiomas()){
                    System.out.println(i + " ");
                }
            }
        }
    }

    // EJ h
    public void avionMasNuevo(){
        Date nuevo= null;
        int serie = 0;
        for (Avion a: listaAviones) { // recorremos la lista de aviones
            if(nuevo==null||nuevo.compareTo(a.getFechaFabricacion())<1){ // guardamos la fecha mas reciente
                // osea la de el avion mas nuevo y redefinimos nuevo y serie con los datos y la fecha
                // del avion correspondiente
                nuevo = a.getFechaFabricacion();
                serie = a.getNumeroSerie();
            }
        }
        System.out.println("Num. de Serie: " + serie);
    }

    @Override
    public String toString() {
        return "Sistema{" +
                "baseDeDatos=" + baseDeDatos +
                ", listaVuelos=" + listaVuelos +
                ", listaPasajeros=" + listaPasajeros +
                ", listaTripulantes=" + listaTripulantes +
                ", listaModelos=" + listaModelos +
                ", listaAviones=" + listaAviones +
                '}';
    }
}

