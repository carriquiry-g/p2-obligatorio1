//Darian Saldaña 230846
//Gaston Carriquiry 230498
package modelo;

import java.util.Arrays;

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
        int diferenciaFila = fila - this.getFila();
        int diferenciaColumna = columna - this.getColumna();

        if (diferenciaFila < 0 && diferenciaColumna == 0) {
            this.setDireccion(0);
        } else if (diferenciaFila == 0 && diferenciaColumna > 0) {
            this.setDireccion(1);
        } else if (diferenciaFila > 0 && diferenciaColumna == 0) {
            this.setDireccion(2);
        } else if (diferenciaFila == 0 && diferenciaColumna < 0) {
            this.setDireccion(3);
        }
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
        return "" + this.getLetraFila() + (this.getColumna() + 1);
    }
}
