//Darian Saldaña 230846
//Gaston Carriquiry 230498
package obligatorio1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import modelo.Autito;
import modelo.Jugador;
import modelo.Tablero;

public class Consola {

    public static char mostrarMenuPrincipal() {
        throwInfo(" _______________________________________________________________________________________________________________________");
        throwInfo("|\t     _   _   _ _____ ___ _____ ___  ____     ____ _   _  ___   ____    _    ____   ___  ____  _____ ____  \t|");
        throwInfo("|\t    / \\ | | | |_   _|_ _|_   _/ _ \\/ ___|   / ___| | | |/ _ \\ / ___|  / \\  |  _ \\ / _ \\|  _ \\| ____/ ___| \t|");
        throwInfo("|\t   / _ \\| | | | | |  | |  | || | | \\___ \\  | |   | |_| | | | | |     / _ \\ | | | | | | | |_) |  _| \\___ \\ \t|");
        throwInfo("|\t  / ___ \\ |_| | | |  | |  | || |_| |___) | | |___|  _  | |_| | |___ / ___ \\| |_| | |_| |  _ <| |___ ___) |\t|");
        throwInfo("|\t /_/   \\_\\___/  |_| |___| |_| \\___/|____/   \\____|_| |_|\\___/ \\____/_/   \\_\\____/ \\___/|_| \\_\\_____|____/ \t|");
        throwInfo("|_______________________________________________________________________________________________________________________|");
        throwInfo("");

        throwImportante("Seleccione una opcion para comenzar:");
        throwInfo("a) Jugar");
        throwInfo("b) Registrar un nuevo jugador");
        throwInfo("c) Ver ranking");
        throwInfo("d) Finalizar juego");
        throwInfo("_".repeat(40));
        String input = pedirDatoString("Ingrese la opcion: ");

        return input.charAt(0);
    }

    public static void mostrarRanking(ArrayList<Jugador> listaJugadores) {
        Collections.sort(listaJugadores);

        String titulo = "RANKING";
        int anchoTotal = 75;
        String formato = "| %-12s | %-8d | %-8d | %-8d | %-12d | %-8d |%n";
        System.out.println("-".repeat(anchoTotal));
        System.out.printf("%" + (anchoTotal / 2 + titulo.length() / 2) + "s%n", titulo);
        System.out.println("-".repeat(anchoTotal));
        System.out.format("| %-12s | %-8s | %-8s | %-8s | %-12s | %-8s |%n", "ALIAS", "PARTIDAS", "GANADAS", "PERDIDAS", "ABANDONADAS", "PUNTAJE");
        System.out.println("-".repeat(anchoTotal));

        for (Jugador jugador : listaJugadores) {
            String aliasTruncado = truncarAlias(jugador.getAlias());
            System.out.format(formato,
                    aliasTruncado,
                    jugador.getPartidasGanadas() + jugador.getPartidasPerdidas() + jugador.getPartidasAbandonadas(),
                    jugador.getPartidasGanadas(),
                    jugador.getPartidasPerdidas(),
                    jugador.getPartidasAbandonadas(),
                    jugador.getPuntaje());
        }
        System.out.println("-".repeat(anchoTotal));

        pedirDatoString("Presione Enter para volver al menu.");
    }

    public static String truncarAlias(String alias) {
        String aliasFormateado = alias;
        if (alias.length() > 9) {
            aliasFormateado = alias.substring(0, 9) + "...";
        }
        return aliasFormateado;
    }

    public static void throwInfo(String mensaje) {
        System.out.println(mensaje);
    }

    public static void throwImportante(String mensaje) {
        System.out.println("\033[1m" + mensaje + "\u001B[0m");
    }

    public static void throwError(String mensaje) {
        System.out.println("\u001B[31m" + mensaje + "\u001B[0m");
    }

