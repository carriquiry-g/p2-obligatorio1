package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Sistema {

    private ArrayList<Jugador> listaJugadores;
    private Scanner scanner;
    private Partida partidaActual;

    public Sistema() {
        listaJugadores = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    public ArrayList<Jugador> getListaJugadores(){
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
        while(iterator.hasNext()){
            Jugador jugador = iterator.next();
            if(jugador.getAlias().equalsIgnoreCase(alias)){
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

    public void registrarJugador() {
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
        System.out.println("Jugador " + alias + " registrado con exito.");
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
    
    public void mostrarMenu(){
        
    }
}
