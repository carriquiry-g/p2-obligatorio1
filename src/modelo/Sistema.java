//Darian Saldaña 230846
//Gaston Carriquiry 230498
package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import obligatorio1.Consola;

public class Sistema {

    private ArrayList<Jugador> listaJugadores;
    private Partida partidaActual;

    public Sistema() {
        listaJugadores = new ArrayList<>();
        this.agregarJugador(new Jugador("Juan", 25, "el_pro"));
        this.agregarJugador(new Jugador("Ana", 30, "mvp_queen"));
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
        this.getListaJugadores().add(jugador);
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

    public Jugador registrarJugador() {
        Consola.throwImportante("Registrando nuevo jugador");

        int edad = -1;

        String nombre = Consola.pedirDatoString("Ingrese su nombre: ");

        while (edad < 0) { //solo uso while en edad porque es el unico que puede tener error por tipado
            edad = Consola.pedirDatoNumerico("Ingrese su edad: ");
            if (edad < 0) {
                Consola.throwError("Error: La edad no puede ser negativa. Por favor ingrese una edad valida.");
            }
        }

        String alias = Consola.pedirDatoString("Ingrese su alias: ");

        boolean esUnico = !this.verificarUnicidadAlias(alias);
        while (!esUnico) {
            Consola.throwError("Error: el alias " + alias + " ya fue ingresado. Ingrese uno distinto.");
            alias = Consola.pedirDatoString("Ingrese un nuevo alias: ");
            esUnico = !this.verificarUnicidadAlias(alias);
        }

        Jugador nuevoJugador = new Jugador(nombre, edad, alias);
        this.agregarJugador(nuevoJugador);
        Consola.throwImportante("Jugador " + nuevoJugador + " registrado con exito.");
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

    public void inicializarPartida() {
        Jugador j1;
        Jugador j2;
        if (this.getListaJugadores().isEmpty()) {
            Consola.throwImportante("No hay jugadores registrados. A continuacion registre ambos jugadores.");
            j1 = registrarJugador();
            j2 = registrarJugador();
        } else if (this.getListaJugadores().size() == 1) {
            Consola.throwImportante("El unico jugador registrado es " + this.getListaJugadores().get(0) + ", por lo que sera asignado como jugador 1.");
            j1 = this.getListaJugadores().get(0);
            Consola.throwImportante("El jugador 2 sera registrado a continuacion.");
            j2 = this.registrarJugador();
        } else {
            j1 = this.seleccionarJugador(1, null);
            j2 = this.seleccionarJugador(2, j1);
        }

        Partida partida = new Partida(j1, j2);
        this.setPartidaActual(partida);
    }

    public Jugador seleccionarJugador(int numJugador, Jugador jugadorSeleccionado) {
        boolean seDefinioJugador = false;
        Jugador jugadorElegido = null;

        Consola.throwImportante("Seleccionando al jugador " + numJugador);
        Consola.throwInfo("Lista de jugadores: ");
        for (int i = 0; i < this.getListaJugadores().size(); i++) {
            Consola.throwInfo("\t" + (i + 1) + ") " + this.getListaJugadores().get(i));
        }

        while (!seDefinioJugador) {
            try {
                int numElegido = Consola.pedirDatoNumerico("Ingrese el jugador a seleccionar: ");
                jugadorElegido = this.getListaJugadores().get(numElegido - 1);
                if (jugadorSeleccionado == null || !jugadorElegido.equals(jugadorSeleccionado)) {
                    seDefinioJugador = true;
                } else {
                    Consola.throwImportante("El jugador ingresado ya fue seleccionado, ingrese otro: ");
                }
            } catch (IndexOutOfBoundsException e) {
                Consola.throwError("Error: el numero ingresado no esta en la lista. Ingrese un numero entre 1 y " + (this.getListaJugadores().size()) + ".");
            }
        }
        Consola.throwImportante("Jugador seleccionado: " + jugadorElegido);
        return jugadorElegido;
    }
}
