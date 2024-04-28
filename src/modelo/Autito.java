package modelo;

import java.util.Random;

public class Autito {

    private int direccion;
    private Color color;
    private String coordenadas;

    public Autito(Color color) {
        Random random = new Random();
        this.setDireccion(random.nextInt((3-0)+1));
        this.setColor(color);
    }

    public Autito(int direccion, Color color, String coordenadas) {

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

    public String getCoordenadas() {
        return this.coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
}
