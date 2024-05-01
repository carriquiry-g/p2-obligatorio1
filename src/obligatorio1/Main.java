package obligatorio1;

import java.util.Scanner;
import modelo.Sistema;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Autitos Chocadores");
        System.out.println("Seleccione una opcion para comenzar:");
        System.out.println("a) Jugar");
        System.out.println("b) Registrar un nuevo jugador");
        System.out.println("c) Ver Ranking");
        System.out.println("-".repeat(20));
        System.out.print("Ingrese la opcion: ");

        String input = scanner.nextLine();
        switch (input.charAt(0)) {
            case 'a':
                break;
            case 'b':
                sistema.registrarJugador();
                break;
            case 'c':
                sistema.mostrarRanking();
                break;
            default:
                break;
        }

    }
}
