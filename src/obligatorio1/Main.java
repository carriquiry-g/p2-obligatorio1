package obligatorio1;

import java.util.Scanner;
import modelo.Sistema;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);

        sistema.mostrarMenu();
    }
}
