package modelo;

import java.util.ArrayList;
import java.util.Random;
import obligatorio1.Consola;

public class Partida {

    private Jugador j1;
    private Jugador j2;
    private Tablero tablero;

    public Partida(Jugador j1, Jugador j2) {
        this.setJugador1(j1);
        this.setJugador2(j2);
        this.seleccionarTablero();
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
        Consola.pedirDatoString("Presione Enter para empezar la partida.");
        this.empezarPartida();
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

    }

    public void terminarPartida() {

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
