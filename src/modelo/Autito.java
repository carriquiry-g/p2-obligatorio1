package modelo;

import java.util.Arrays;
import java.util.Random;

public class Autito {

    private int direccion;
    private Color color;
    private int fila;
    private int columna;

    public Autito(Color color) {
        Random random = new Random();
        this.setDireccion(random.nextInt((3 - 0) + 1));
        this.setColor(color);
    }

    public Autito(int direccion, Color color, int fila, int columna) {
        this.setDireccion(direccion);
        this.setColor(color);
        this.setFila(fila);
        this.setColumna(columna);
    }

    public void rotar() {
        //TODO: mecanica de rotar
    }

    public void chocar() {
        //TODO: mecanica de chocar
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

    public String[] renderizar() {
        String[] configuracion;
        // ANSI escape codes for colors
        final String RESET = "\u001B[0m";
        final String RED_TEXT_YELLOW_BG = "\u001B[31;43m"; // Red text on yellow background

        String colorANSI = this.getColor().toString();

        switch (this.getDireccion()) {
            case 0: // Arriba
                configuracion = new String[]{
                    " " + RED_TEXT_YELLOW_BG + "oo" + RESET + " ",
                    colorANSI + " ** " + RESET,
                    colorANSI + " ** " + RESET,
                    colorANSI + " ** " + RESET
                };
                break;
            case 1: // Derecha
                configuracion = new String[]{
                    "    ",
                    colorANSI + "***" + RED_TEXT_YELLOW_BG + "o" + RESET,
                    colorANSI + "***" + RED_TEXT_YELLOW_BG + "o" + RESET,
                    "    "
                };
                break;
            case 2: // Abajo
                configuracion = new String[]{
                    colorANSI + " ** " + RESET,
                    colorANSI + " ** " + RESET,
                    colorANSI + " ** " + RESET,
                    " " + RED_TEXT_YELLOW_BG + "oo" + RESET + " "
                };
                break;
            case 3: // Izquierda
                configuracion = new String[]{
                    "    ",
                    RED_TEXT_YELLOW_BG + "o"+ RESET + colorANSI + "***" + RESET,
                    RED_TEXT_YELLOW_BG + "o"+ RESET + colorANSI + "***" + RESET,
                    "    "
                };
                break;
            default:
                configuracion = new String[]{"    ", "    ", "    ", "    "};
        }
        return configuracion;
    }
}
