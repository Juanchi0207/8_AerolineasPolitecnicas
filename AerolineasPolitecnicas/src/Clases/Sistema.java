package Clases;

import Conector.AccesoBaseDeDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Sistema {
    private AccesoBaseDeDatos baseDeDatos;
    private HashSet<Vuelo>listaVuelos;
    private HashSet<Idioma>listaIdiomas;
    private HashSet<Persona>listaPasajeros;
    private HashSet<Persona>listaTripulantes;
    private HashSet<Modelo>listaModelos;
    private HashSet<Avion>listaAviones;

    public Sistema(AccesoBaseDeDatos baseDeDatos, HashSet<Vuelo> listaVuelos, HashSet<Idioma>listaIdiomas,HashSet<Persona> listaPasajeros, HashSet<Persona> listaTripulantes, HashSet<Modelo>listaModelos,HashSet<Avion> listaAviones) {
        this.baseDeDatos = baseDeDatos;
        this.listaVuelos = listaVuelos;
        this.listaIdiomas=listaIdiomas;
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

    public HashSet<Idioma> getListaIdiomas() {
        return listaIdiomas;
    }

    public void setListaIdiomas(HashSet<Idioma> listaIdiomas) {
        this.listaIdiomas = listaIdiomas;
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
        try {
            while (resultado1.next()) {
                int dni= resultado1.getInt("dni");
                String nombre=resultado1.getString("nombre");
                String apellido=resultado1.getString("apellido");
                Date fechaNacimiento=resultado1.getDate("fecha_nacimiento");
                boolean vip=resultado1.getBoolean("vip");
                boolean necesidades_especiales=resultado1.getBoolean("necesidades_especiales");
                Pasajero pasajero=new Pasajero(dni,nombre,apellido,fechaNacimiento,vip,necesidades_especiales);
                listaPasajeros.add(pasajero);
            }
            resultado1.close();
        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }
    }

    public void cargarDatosIdiomas(ResultSet resultado1) throws SQLException {
        try {
            while (resultado1.next()) {
                int id= resultado1.getInt("ididioma");
                String nombre=resultado1.getString("idioma");
                Idioma idioma=new Idioma(id,nombre);
                listaIdiomas.add(idioma);
            }
            resultado1.close();
        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }
    }


    public void cargarDatosVuelo(ResultSet resultado1) throws SQLException {
        try {
            while (resultado1.next()) {
                int idvuelo=resultado1.getInt("idvuelo");
                int patente=resultado1.getInt("avion_patente1");
                Date fecha=resultado1.getDate("fecha_vuelo");
                String origen=resultado1.getString("origen");
                String destino=resultado1.getString("destino");
                ResultSet resultado2= baseDeDatos.obtenerResultado("select * from tripulante_has_vuelo where vuelo_idvuelo="+idvuelo+";");
                ResultSet resultado3= baseDeDatos.obtenerResultado("select * from vuelo_has_pasajero where vuelo_idvuelo= "+idvuelo+";");
                HashSet<Tripulante>tripulantesVuelo=new HashSet<Tripulante>();
                HashSet<Pasajero>pasajerosVuelo=new HashSet<Pasajero>();
                try {

                    while (resultado2.next()){
                        int dniTrip=resultado2.getInt("tripulante_persona_dni");
                        for(Persona trip:listaTripulantes){
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
                        for(Persona pas:listaPasajeros){
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
                for (Avion avion1:listaAviones){
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
                listaModelos.add(modelo);
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
                Modelo modelo=new Modelo();
                for(Modelo modelo1:listaModelos){
                    if (modelo1.getModelo()==modelos){
                        modelo=modelo1;
                    }
                }
                Avion avion=new Avion(patente,modelo,numSerie,fechaFabricacion);
                listaAviones.add(avion);
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
                String modelos =resultado1.getString("modelo");
                ResultSet resultado2= baseDeDatos.obtenerResultado("Select * from modelo_has_tripulante where tripulante_persona_dni="+dni+";");
                int ididioma = resultado1.getInt("ididioma");
                String nombreIdioma =resultado1.getString("idioma");
                HashSet<Modelo> modeloNuevo=new HashSet<Modelo>();
                HashSet<Idioma>idiomaNuevo= new HashSet<Idioma>();
                Modelo modelo= new Modelo();
                Idioma idioma = new Idioma();
                try {
                    while (resultado2.next()){
                        String mod=resultado2.getString("modelo_modelo");
                        for(Modelo modelo1:listaModelos){
                            if (mod.equals(modelo1.getModelo())){
                                modeloNuevo.add(modelo1);
                            }
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }


                for(Idioma idioma1:listaIdiomas){
                    if (idioma1.getIdioma()==ididioma){
                        idioma=idioma1;
                        idiomaNuevo.add(idioma);
                    }
                }
                Tripulante tripulante=new Tripulante(dni,nombre,apellido,fechaNacimiento,modeloNuevo,idiomaNuevo);
                listaTripulantes.add(tripulante);
            }

            resultado1.close();
        } catch (SQLException excepcion) {
            excepcion.printStackTrace();
        }
    }


    // EJ a
    public void PasajerosXVuelo() throws SQLException{
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado = db.obtenerResultado("call listarPasajerosXvuelo();");

        db.imprimirDatos(resultado);
    }


    // EJ b
    public void PasajeroMasJoven() throws SQLException{
        Pasajero pasajero = new Pasajero();
        for (Vuelo v: listaVuelos) {
            Date joven = null;
            for(Pasajero p: v.getPasajeros()){
                if(joven==null||p.getNacimiento().compareTo(joven)<-1){
                    joven = p.getNacimiento();
                    pasajero = p;
                }
            }
            System.out.println("Pasajero mas joven en el vuelo "+ v.getIdVuelo() + " es " + pasajero.getDni());
        }
    }
    // EJ c
    public void noTripMinima(){

    }
    // EJ d
    public void vuelosXtripNoAutorizados(){
        for (Vuelo vuelo:listaVuelos){
            boolean estado=false;
            Avion avion=vuelo.getAvion();
            Modelo modelo=avion.getModelo();
            HashSet<Tripulante>tripulantes=vuelo.getTripulantes();
            for (Tripulante trips:tripulantes){
                int dni=trips.getDni();
                for (Persona trip1:listaTripulantes){
                    if(trip1.getDni()==dni){
                        HashSet<Modelo>modelos=((Tripulante) trip1).getModelos();
                        for (Modelo modelo1:modelos){
                            if (modelo1.getModelo().equals(modelo)){
                                estado=true;
                            }
                        }
                    }
                }
            }
            if (estado==true){
                System.out.println(vuelo.getIdVuelo());
            }

        }
    }


    // EJ e
    public void reglaRota(Date fecha){
        int cont = 0;
        System.out.println("Tripulantes: ");
        for (Persona t: listaTripulantes) {
            for(Vuelo v: listaVuelos){
                if(v.getFecha()==fecha){
                    System.out.println("entro");

                    if(v.getTripulantes().contains(t)){
                        cont++;
                        if(cont > 1){
                            System.out.println(t.toString());
                        }else {

                        }
                    }

                }

            }

        }
    }

    // EJ f
    public void cambiarPasaje() throws SQLException{
        AccesoBaseDeDatos db = new AccesoBaseDeDatos("AerolineasPolitecnicas");
        db.conectar("alumno", "alumnoipm");

        ResultSet resultado = db.obtenerResultado("call cambioPasaje();");

        db.imprimirDatos(resultado);
    }

    // EJ g
    public void idiomasHablados(){
        for (Vuelo v: listaVuelos) {
            System.out.println("Vuelo: " + v.getIdVuelo());
            boolean ingles = false;
            boolean castellano= false;
            boolean portugues=false;
            boolean aleman= false;
            boolean italiano=false;
            for (Persona t: v.getTripulantes()) {
                HashSet<Idioma>idomasT=((Tripulante)t).getIdiomas();
                for (Idioma idioma1:idomasT){
                    if (idioma1.getNombre_idioma().equals("Ingles")){
                        ingles=true;
                    }
                    if (idioma1.getNombre_idioma().equals("Castellano")){
                        castellano=true;
                    }
                    if (idioma1.getNombre_idioma().equals("Portugues")){
                        portugues=true;
                    }
                    if (idioma1.getNombre_idioma().equals("Aleman")){
                        aleman=true;
                    }
                    if (idioma1.getNombre_idioma().equals("Italiano")){
                        italiano=true;
                    }
                }

            }
            String idiomasHablados="";
            if (ingles==true){
                idiomasHablados=idiomasHablados+"Ingles ";
            }
            if (castellano==true){
                idiomasHablados=idiomasHablados+"Castellano ";
            }
            if (portugues==true){
                idiomasHablados=idiomasHablados+"Portugues ";
            }
            if (aleman==true){
                idiomasHablados=idiomasHablados+"Aleman ";
            }
            if (italiano==true){
                idiomasHablados=idiomasHablados+"Italiano ";
            }
            System.out.println(idiomasHablados);
        }
    }

    // EJ h
    public void avionMasNuevo(){
        Date nuevo= null;
        int serie = 0;
        for (Avion a: listaAviones) {
            if(nuevo==null||nuevo.compareTo(a.getFechaFabricacion())<1){
                nuevo = a.getFechaFabricacion();
                serie = a.getNumeroSerie();
            }
        }
        System.out.println("Num. de Serie: " + serie);
    }


}

