package Clases;

public enum Sector {
    NORTE("Ala-Norte"), SUR("Ala-Sur"), ESTE("Ala-Este"), OESTE("Ala-Oeste");

    private String sectorAvion;

    Sector(String sectorAvion) {
        this.sectorAvion = sectorAvion;
    }

    public String getSectorAvion() {
        return sectorAvion;
    }

    public void setSectorAvion(String sectorAvion) {
        this.sectorAvion = sectorAvion;
    }

    @Override
    public String toString() {
        return "Sector: " + sectorAvion;
    }
}
