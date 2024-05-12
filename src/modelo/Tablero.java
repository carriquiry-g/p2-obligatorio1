//Darian Saldaña 230846
//Gaston Carriquiry 230498
package modelo;

import java.util.ArrayList;
import java.util.Random;
import obligatorio1.Consola;

public class Tablero {

    private Autito[][] configuracion;
    private int dimension;
    private ArrayList<Autito> listaAutos = new ArrayList<>();
    private ArrayList<Color> listaColores = new ArrayList<>();
    private static int[][] Direcciones = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public Tablero(int tipoTablero, ArrayList<Color> colores) {
        this.setListaColores(colores);

        switch (tipoTablero) {
            case 1:
                //Al azar
                this.setConfiguracion(this.crearTableroAlAzar());
                break;
            case 2:
                //Configurado por el usuario
                this.setConfiguracion(this.crearTableroConfigurado());
                break;
            case 3:
                //Predefinido
                this.setConfiguracion(this.crearTableroPredefinido());
                break;
            default:
                this.setConfiguracion(this.crearTableroPredefinido());
                break;
        }

        Consola.throwImportante("Tablero a jugar: ");
        Consola.renderizarTablero(this);
    }

    public void agregarAuto(Autito auto) {
        this.listaAutos.add(auto);
    }

    public void eliminarAutoChocado(Autito autoEliminar) {
        this.getListaAutos().removeIf(auto -> auto.toString().equalsIgnoreCase(autoEliminar.toString()));

        //* El codigo comentado da error: ConcurrentModificationException.
        /*Iterator<Autito> iterator = this.getListaAutos().iterator();
        while (iterator.hasNext()) {
            Autito auto = iterator.next();
            if (auto.toString().equalsIgnoreCase(autoEliminar.toString())) {
                this.getListaAutos().remove(autoEliminar);
            }
        }*/
    }

    public Autito[][] getConfiguracion() {
        return this.configuracion;
    }

    public void setConfiguracion(Autito[][] configuracion) {
        this.configuracion = configuracion;
    }

    public Autito[][] crearTableroAlAzar() {
        Random random = new Random();

        int dim = this.obtenerDimension();
        int n = obtenerCantidadAutos();
        this.setDimension(dim);

        Autito[][] aux = new Autito[dim][dim];

        boolean esTableroValido = false;
        while (!esTableroValido) {
            for (int i = 0; i < n; i++) {
                int fila, columna;

                do {
                    fila = random.nextInt(dim);
                    columna = random.nextInt(dim);
                } while (aux[fila][columna] != null);

                int direccion = random.nextInt((3 - 0) + 1);
                Autito nuevoAuto = new Autito(direccion, this.obtenerColorAleatorio(), fila, columna);
                this.agregarAuto(nuevoAuto);
                aux[fila][columna] = nuevoAuto;
            }

            esTableroValido = validarConfiguracion(aux);

            if (!esTableroValido) {
                aux = new Autito[dim][dim];
            }
        }

        return aux;
    }

    public Autito[][] crearTableroPredefinido() {
        int dim = 5;
        Autito[][] aux = new Autito[dim][dim];
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
            aux[autos[i].getFila()][autos[i].getColumna()] = autos[i];
        }

