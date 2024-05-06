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
        int dim = this.getDimension();
        Autito[][] tab = this.getTablero();

        // Imprimir la primera fila con números
        System.out.print("    ");
        for (int j = 1; j <= dim; j++) {
            if (j == 1) {
                System.out.print(j + "  ");
            } else {
                System.out.print("  " + j + "  ");
            }
        }
        System.out.println();

        // Imprimir líneas superiores
        System.out.print("  +");
        for (int j = 0; j < dim; j++) {
            System.out.print("----+");
        }
        System.out.println();

        for (int i = 0; i < dim; i++) {
            // Imprimir las líneas de la celda
            for (int l = 0; l < 4; l++) {
                if (l == 1) {
                    // Imprimir la letra correspondiente a la fila en la segunda línea
                    System.out.print((char) ('A' + i) + " ");
                } else {
                    System.out.print("  ");
                }

                System.out.print("|");
                for (int j = 0; j < dim; j++) {
                    String[] representacion;
                    if (tab[i][j] == null) {
                        representacion = new String[]{"    ", "    ", "    ", "    "};
                    } else {
                        representacion = tab[i][j].renderizar();
                    }
                    System.out.print(representacion[l] + "|");
                }
                System.out.println();
            }

            // Imprimir líneas inferiores
            System.out.print("  +");
            for (int j = 0; j < dim; j++) {
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

            if (!esTableroValido) {
                tableroAux = new Autito[dim][dim];
            }
        }

        return tableroAux;
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

                try {
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
                            System.out.println("Error: formato de coordenadas invalido.");
                        }

                        if (direccion > 3) {
                            throw new Exception("Error: la direccion indicada no es correcta. Ingrese un valor entero entre 0 y 3.");
                        }

                        if (tableroAux[fila][columna] != null && coordenadas.length() <= 3) {
                            System.out.println("Ya hay un autito en esas coordenadas. Ingrese coordenadas distintas.");
                        }
                    } while (tableroAux[fila][columna] != null);

                    Autito nuevoAuto = new Autito(direccion, this.obtenerColorAleatorio(), fila, columna);
                    this.agregarAuto(nuevoAuto);
                    tableroAux[fila][columna] = nuevoAuto;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: las coordenadas no son validas. Corrobore que la fila y/o columna indicada este dentro del rango valido.");
                    i--;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    i--;
                }
            }

            esTableroValido = esValido(tableroAux);

            if (!esTableroValido) {
                System.out.println("El tablero ingresado no es valido. No hay jugadas posibles. Ingrese nuevamente las coordenadas de los autitos.");
                tableroAux = new Autito[dim][dim];
                this.setTablero(tableroAux);
            }
        }

        return tableroAux;
    }

    public boolean autoTieneMovimientoPosible(Autito[][] tablero, int i, int j, int[][] direcciones) {
        boolean esValidoTablero = false;
        int n = tablero.length;
        Autito auto = tablero[i][j];

        for (int rotacion = 1; rotacion <= 3; rotacion++) {
            int nuevaDireccion = (auto.getDireccion() + rotacion) % 4;
            int[] movimiento = direcciones[nuevaDireccion];

            int fila = i + movimiento[0];
            int columna = j + movimiento[1];
            while (fila >= 0 && fila < n && columna >= 0 && columna < n) {
                if (!esValidoTablero && tablero[fila][columna] != null) {
                    esValidoTablero = true;
                }
                fila += movimiento[0];
                columna += movimiento[1];
            }
        }
        return esValidoTablero;
    }

    public boolean esValido(Autito[][] tablero) {
        boolean esValidoTablero = false;
        int[][] direcciones = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (!esValidoTablero && tablero[i][j] != null && autoTieneMovimientoPosible(tablero, i, j, direcciones)) {
                    esValidoTablero = true; // Se encontró al menos un auto con movimiento posible
                }
            }
        }
        return esValidoTablero;
    }

    public ArrayList<String> obtenerPosiblesMovimientos(Autito[][] tablero) {
        ArrayList<String> posiblesMovimientos = new ArrayList<>();
        int[][] direcciones = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] != null && autoTieneMovimientoPosible(tablero, i, j, direcciones)) {
                    Autito auto = tablero[i][j];
                    posiblesMovimientos.add(auto.toString());
                }
            }
        }
        return posiblesMovimientos;
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
