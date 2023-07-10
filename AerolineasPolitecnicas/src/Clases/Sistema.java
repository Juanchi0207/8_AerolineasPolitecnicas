package Clases;

import Conector.AccesoBaseDeDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void cargarDatosPasajero(ResultSet resultado1) throws SQLException, ParseException {
        HashMap<Integer,HashMap<String,Object>>datosPasajero=baseDeDatos.obtenerDatosPasajero(resultado1);
        for(Map.Entry<Integer, HashMap<String,Object>>  pasajeroId: datosPasajero.entrySet()){
            int dni=pasajeroId.getKey();
            String nombre=pasajeroId.getValue().get("nombre").toString();
            String apellido=pasajeroId.getValue().get("apellido").toString();
            Date nacimiento= (Date) pasajeroId.getValue().get("fecha_nacimiento");
            String vip1=pasajeroId.getValue().get("vip").toString();
            Boolean vip=false;
            if (vip1.equals("1")){
                vip=true;
            }
            String necEspecial1=pasajeroId.getValue().get("necesidades_especiales").toString();
            Boolean necEspecial=false;
            if (necEspecial1.equals("1")){
                necEspecial=true;
            }
            Pasajero pasajero=new Pasajero(dni,nombre,apellido,nacimiento,vip,necEspecial);
            listaPasajeros.add(pasajero);
        }
    }

    public void cargarDatosVuelo(ResultSet resultado1) throws SQLException {
        HashMap<Integer,HashMap<String,Object>>datosVuelo=baseDeDatos.obtenerDatosVuelo(resultado1);
        for(Map.Entry<Integer, HashMap<String,Object>>  vueloId: datosVuelo.entrySet()){
        int idvuelo=vueloId.getKey();
        int patente=Integer.parseInt(vueloId.getValue().get("avion_patente1").toString());
        Date fecha= (Date) vueloId.getValue().get("fecha_vuelo");
        String origen=vueloId.getValue().get("origen").toString();
        String destino=vueloId.getValue().get("destino").toString();
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
    }

    public void cargarDatosModelo(ResultSet resultado1) throws SQLException {
        HashMap<String, HashMap<String, Object>> datosModelo=baseDeDatos.obtenerDatosModelo(resultado1);
        for(Map.Entry<String, HashMap<String, Object>> modeloId: datosModelo.entrySet()) {
            String nombreModelo=modeloId.getKey().toString();
            int cantPasajeros= Integer.parseInt(modeloId.getValue().get("cant_pasajeros").toString());
            int cantTripulantes= Integer.parseInt(modeloId.getValue().get("cant_trip_necesaria").toString());
            Modelo modelo = new Modelo(nombreModelo, cantPasajeros, cantTripulantes);
            listaModelos.add(modelo);
        }
         // Obtenemos los datos de un modelo, y lo agregamos a la lista
        // general de modelos
    }


    public void cargarDatosAvion(ResultSet resultado1) throws SQLException {
        HashMap<Integer, HashMap<String, Object>> datosModelo=baseDeDatos.obtenerDatosAvion(resultado1);
        for(Map.Entry<Integer, HashMap<String, Object>> avionId: datosModelo.entrySet()) {
            int patente=Integer.parseInt(avionId.getKey().toString());
            String modeloAux= avionId.getValue().get("modelo_modelo").toString();
            Modelo modelo=new Modelo();
            for (Modelo mod1:listaModelos){
                if (modeloAux.equals(mod1.getModelo())){
                    modelo=mod1;
                }
            }
            int numSerie= Integer.parseInt(avionId.getValue().get("num_serie").toString());
            Date fechaFab= (Date) avionId.getValue().get("fecha_nacimiento");
            Avion avion = new Avion(patente,modelo,numSerie,fechaFab);
            listaAviones.add(avion);
        }
    }

    public void cargarDatosTripulantes(ResultSet resultado1) throws SQLException {
        HashMap<Integer,HashMap<String,Object>>datosTripulantes=baseDeDatos.obtenerDatosTripulantes(resultado1);
        for(Map.Entry<Integer, HashMap<String,Object>>  pasajeroId: datosTripulantes.entrySet()){
            int dni=pasajeroId.getKey();
            String nombre=pasajeroId.getValue().get("nombre").toString();
            String apellido=pasajeroId.getValue().get("apellido").toString();
            Date nacimiento= (Date) pasajeroId.getValue().get("fecha_nacimiento");
            ResultSet resultado2 = baseDeDatos.obtenerResultado("Select * from modelo_has_tripulante where tripulante_persona_dni=" + dni + ";");
            // seleccionamos todos los modelos que tiene permitidos volar el tripulante actual
            ResultSet resultado3 = baseDeDatos.obtenerResultado("Select idioma_ididioma, idioma from idioma_has_tripulante join idioma on idioma_ididioma=ididioma where tripulante_persona_dni=" + dni + ";");
            HashSet<Modelo> modeloNuevo = new HashSet<Modelo>();
            HashSet<Idioma> idiomaNuevo = new HashSet<Idioma>();
            Modelo modelo = new Modelo();
                try {
                    while (resultado2.next()) {
                        String mod = resultado2.getString("modelo_modelo");
                        for (Modelo modelo1 : listaModelos) { // recorremos los modelos que el tripulante
                            // puede tripular y los buscamos en la lista general de modelos
                            // asi agregamos todos los datos correspondientes al hashset modeloNuevo,
                            // el cual contiene los modelos permitidos
                            if (mod.equals(modelo1.getModelo())) {
                                modeloNuevo.add(modelo1);
                            }
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    while (resultado3.next()){
                        int idIdioma=resultado3.getInt("idioma_ididioma");
                        String nomIdioma=resultado3.getString("idioma");
                        Idioma idioma1=new Idioma(idIdioma,nomIdioma);
                        idiomaNuevo.add(idioma1);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Tripulante tripulante=new Tripulante(dni,nombre,apellido,nacimiento,modeloNuevo,idiomaNuevo);
                listaTripulantes.add(tripulante); // creamos y añadimos al tripulante
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
    public HashMap<Integer,HashSet<Idioma>> idiomasHablados(){
        HashMap<Integer,HashSet<Idioma>> idiomasxVuelo = new HashMap<Integer, HashSet<Idioma>>();
        for (Vuelo v: listaVuelos) {
            HashSet<Idioma> idiomaxVuelo = new HashSet<Idioma>();
            for(Persona t: v.getTripulantes()){
                HashSet<Idioma>idiomasT=((Tripulante)t).getIdiomas();
                for (Idioma idioma:idiomasT){
                    idiomaxVuelo.add(idioma);
                }
            }
            idiomasxVuelo.put(v.getIdVuelo(),idiomaxVuelo);
        }
        return idiomasxVuelo;
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

