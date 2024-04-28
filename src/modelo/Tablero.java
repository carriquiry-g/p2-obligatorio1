package modelo;

import java.util.ArrayList;

public class Tablero {
    private int dimension;
    private ArrayList<Autito> listaAutos;
    private ArrayList<Color> listaColores;

    public Tablero(){
        //TODO: generar valores por defecto
    }
    
    public Tablero(int dimension, ArrayList<Autito> listaAutos, ArrayList<Color> listaColores){
        this.setDimension(dimension);
        this.setListaAutos(listaAutos);
        this.setListaColores(listaColores);
    }
    
    public void rotar(){
        //TODO: rotar tablero
    }
    
    public void obtenerPosiblesMovimientos(){
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
