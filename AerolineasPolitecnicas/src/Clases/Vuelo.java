package Clases;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Vuelo {
    private Avion avion;
    private Date fecha;
    private String origen;
    private String destino;
    private HashSet<Tripulante>tripulantes = new HashSet<>();
    private HashSet<Pasajero>pasajeros = new HashSet<>();
    private HashMap<Tripulante, Sector>area = new HashMap<>();

    public Vuelo() {
        this.avion = new Avion();
        this.fecha = null;
        this.origen = "Argentina";
        this.destino = "Argentina";
        this.tripulantes.add(new Tripulante());
        this.pasajeros.add(new Pasajero());
        this.area.put(new Tripulante(), Sector.GOOFYAHH);
    }

    public Vuelo(Avion avion, Date fecha, String origen, String destino, HashSet<Tripulante> tripulantes, HashSet<Pasajero> pasajeros, HashMap<Tripulante, Sector> area) {
        this.avion = avion;
        this.fecha = fecha;
        this.origen = origen;
        this.destino = destino;
        this.tripulantes = tripulantes;
        this.pasajeros = pasajeros;
        this.area = area;
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
