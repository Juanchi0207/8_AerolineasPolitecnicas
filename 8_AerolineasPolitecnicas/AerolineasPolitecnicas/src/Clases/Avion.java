package Clases;

import java.util.Date;

public class Avion{
    private int patente;
    private Modelo modeloAvion;
    private int numeroSerie;
    private Date fechaFabricacion;

    public Avion(int patente, Modelo modelo, int numeroSerie, Date fechaFabricacion) {
        this.patente = patente;
        this.modeloAvion = modelo;
        this.numeroSerie = numeroSerie;
        this.fechaFabricacion = fechaFabricacion;
    }


    public Avion() {

    }

    public Modelo getModelo() {
        return modeloAvion;
    }

    public void setModelo(Modelo modelo) {
        this.modeloAvion = modelo;
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
