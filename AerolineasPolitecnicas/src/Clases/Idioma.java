package Clases;

public class Idioma {
    private int idioma;
    private String nombre_idioma;

    public Idioma(int idioma, String nombre_idioma) {
        this.idioma = idioma;
        this.nombre_idioma = nombre_idioma;
    }

    public Idioma() {
        this.idioma = 0;
        this.nombre_idioma= "";
    }

    public int getIdioma() {
        return idioma;
    }

    public void setIdioma(int idioma) {
        this.idioma = idioma;
    }

    public String getNombre_idioma() {
        return nombre_idioma;
    }

    public void setNombre_idioma(String nombre_idioma) {
        this.nombre_idioma = nombre_idioma;
    }

    @Override
    public String toString() {
        return "Idioma{" +
                "idioma=" + idioma +
                ", nombre_idioma='" + nombre_idioma + '\'' +
                '}';
    }
}
