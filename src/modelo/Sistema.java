package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class Sistema {

    private ArrayList<Jugador> listaJugadores;
    private Scanner scanner;
    private Partida partidaActual;

    public Sistema() {
        listaJugadores = new ArrayList<>();
        scanner = new Scanner(System.in);
        this.agregarJugador(new Jugador("asfsa", 523, "pussy_destroyer"));
        this.agregarJugador(new Jugador("Juan", 25, "el_pro"));
        this.agregarJugador(new Jugador("Ana", 30, "mvp_queen"));
        this.agregarJugador(new Jugador("Pedro", 28, "fast_racer"));
        this.agregarJugador(new Jugador("Maria", 22, "snipe_girl"));
        this.agregarJugador(new Jugador("Carlos", 35, "tank_master"));
        this.agregarJugador(new Jugador("Luis", 40, "legend_hunter"));
        this.agregarJugador(new Jugador("Elena", 27, "shadow_assassin"));
        this.agregarJugador(new Jugador("Miguel", 32, "sniper_king"));
        this.agregarJugador(new Jugador("Lucia", 24, "speed_demon"));
        this.agregarJugador(new Jugador("Roberto", 29, "strong_warrior"));
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public Partida getPartidaActual() {
        return partidaActual;
    }

    public void setPartidaActual(Partida partidaActual) {
        this.partidaActual = partidaActual;
    }

    public void agregarJugador(Jugador jugador) {
        listaJugadores.add(jugador);
    }

    public void eliminarJugador(String alias) {
        Iterator<Jugador> iterator = this.getListaJugadores().iterator();
        while (iterator.hasNext()) {
            Jugador jugador = iterator.next();
            if (jugador.getAlias().equalsIgnoreCase(alias)) {
                this.getListaJugadores().remove(jugador);
            }
        }
    }

    public void mostrarRanking() {
        this.getListaJugadores().sort(Comparator
                .comparingInt(Jugador::getPuntaje).reversed()
                .thenComparing(Jugador::getAlias));

        String titulo = "RANKING";
        int anchoTotal = 75;
        String formato = "| %-12s | %-8d | %-8d | %-8d | %-12d | %-8d |%n";
        System.out.println("-".repeat(anchoTotal));
        System.out.printf("%" + (anchoTotal / 2 + titulo.length() / 2) + "s%n", titulo);
        System.out.println("-".repeat(anchoTotal));
        System.out.format("| %-12s | %-8s | %-8s | %-8s | %-12s | %-8s |%n", "ALIAS", "PARTIDAS", "GANADAS", "PERDIDAS", "ABANDONADAS", "PUNTAJE");
        System.out.println("-".repeat(anchoTotal));

        for (Jugador jugador : this.getListaJugadores()) {
            String aliasTruncado = this.truncarAlias(jugador.getAlias());
            System.out.format(formato,
                    aliasTruncado,
                    jugador.getPartidasGanadas() + jugador.getPartidasPerdidas() + jugador.getPartidasAbandonadas(),
                    jugador.getPartidasGanadas(),
                    jugador.getPartidasPerdidas(),
                    jugador.getPartidasAbandonadas(),
                    jugador.getPuntaje());
        }
        System.out.println("-".repeat(anchoTotal));
    }

    public String truncarAlias(String alias) {
        String aliasFormateado = alias;
        if (alias.length() > 9) {
            aliasFormateado = alias.substring(0, 9) + "...";
        }
        return aliasFormateado;
    }

    public Jugador registrarJugador() {
        System.out.println("-- Registrando nuevo jugador --");

        String nombre;
        int edad = -1;
        String alias;

        System.out.print("Ingrese su nombre: ");
        nombre = scanner.nextLine();

        while (edad < 0) { //solo uso while en edad porque es el unico que puede tener error por tipado
            try {
                System.out.print("Ingrese su edad: ");
                edad = scanner.nextInt();
                scanner.nextLine();
                if (edad < 0) {
                    System.out.println("Error: La edad no puede ser negativa. Por favor ingrese una edad valida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: formato de edad incorrecto. Ingrese un numero entero.");
                scanner.nextLine();
            }
        }
        System.out.print("Ingrese su alias: ");
        alias = scanner.nextLine();

        boolean esUnico = !this.verificarUnicidadAlias(alias);
        while (!esUnico) {
            System.out.println("El alias " + alias + " ya fue ingresado. Ingrese uno distinto.");
            System.out.print("Ingrese un nuevo alias: ");
            alias = scanner.nextLine();
            esUnico = !this.verificarUnicidadAlias(alias);
        }

        Jugador nuevoJugador = new Jugador(nombre, edad, alias);
        this.agregarJugador(nuevoJugador);
        System.out.println("Jugador " + nuevoJugador + " registrado con exito.");
        return nuevoJugador;
    }

    public boolean verificarUnicidadAlias(String alias) {
        boolean aliasExiste = false;
        for (Jugador jugador : this.getListaJugadores()) {
            if (jugador.getAlias().equalsIgnoreCase(alias)) {
                aliasExiste = true;
            }
        }

        return aliasExiste;
    }

    public void mostrarMenu() {
        System.out.println(" _______________________________________________________________________________________________________________________");
        System.out.println("|\t     _   _   _ _____ ___ _____ ___  ____     ____ _   _  ___   ____    _    ____   ___  ____  _____ ____  \t|");
        System.out.println("|\t    / \\ | | | |_   _|_ _|_   _/ _ \\/ ___|   / ___| | | |/ _ \\ / ___|  / \\  |  _ \\ / _ \\|  _ \\| ____/ ___| \t|");
        System.out.println("|\t   / _ \\| | | | | |  | |  | || | | \\___ \\  | |   | |_| | | | | |     / _ \\ | | | | | | | |_) |  _| \\___ \\ \t|");
        System.out.println("|\t  / ___ \\ |_| | | |  | |  | || |_| |___) | | |___|  _  | |_| | |___ / ___ \\| |_| | |_| |  _ <| |___ ___) |\t|");
        System.out.println("|\t /_/   \\_\\___/  |_| |___| |_| \\___/|____/   \\____|_| |_|\\___/ \\____/_/   \\_\\____/ \\___/|_| \\_\\_____|____/ \t|");
        System.out.println("|_______________________________________________________________________________________________________________________|");
        System.out.println("");

        System.out.println("Seleccione una opcion para comenzar:");
        System.out.println("a) Jugar");
        System.out.println("b) Registrar un nuevo jugador");
        System.out.println("c) Ver ranking");
        System.out.println("_".repeat(40));
        System.out.print("Ingrese la opcion: ");

        String input = scanner.nextLine();
        switch (input.charAt(0)) {
            case 'a':
                this.inicializarJuego();
                break;
            case 'b':
                this.registrarJugador();
                break;
            case 'c':
                this.mostrarRanking();
                break;
            default:
                break;
        }
    }

    public void inicializarJuego() {
        Jugador j1;
        Jugador j2;
        if (this.getListaJugadores().isEmpty()) {
            System.out.println("No hay jugadores registrados. A continuacion registre ambos jugadores.");
            j1 = registrarJugador();
            j2 = registrarJugador();
        } else if (this.getListaJugadores().size() == 1) {
            System.out.println("El unico jugador registrado es " + this.getListaJugadores().get(0) + ", por lo que sera asignado como jugador 1.");
            j1 = this.getListaJugadores().get(0);
            System.out.println("El jugador 2 sera registrado a continuacion.");
            j2 = this.registrarJugador();
        } else {
            j1 = this.seleccionarJugador(1, null);
            j2 = this.seleccionarJugador(2, j1);
        }

        Tablero tablero = seleccionarTablero();
        System.out.println("Tablero a jugar:");
        tablero.mostrarTablero();
        Partida partida = new Partida(j1, j2, tablero);
        this.setPartidaActual(partida);
    }

    public Jugador seleccionarJugador(int numJugador, Jugador jugadorSeleccionado) {
        boolean seDefinioJugador = false;
        Jugador jugadorElegido = new Jugador();

        System.out.println("-- Seleccionado al jugador " + numJugador + " --");
        System.out.println("Lista de jugadores: ");
        for (int i = 0; i < this.getListaJugadores().size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + this.getListaJugadores().get(i));
        }

        System.out.print("Numero de jugador a seleccionar: ");
        while (!seDefinioJugador) {
            try {
                int numElegido = scanner.nextInt();
                scanner.nextLine();
                jugadorElegido = this.getListaJugadores().get(numElegido - 1);
                if (jugadorSeleccionado == null || !jugadorElegido.equals(jugadorSeleccionado)) {
                    seDefinioJugador = true;
                } else {
                    System.out.print("Ese jugador ya fue seleccionado, ingrese otro: ");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: el numero ingresado no esta en la lista. Ingrese un numero entre 1 y " + (this.getListaJugadores().size() + 1) + ".");
            } catch (InputMismatchException e) {
                System.out.println("Error: formato de edad incorrecto. Ingrese un numero entero.");
                scanner.nextLine();
            }
        }
        System.out.println("Jugador seleccionado: " + jugadorElegido);
        return jugadorElegido;
    }

    public Tablero seleccionarTablero() {
        int opcion = -1;
        System.out.println("Tipos de tableros:");
        System.out.println("""
                           \t 1) Al azar 
                           \t 2) Configuracion manual
                           \t 3) Predefinido""");
        while (opcion != 1 && opcion != 2 && opcion != 3) {
            System.out.print("Ingrese el tipo de tablero a utilizar: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion != 1 && opcion != 2 && opcion != 3) {
                System.out.println("Error: el numero ingresado no forma parte de las opciones. Ingrese 1, 2 o 3.");
            }
        }
        ArrayList<Color> colores = determinarColores();
        return new Tablero(opcion, colores);
    }

    public ArrayList<Color> determinarColores() {
        ArrayList<Color> colores = new ArrayList<>(6);
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            colores.add(new Color(random.nextInt(8)));
        }

        return colores;
    }
}
