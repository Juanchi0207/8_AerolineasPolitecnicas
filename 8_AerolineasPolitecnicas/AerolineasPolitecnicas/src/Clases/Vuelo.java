package Clases;
import Conector.AccesoBaseDeDatos;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Vuelo {
    private int idVuelo;
    private Avion avion;
    private Date fecha;
    private String origen;
    private String destino;
    private HashSet<Tripulante>tripulantes = new HashSet<Tripulante>();
    private HashSet<Pasajero>pasajeros = new HashSet<>();
    private HashMap<Tripulante, Sector>area = new HashMap<>();



    public Vuelo(int idVuelo,Avion avion, Date fecha, String origen, String destino, HashSet<Tripulante> tripulantes, HashSet<Pasajero> pasajeros, HashMap<Tripulante, Sector> area) {
        this.idVuelo=idVuelo;
        this.avion = avion;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.tripulantes = tripulantes;
        this.pasajeros = pasajeros;
        this.area = area;
    }

    public Vuelo() {
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public HashSet<Tripulante> getTripulantes() {
        return tripulantes;
    }

    public void setTripulantes(HashSet<Tripulante> tripulantes) {
        this.tripulantes = tripulantes;
    }

    public HashSet<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(HashSet<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
    }

    public HashMap<Tripulante, Sector> getArea() {
        return area;
    }

    public void setArea(HashMap<Tripulante, Sector> area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "avion=" + avion +
                ", fecha=" + fecha +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", tripulantes=" + tripulantes +
                ", pasajeros=" + pasajeros +
                ", area=" + area +
                '}';
    }
}
