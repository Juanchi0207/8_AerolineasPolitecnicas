package Clases;

public class Modelo {
    private String modelo;
    private int cantidadTripulacion;
    private int cantidadPasajeros;

    public Modelo() {
        this.modelo = "Test";
        this.cantidadPasajeros = 0;
        this.cantidadTripulacion = 0;
    }

    public Modelo(String modelo, int cantidadTripulacion, int cantidadPasajeros) {
        this.modelo = modelo;
        this.cantidadTripulacion = cantidadTripulacion;
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCantidadTripulacion() {
        return cantidadTripulacion;
    }

    public void setCantidadTripulacion(int cantidadTripulacion) {
        this.cantidadTripulacion = cantidadTripulacion;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "modelo='" + modelo + '\'' +
                ", cantidadTripulacion=" + cantidadTripulacion +
                ", cantidadPasajeros=" + cantidadPasajeros +
                '}';
    }
}