    public static String pedirDatoString(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensaje);
        String dato = scanner.nextLine();
        return dato.trim();
    }

    public static int pedirDatoNumerico(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(mensaje);
        int dato = -1;
        try {
            dato = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            throwError("Error: formato de ingreso incorrecto. Ingrese un numero entero.");
            scanner.nextLine();
        }
        return dato;
    }

    public static void renderizarTablero(Tablero tablero) {
        int dim = tablero.getDimension();
        Autito[][] configuracion = tablero.getConfiguracion();

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
                    if (configuracion[i][j] == null) {
                        representacion = new String[]{"    ", "    ", "    ", "    "};
                    } else {
                        representacion = renderizarAutito(configuracion[i][j]);
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

    public static String[] renderizarAutito(Autito autito) {
        String[] configuracion;
        String RESET = "\u001B[0m";
        String RED_TEXT_YELLOW_BG = "\u001B[31;43m";

        String colorANSI = autito.getColor().toString();

        switch (autito.getDireccion()) {
            case 0: // Arriba
                configuracion = new String[]{
                    " " + RED_TEXT_YELLOW_BG + "oo" + RESET + " ",
                    colorANSI + " ** " + RESET,
                    colorANSI + " ** " + RESET,
                    colorANSI + " ** " + RESET
                };
                break;
            case 1: // Derecha
                configuracion = new String[]{
                    "    ",
                    colorANSI + "***" + RED_TEXT_YELLOW_BG + "o" + RESET,
                    colorANSI + "***" + RED_TEXT_YELLOW_BG + "o" + RESET,
                    "    "
                };
                break;
            case 2: // Abajo
                configuracion = new String[]{
                    colorANSI + " ** " + RESET,
                    colorANSI + " ** " + RESET,
                    colorANSI + " ** " + RESET,
                    " " + RED_TEXT_YELLOW_BG + "oo" + RESET + " "
                };
                break;
            case 3: // Izquierda
                configuracion = new String[]{
                    "    ",
                    RED_TEXT_YELLOW_BG + "o" + RESET + colorANSI + "***" + RESET,
                    RED_TEXT_YELLOW_BG + "o" + RESET + colorANSI + "***" + RESET,
                    "    "
                };
                break;
            default:
                configuracion = new String[]{"    ", "    ", "    ", "    "};
        }
        return configuracion;
    }

    public static void mostrarInstruccionesPartida() {
        throwImportante("En cada turno, los jugadores pueden optar por las siguientes opciones:");
        throwInfo("1) Ingresar las coordenadas del auto que desean seleccionar para chocar. Ejemplo: 'A3'");
        throwInfo("2) Ingresar 'S' para solicitar la lista de autos que pueden ser seleccionados (-1 punto).");
        throwInfo("3) Ingresar 'X' para abandonar la partida (pierde la partida).");
        throwInfo("4) Ingresar 'R' para rotar el tablero en sentido horario (pierde el turno).");
        throwInfo("_".repeat(40));
    }

    public static void listar(ArrayList<String> lista) {
        if (lista.isEmpty()) {
            throwImportante("La lista está vacía.");
        } else {
            for (String s : lista) {
                throwInfo("\t- " + s);
            }
        }
    }

    public static void informarResultadoPartida(Jugador ganador, Jugador perdedor, boolean huboAbandono) {
        throwInfo(" _______________________________________________________________________________________");
        throwInfo("|\t  _____ ___ _   _   ____  _____   ____   _    ____ _____ ___ ____    _    \t|");
        throwInfo("|\t |  ___|_ _| \\ | | |  _ \\| ____| |  _ \\ / \\  |  _ \\_   _|_ _|  _ \\  / \\   \t|");
        throwInfo("|\t | |_   | ||  \\| | | | | |  _|   | |_) / _ \\ | |_) || |  | || | | |/ _ \\  \t|");
        throwInfo("|\t |  _|  | || |\\  | | |_| | |___  |  __/ ___ \\|  _ < | |  | || |_| / ___ \\ \t|");
        throwInfo("|\t |_|   |___|_| \\_| |____/|_____| |_| /_/   \\_\\_| \\_\\|_| |___|____/_/   \\_\\\t|");
        throwInfo("|_______________________________________________________________________________________|");

        throwInfo("");
        throwInfo("Ganador: " + ganador + " - Puntaje total: " + ganador.getPuntaje());
        System.out.print("Perdedor: " + perdedor + " - Puntaje total: " + perdedor.getPuntaje());
        if (huboAbandono) {
            throwInfo(" (Abandono)");
        }
        throwInfo("");

        pedirDatoString("Presione Enter para volver al menu principal.");
    }
}
