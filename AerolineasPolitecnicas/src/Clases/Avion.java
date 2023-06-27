package Clases;

import java.util.Date;

public class Avion extends Modelo{
    private int patente;
    private Modelo modelo;
    private int numeroSerie;
    private Date fechaFabricacion;

    public Avion(int patente, Modelo modelo, int numeroSerie, Date fechaFabricacion) {
        this.patente = patente;
        this.modelo = modelo;
        this.numeroSerie = numeroSerie;
        this.fechaFabricacion = fechaFabricacion;
    }

    public Avion(String modelo, int cantidadTripulacion, int cantidadPasajeros, int patente, Modelo modelo1, int numeroSerie, Date fechaFabricacion) {
        super(modelo, cantidadTripulacion, cantidadPasajeros);
        this.patente = patente;
        this.modelo = modelo1;
        this.numeroSerie = numeroSerie;
        this.fechaFabricacion = fechaFabricacion;
    }

    public Avion() {

    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public int getPatente() {
        return patente;
    }

    public void setPatente(int patente) {
        this.patente = patente;
    }

    public Date getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(Date fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "numeroSerie=" + numeroSerie +
                ", patente='" + patente + '\'' +
                '}';
    }
}