        return aux;
    }

    public Autito[][] crearTableroConfigurado() {
        int dim = this.obtenerDimension();
        this.setDimension(dim);
        int n = obtenerCantidadAutos();

        Autito[][] aux = new Autito[dim][dim];
        this.setConfiguracion(aux);

        boolean esTableroValido = false;
        while (!esTableroValido) {
            for (int i = 0; i < n; i++) {
                int fila = 0, columna = 0, direccion = 0;

                try {
                    do {
                        Consola.renderizarTablero(this);
                        Consola.throwImportante("Ingrese las coordenadas y direccion del auto " + (i + 1) + " en el formato 'A10', donde 'A' es la fila, '1' es la columna y '0' es la direccion.");
                        Consola.throwImportante("Direcciones: 0=Arriba, 1=Derecha, 2=Abajo, 3=Izquierda");

                        String coordenadas = Consola.pedirDatoString("Ingrese el auto " + (i + 1) + ": ").toUpperCase();
                        if (coordenadas.length() <= 3) {
                            fila = coordenadas.charAt(0) - 'A';
                            columna = Character.getNumericValue(coordenadas.charAt(1)) - 1;
                            direccion = Character.getNumericValue(coordenadas.charAt(2));
                        } else {
                            Consola.throwError("Error: formato de coordenadas invalido.");
                        }

                        if (direccion > 3) {
                            throw new Exception("Error: la direccion indicada no es correcta. Ingrese un valor entero entre 0 y 3.");
                        }

                        if (aux[fila][columna] != null && coordenadas.length() <= 3) {
                            Consola.throwError("Ya hay un autito en esas coordenadas. Ingrese coordenadas distintas.");
                        }
                    } while (aux[fila][columna] != null);

                    Autito nuevoAuto = new Autito(direccion, this.obtenerColorAleatorio(), fila, columna);
                    this.agregarAuto(nuevoAuto);
                    aux[fila][columna] = nuevoAuto;
                } catch (ArrayIndexOutOfBoundsException e) {
                    Consola.throwError("Error: las coordenadas no son validas. Corrobore que la fila y/o columna indicada este dentro del rango valido.");
                    i--;
                } catch (Exception e) {
                    Consola.throwError(e.getMessage());
                    i--;
                }
            }

            esTableroValido = validarConfiguracion(aux);

            if (!esTableroValido) {
                Consola.throwError("El tablero ingresado no es valido. No hay jugadas posibles. Ingrese nuevamente las coordenadas de los autitos.");
                aux = new Autito[dim][dim];
                this.setConfiguracion(aux);
            }
        }

        return aux;
    }

    public int obtenerDimension() {
        int dim = -1;
        while (dim != 5 && dim != 6 && dim != 7) {
            dim = Consola.pedirDatoNumerico("Ingrese la dimension deseada para el tablero (5, 6 o 7): ");

            if (dim != 5 && dim != 6 && dim != 7) {
                Consola.throwError("Error: la dimension del tablero debe ser 5, 6 o 7.");
            }
        }

        return dim;
    }

    public int obtenerCantidadAutos() {
        int n = 0;
        while (n < 3 || n > 12) {
            n = Consola.pedirDatoNumerico("Ingrese la cantidad de autos deseados para el tablero (entre 3 y 12): ");

            if (n < 3 || n > 12) {
                Consola.throwError("Error: la cantidad de autos debe ser entre 3 y 12.");
            }
        }

        return n;
    }

    public Autito obtenerMovimientoAuto(Autito[][] configuracion, int i, int j) {
        if (configuracion == null) {
            configuracion = this.getConfiguracion();
        }
        boolean tieneMovimientoPosible = false;
        Autito autoChocar = null;
        int n = configuracion.length;
        Autito auto = configuracion[i][j];

        if (auto != null) {
            for (int rotacion = 1; rotacion <= 3; rotacion++) {
                int nuevaDireccion = auto.rotar(rotacion);
                int[] movimiento = this.getDirecciones()[nuevaDireccion];

                int fila = i + movimiento[0];
                int columna = j + movimiento[1];
                while (fila >= 0 && fila < n && columna >= 0 && columna < n && !tieneMovimientoPosible) {
                    if (configuracion[fila][columna] != null) {
                        tieneMovimientoPosible = true;
                        autoChocar = configuracion[fila][columna];
                    }
                    fila += movimiento[0];
                    columna += movimiento[1];
                }
            }
        }
        return autoChocar;
    }

    public boolean validarConfiguracion(Autito[][] configuracion) {
        boolean esValidoTablero = false;
        for (int i = 0; i < configuracion.length; i++) {
            for (int j = 0; j < configuracion[i].length; j++) {
                if (!esValidoTablero && configuracion[i][j] != null && obtenerMovimientoAuto(configuracion, i, j) != null) {
                    esValidoTablero = true;
                }
            }
        }
        return esValidoTablero;
    }

    public ArrayList<String> obtenerPosiblesMovimientos() {
        Autito[][] config = this.getConfiguracion();
        ArrayList<String> posiblesMovimientos = new ArrayList<>();
        for (int i = 0; i < config.length; i++) {
            for (int j = 0; j < config[i].length; j++) {
                if (config[i][j] != null && obtenerMovimientoAuto(config, i, j) != null) {
                    Autito auto = config[i][j];
                    posiblesMovimientos.add(auto.toString());
                }
            }
        }
        return posiblesMovimientos;
    }

    public void procesarChoque(int fila, int columna) {
        Autito autoSeleccionado = this.getConfiguracion()[fila][columna];
        Autito autoChocado = this.obtenerMovimientoAuto(null, fila, columna);
        this.eliminarAutoChocado(autoChocado);
        autoSeleccionado.chocar(autoChocado.getFila(), autoChocado.getColumna());

        this.getConfiguracion()[autoChocado.getFila()][autoChocado.getColumna()] = autoSeleccionado;
        this.getConfiguracion()[fila][columna] = null;
    }

    public void rotar() {
        Autito[][] configOriginal = this.getConfiguracion();
        int dim = this.getDimension();
        Autito[][] configRotada = new Autito[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Autito auto = configOriginal[i][j];
                if (auto != null) {
                    auto.setDireccion(auto.rotar(1));
                }
                configRotada[j][dim - 1 - i] = auto;
            }
        }

        this.setConfiguracion(configRotada);
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

    public ArrayList<Color> getListaColores() {
        return this.listaColores;
    }

    public int[][] getDirecciones() {
        return Direcciones;
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
