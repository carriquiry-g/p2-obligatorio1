package modelo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Tablero {

    private Scanner scanner;
    private Autito[][] tablero;
    private int dimension;
    private ArrayList<Autito> listaAutos;
    private ArrayList<Color> listaColores;

    public Tablero(int tipoTablero, ArrayList<Color> colores) {
        this.setListaColores(colores);
        scanner = new Scanner(System.in);

        switch (tipoTablero) {
            case 1:
                //Al azar
                this.setTablero(this.crearTableroAlAzar());
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
    }

    public Autito[][] crearTableroAlAzar() {
        Random random = new Random();

        System.out.print("Ingrese la dimension deseada para el tablero (5, 6 o 7): ");
        int dim = scanner.nextInt();
        scanner.nextLine();
        this.setDimension(dim);
        Autito[][] tableroAux = new Autito[dim][dim];

        int n = 0;
        while (n < 3 || n > 12) {
            System.out.print("Ingrese la cantidad de autos deseados para el tablero (entre 3 y 12): ");
            try {
                n = scanner.nextInt();
                scanner.nextLine();

                if (n < 3 || n > 12) {
                    System.out.println("Error: la cantidad de autos debe estar entre 3 y 12.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: la cantidad de autos debe ser un numero entero entre 3 y 12.");
                scanner.nextLine();
            }
        }

        boolean esTableroValido = false;

        while (!esTableroValido) {
            for (int i = 0; i < n; i++) {
                int fila, columna;

                do {
                    fila = random.nextInt(dim);
                    columna = random.nextInt(dim);
                } while (tableroAux[fila][columna] != null);

                int direccion = random.nextInt((3 - 0) + 1);
                tableroAux[fila][columna] = new Autito(direccion, this.obtenerColorAleatorio(), fila, columna);
            }

            esTableroValido = esValido(tableroAux);
        }

        return tableroAux;

        // generar autos con sus posiciones, direccion y color
        // verificar si el tablero es válido
        // si no es válido, repetir hasta que sea
    }

    public void mostrarTablero() {
        int dimension = this.getDimension();
        Autito[][] tablero = this.getTablero();

        // Imprimir la primera fila con números
        System.out.print("    ");
        for (int j = 1; j <= dimension; j++) {
            if (j == 1) {
                System.out.print(j + "   ");
            } else {
                System.out.print("  " + j + "  ");
            }
        }
        System.out.println();

        // Imprimir líneas superiores
        System.out.print("  +");
        for (int j = 0; j < dimension; j++) {
            System.out.print("----+");
        }
        System.out.println();

        for (int i = 0; i < dimension; i++) {
            // Imprimir las líneas de la celda
            for (int l = 0; l < 4; l++) {
                if (l == 1) {
                    // Imprimir la letra correspondiente a la fila en la segunda línea
                    System.out.print((char) ('A' + i) + " ");
                } else {
                    System.out.print("  ");
                }

                System.out.print("|");
                for (int j = 0; j < dimension; j++) {
                    String[] representacion;
                    if (tablero[i][j] == null) {
                        representacion = new String[]{"    ", "    ", "    ", "    "};
                    } else {
                        representacion = tablero[i][j].renderizar();
                    }
                    System.out.print(representacion[l] + "|");
                }
                System.out.println();
            }

            // Imprimir líneas inferiores
            System.out.print("  +");
            for (int j = 0; j < dimension; j++) {
                System.out.print("----+");
            }
            System.out.println();
        }
    }

    public Autito[][] getTablero() {
        return this.tablero;
    }

    public void setTablero(Autito[][] tablero) {
        this.tablero = tablero;
    }

    public char[][] crearTableroPredefinido() {
        int dim = 5;
        char[][] tablero = new char[5][5];
        this.setDimension(dim);

        return tablero;
    }

    /*public char[][] crearTableroConfigurado() {

    }*/
    public boolean esValido(Autito[][] tablero) {
        return true;
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

    public Color obtenerColorAleatorio() {
        Random random = new Random();
        int indice = random.nextInt(this.getListaColores().size());
        return this.getListaColores().get(indice);
    }
}
