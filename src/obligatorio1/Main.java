//Darian Saldaña 230846
//Gaston Carriquiry 230498

package obligatorio1;

import modelo.Sistema;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        inicializarJuego(sistema);

    }

    public static void inicializarJuego(Sistema sistema) {
        char opcionElegida = '0';

        while (opcionElegida != 'a' && opcionElegida != 'b' && opcionElegida != 'c' && opcionElegida != 'd') {
            opcionElegida = Consola.mostrarMenuPrincipal();
        }
        switch (opcionElegida) {
            case 'a':
                sistema.inicializarPartida();
                inicializarJuego(sistema);
                break;
            case 'b':
                sistema.registrarJugador();
                inicializarJuego(sistema);
                break;
            case 'c':
                Consola.mostrarRanking(sistema.getListaJugadores());
                inicializarJuego(sistema);
                break;
            case 'd':
                Consola.throwImportante("Juego finalizado");
                break;
            default:
                inicializarJuego(sistema);
                break;
        }
    }
}
