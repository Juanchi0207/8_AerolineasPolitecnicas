package Clases;

public class Avion extends Modelo{
    private int numeroSerie;
    private String patente;

    public Avion() {
        super();
        this.numeroSerie = 0;
        this.patente = "LV-AAA";
    }

    public Avion(String modelo, int cantidadTripulacion, int cantidadPasajeros, int numeroSerie, String patente) {
        super(modelo, cantidadTripulacion, cantidadPasajeros);
        this.numeroSerie = numeroSerie;
        this.patente = patente;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "numeroSerie=" + numeroSerie +
                ", patente='" + patente + '\'' +
                '}';
    }
}
