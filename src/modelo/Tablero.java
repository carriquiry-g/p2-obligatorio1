package modelo;

import java.util.ArrayList;

public class Tablero {

    private int dimension;
    private ArrayList<Autito> listaAutos;
    private ArrayList<Color> listaColores;

    public Tablero(int tipoTablero, ArrayList<Color> colores) {
        switch (tipoTablero) {
            case 1:
                //Al azar
                break;
            case 2:
                //Configurado por el usuario
                break;
            case 3:
                //Predefinido
                break;
            default:
                break;
        }
        this.setListaColores(listaColores);
    }

    public void rotar() {
        //TODO: rotar tablero
    }

    public void obtenerPosiblesMovimientos() {
        //TODO: devolver posibles movimientos
    }

    public int getDimension() {
        return this.dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public ArrayList<Autito> getListaAutos() {
        return this.listaAutos;
    }

    public void setListaAutos(ArrayList<Autito> listaAutos) {
        this.listaAutos = listaAutos;
    }

    public ArrayList<Color> getListaColores() {
        return this.listaColores;
    }

    public void setListaColores(ArrayList<Color> listaColores) {
        this.listaColores = listaColores;
    }
}
