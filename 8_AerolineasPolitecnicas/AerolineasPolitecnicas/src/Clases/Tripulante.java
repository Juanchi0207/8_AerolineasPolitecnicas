package Clases;

import java.util.Date;
import java.util.HashSet;

public class Tripulante extends Persona{
    private HashSet<Modelo>modelos = new HashSet<>();
    private HashSet<Idioma>idiomas = new HashSet<>();

    public Tripulante() {
        super();
        this.modelos.add(new Modelo());
        this.idiomas.add(new Idioma());
    }

    public Tripulante(int dni,String nombre, String apellido, Date nacimiento, HashSet<Modelo> modelos, HashSet<Idioma> idiomas) {
        super(dni,nombre, apellido, nacimiento);
        this.modelos = modelos;
        this.idiomas = idiomas;
    }

    public HashSet<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(HashSet<Modelo> modelos) {
        this.modelos = modelos;
    }

    public HashSet<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(HashSet<Idioma> idiomas) {
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
