package modelo;

public class Autito {

    private int direccion;
    private Color color;
    private int fila;
    private int columna;

    public Autito(int direccion, Color color, int fila, int columna) {
        this.setDireccion(direccion);
        this.setColor(color);
        this.setFila(fila);
        this.setColumna(columna);
    }

    public int rotar(int rotacion) {
        return (this.getDireccion() + rotacion) % 4;
    }

    public void chocar(int fila, int columna) {
        //TODO: mecanica de chocar
        this.setFila(fila);
        this.setColumna(columna);
    }

    public int getDireccion() {
        return this.direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getFila() {
        return this.fila;
    }

    public char getLetraFila() {
        return (char) ('A' + this.getFila());
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return this.columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public String toString() {
        return "" + this.getLetraFila() + (this.getColumna() + 1) + this.getDireccion();
    }
}
