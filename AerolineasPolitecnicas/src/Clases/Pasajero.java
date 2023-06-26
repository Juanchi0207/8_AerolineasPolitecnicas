package Clases;

import java.util.Date;

public class Pasajero extends Persona{
    private boolean vip;
    private boolean especial;


    public Pasajero() {
        super();
        this.vip = false;
        this.especial = false;
    }

    public Pasajero(int dni,String nombre, String apellido, Date nacimiento, boolean vip, boolean especial) {
        super(dni,nombre , apellido, nacimiento);
        this.vip = vip;
        this.especial = especial;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                "vip=" + vip +
                ", especial=" + especial +
                '}';
    }
}
