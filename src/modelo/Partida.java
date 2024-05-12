//Darian Saldaña 230846
//Gaston Carriquiry 230498
package modelo;

import java.util.ArrayList;
import java.util.Random;
import obligatorio1.Consola;

public class Partida {

    private Jugador j1;
    private Jugador j2;
    private Tablero tablero;
    private Jugador jugadorActual;

    public Partida(Jugador j1, Jugador j2) {
        this.setJugador1(j1);
        this.setJugador2(j2);
        this.setJugadorActual(j1);
        this.seleccionarTablero();

        Consola.pedirDatoString("Presione Enter para empezar la partida.");
        this.empezarPartida();
    }

    public void seleccionarTablero() {
        int opcion = -1;
        Consola.throwImportante("Tipos de tableros:");
        Consola.throwInfo("""
                           \t 1) Al azar 
                           \t 2) Configuracion manual
                           \t 3) Predefinido""");
        while (opcion != 1 && opcion != 2 && opcion != 3) {
            opcion = Consola.pedirDatoNumerico("Ingrese el tipo de tablero a utilizar: ");
            if (opcion != 1 && opcion != 2 && opcion != 3) {
                Consola.throwError("Error: el numero ingresado no forma parte de las opciones. Ingrese 1, 2 o 3.");
            }
        }
        ArrayList<Color> colores = this.determinarColores();
        this.setTablero(new Tablero(opcion, colores));
    }

    public ArrayList<Color> determinarColores() {
        ArrayList<Color> colores = new ArrayList<>(6);
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            colores.add(new Color(random.nextInt(8)));
        }

        return colores;
    }

    public void empezarPartida() {
        Consola.mostrarInstruccionesPartida();
        boolean hayGanador = false;
        boolean jugadorAbandona = false;
        Consola.throwImportante("Turno: " + this.getJugadorActual());
        String input;
        while (!hayGanador && !jugadorAbandona) {
            input = Consola.pedirDatoString("Ingrese su jugada: ").toUpperCase();
            if (input.isEmpty()) {
                Consola.throwError("Debe ingresar una jugada.");
            } else {
                switch (input.charAt(0)) {
                    case 'S':
                        Consola.throwInfo("Autitos seleccionables:");
                        Consola.listar(this.getTablero().obtenerPosiblesMovimientos());
                        this.getJugadorActual().modificarPuntaje(-1);
                        break;
                    case 'X':
                        jugadorAbandona = true;
                        break;
                    case 'R':
                        this.getTablero().rotar();
                        this.cambiarTurno();
                        break;
                    default:
                        if (input.length() == 2) {
                            if (this.procesarJugada(input)) {
                                System.out.println("Ultimo turno");
                                System.out.println(jugadorActual);
                                this.cambiarTurno();
                            } else {
                                Consola.throwError("Las coordenadas ingresadas no son validas, ingrese otras.");
                            }
                        } else {
                            Consola.throwError("No se reconoce la opcion seleccionada.");
                            Consola.mostrarInstruccionesPartida();
                        }
                        break;
                }
            }

            if (!jugadorAbandona) {
                hayGanador = this.hayGanador();
            }
        }

        this.terminarPartida(jugadorAbandona);
    }

    public void terminarPartida(boolean huboAbandono) {
        Jugador ganador;
        Jugador perdedor;
        int puntajePerdedor;
        if (huboAbandono) {
            perdedor = this.getJugadorActual();
            ganador = perdedor.equals(this.getJugador1()) ? this.getJugador2() : this.getJugador1();
            perdedor.sumarPartidaAbandonada();
            puntajePerdedor = -5;
        } else {
            ganador = this.getJugadorActual();
            perdedor = ganador.equals(this.getJugador1()) ? this.getJugador2() : this.getJugador1();
            perdedor.sumarPartidaPerdida();
            puntajePerdedor = -2;
        }

        ganador.modificarPuntaje(10);
        ganador.sumarPartidaGanada();
        ganador.sumarPartidaJugada();

        perdedor.modificarPuntaje(puntajePerdedor);
        perdedor.sumarPartidaJugada();
        
        Consola.informarResultadoPartida(ganador, perdedor, huboAbandono);
    }

    public boolean procesarJugada(String input) {
        int fila = input.charAt(0) - 'A';
        int columna = Character.getNumericValue(input.charAt(1)) - 1;
        Tablero tab = this.getTablero();
        boolean coordenadasValidas = tab.obtenerMovimientoAuto(null, fila, columna) != null;
        if (coordenadasValidas) {
            tab.procesarChoque(fila, columna);
        }

        return coordenadasValidas;
    }

    public boolean hayGanador() {
        return this.getTablero().obtenerPosiblesMovimientos().isEmpty();
    }

    public void cambiarTurno() {
        if (!this.hayGanador()) {
            if (this.getJugadorActual().equals(j1)) {
                this.setJugadorActual(j2);
            } else {
                this.setJugadorActual(j1);
            }
        }
        Consola.renderizarTablero(this.getTablero());
        if (!this.hayGanador()) {
            Consola.throwImportante("Turno: " + this.getJugadorActual());
        }
    }

    public Jugador getJugadorActual() {
        return this.jugadorActual;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public Jugador getJugador1() {
        return this.j1;
    }

    public void setJugador1(Jugador jugador1) {
        this.j1 = jugador1;
    }

    public Jugador getJugador2() {
        return this.j2;
    }

    public void setJugador2(Jugador jugador2) {
        this.j2 = jugador2;
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }
}
