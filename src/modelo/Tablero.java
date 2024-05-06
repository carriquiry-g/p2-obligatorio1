package modelo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Tablero {

    private Scanner scanner;
    private Autito[][] tablero;
    private int dimension;
    private ArrayList<Autito> listaAutos = new ArrayList<>();
    private ArrayList<Color> listaColores = new ArrayList<>();

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
                this.setTablero(this.crearTableroConfigurado());
                break;
            case 3:
                //Predefinido
                this.setTablero(this.crearTableroPredefinido());
                break;
            default:
                break;
        }
    }

    public void agregarAuto(Autito auto) {
        this.listaAutos.add(auto);
    }

    public void eliminarAuto(Autito autoEliminar) {
        Iterator<Autito> iterator = this.getListaAutos().iterator();
        while (iterator.hasNext()) {
            Autito auto = iterator.next();
            if (auto.toString().equalsIgnoreCase(autoEliminar.toString())) {
                this.getListaAutos().remove(autoEliminar);
            }
        }
    }

    public void renderizar() {
        int dimension = this.getDimension();
        Autito[][] tablero = this.getTablero();

        // Imprimir la primera fila con números
        System.out.print("    ");
        for (int j = 1; j <= dimension; j++) {
            if (j == 1) {
                System.out.print(j + "  ");
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
                Autito nuevoAuto = new Autito(direccion, this.obtenerColorAleatorio(), fila, columna);
                this.agregarAuto(nuevoAuto);
                tableroAux[fila][columna] = nuevoAuto;
            }

            esTableroValido = esValido(tableroAux);
        }

        return tableroAux;

        // generar autos con sus posiciones, direccion y color
        // verificar si el tablero es válido
        // si no es válido, repetir hasta que sea
    }

    public Autito[][] crearTableroPredefinido() {
        int dim = 5;
        Autito[][] tableroAux = new Autito[dim][dim];
        this.setDimension(dim);

        Autito[] autos = {
            new Autito(2, new Color(8), 0, 0),
            new Autito(3, new Color(4), 0, 1),
            new Autito(2, new Color(5), 0, 3),
            new Autito(0, new Color(8), 0, 4),
            new Autito(2, new Color(4), 3, 4),
            new Autito(0, new Color(8), 4, 0),
            new Autito(1, new Color(4), 4, 3),
            new Autito(3, new Color(2), 4, 4)
        };
        for (int i = 0; i < autos.length; i++) {
            this.agregarAuto(autos[i]);
            tableroAux[autos[i].getFila()][autos[i].getColumna()] = autos[i];
        }

        return tableroAux;
    }

    public Autito[][] crearTableroConfigurado() {
        //TODO: logica para generar tablero configurado
        //1) preguntar dimension
        //2) preguntar cantidad de autos
        //3) preguntar posiciones de todos los autos
        //4) crear tablero
        Random random = new Random();

        int dim = 0;
        while (dim != 5 && dim != 6 && dim != 7) {
            System.out.print("Ingrese la dimension deseada para el tablero (5, 6 o 7): ");
            try {
                dim = scanner.nextInt();
                scanner.nextLine();

                if (dim != 5 && dim != 6 && dim != 7) {
                    System.out.println("Error: la dimension del tablero debe ser 5, 6 o 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: la dimension del tablero debe ser un numero entero (5, 6 o 7).");
                scanner.nextLine();
            }
        }

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
        this.setTablero(tableroAux);

        while (!esTableroValido) {
            for (int i = 0; i < n; i++) {
                int fila = 0, columna = 0, direccion = 0;

                do {
                    this.renderizar();
                    System.out.println("Ingrese las coordenadas y direccion del auto " + (i + 1) + " en el formato 'A10', donde 'A' es la fila, '1' es la columna y '0' es la direccion.");
                    System.out.println("Direcciones: 0=Arriba, 1=Derecha, 2=Abajo, 3=Izquierda");
                    System.out.print("Ingrese el auto " + (i + 1) + ": ");
                    String coordenadas = scanner.nextLine().toUpperCase();
                    if (coordenadas.length() <= 3) {
                        fila = coordenadas.charAt(0) - 'A';
                        columna = Character.getNumericValue(coordenadas.charAt(1)) - 1;
                        direccion = Character.getNumericValue(coordenadas.charAt(2));
                    } else {
                        System.out.println("Formato de coordenadas invalido.");
                    }
                    
                    if(tableroAux[fila][columna] != null && coordenadas.length() <= 3){
                        System.out.println("Ya hay un autito en esas coordenadas. Ingrese coordenadas distintas.");
                    }
                } while (tableroAux[fila][columna] != null);

                Autito nuevoAuto = new Autito(direccion, this.obtenerColorAleatorio(), fila, columna);
                this.agregarAuto(nuevoAuto);
                tableroAux[fila][columna] = nuevoAuto;
            }

            esTableroValido = esValido(tableroAux);

            if (!esTableroValido) {
                System.out.println("El tablero ingresado no es valido. No hay jugadas posibles. Ingrese nuevamente las coordenadas de los autitos.");
            }
        }

        return tableroAux;
    }

    public boolean esValido(Autito[][] tablero) {
        //TODO: implementar logica para detectar si es valido
        //chequear que para al menos 1 auto, haya un movimiento posible
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
