package Clases;

import java.util.Date;
import java.util.HashSet;

public class Tripulante extends Persona{
    private HashSet<Modelo>modelos = new HashSet<>();
    private HashSet<String>idiomas = new HashSet<>();

    public Tripulante() {
        super();
        this.modelos.add(new Modelo());
        this.idiomas.add("Español");
    }

    public Tripulante(String nombre, String apellido, Date nacimiento, int dni, HashSet<Modelo> modelos, HashSet<String> idiomas) {
        super(nombre, apellido, nacimiento, dni);
        this.modelos = modelos;
        this.idiomas = idiomas;
    }

    public HashSet<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(HashSet<Modelo> modelos) {
        this.modelos = modelos;
    }

    public HashSet<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(HashSet<String> idiomas) {
        this.idiomas = idiomas;
    }

    @Override
    public String toString() {
        return "Tripulante{" +
                "modelos=" + modelos +
                ", idiomas=" + idiomas +
                '}';
    }
}
