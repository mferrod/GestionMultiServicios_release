package dev.marianof.gestionmultiservicios.Model;

public class PosicionesFactura {
    private final int pos1;
    private final int pos2;
    private final int pos3;

    public PosicionesFactura(int pos1, int pos2, int pos3) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.pos3 = pos3;
    }

    public int getPos1() {
        return pos1;
    }

    public int getPos2() {
        return pos2;
    }

    public int getPos3() {
        return pos3;
    }
}
