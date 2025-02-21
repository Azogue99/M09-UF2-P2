import Exercicis.Ex_01.*;
import Exercicis.Ex_02.*;
import Exercicis.Ex_03.*;
import Exercicis.Ex_04.*;
import Exercicis.Ex_05.*;
import Exercicis.Ex_06.*;
import Exercicis.Utilities;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); // Scanner per llegir l'entrada de l'usuari.

        boolean close = false; // Variable per controlar la sortida del menú.
        while (!close) {
            // Imprimim un encapçalament atractiu amb fons vermell i text blanc.
            System.out.println(Utilities.BG_RED + Utilities.WHITE + "╔════════════════════════════════════════════╗" + Utilities.RESET);
            System.out.println(Utilities.BG_RED + Utilities.WHITE + "║           SELECCIONA L'EXERCICI            ║" + Utilities.RESET);
            System.out.println(Utilities.BG_RED + Utilities.WHITE + "╚════════════════════════════════════════════╝" + Utilities.RESET);
            Utilities.printDivider();

            // Imprimim les opcions del menú amb colors.
            Utilities.printMultiColored(Utilities.BLUE, "- 0 - ", Utilities.YELLOW, "Sortir");
            Utilities.printMultiColored(Utilities.BLUE, "- 1 - ", Utilities.YELLOW, "Reserva seients teatre");
            Utilities.printMultiColored(Utilities.BLUE, "- 2 - ", Utilities.YELLOW, "Passaplats");
            Utilities.printMultiColored(Utilities.BLUE, "- 3 - ", Utilities.YELLOW, "Caixer Automàtic");
            Utilities.printMultiColored(Utilities.BLUE, "- 4 - ", Utilities.YELLOW, "Articles, Lectors i Escriptors");
            Utilities.printMultiColored(Utilities.BLUE, "- 5 - ", Utilities.YELLOW, "Sala d'Espera en un Consultori Mèdic");
            Utilities.printMultiColored(Utilities.BLUE, "- 6 - ", Utilities.YELLOW, "Multiplicació de matrius en paral·lel");

            Utilities.printDivider();
            System.out.print(Utilities.CYAN + "Introdueix la teva opció: " + Utilities.RESET);
            int choice = scanner.nextInt(); // Llegim l'opció de l'usuari.

            // Comprovem l'opció triada i executem l'exercici corresponent.
            switch (choice) {
                case 0 -> close = true; // Si es tria 0, es tanca el menú.
                case 1 -> Ex_01.main(args); // Executa l'exercici 1.
                case 2 -> Ex_02.main(args); // Executa l'exercici 2.
                case 3 -> Ex_03.main(args); // Executa l'exercici 3.
                case 4 -> Ex_04.main(args); // Executa l'exercici 4.
                case 5 -> Ex_05.main(args); // Executa l'exercici 5.
                case 6 -> Ex_06.main(args); // Executa l'exercici 6.
                default -> Utilities.error("Selecció incorrecta, si us plau tria una de la llista"); // Missatge d'error si l'opció no és vàlida.
            }
        }
        scanner.close(); // Tanquem l'escàner quan sortim del menú.

        // Missatge final de comiat amb fons verd i text blanc.
        System.out.println(Utilities.BG_GREEN + Utilities.WHITE + "╔══════════════════════════╗" + Utilities.RESET);
        System.out.println(Utilities.BG_GREEN + Utilities.WHITE + "║       FINS AVIAT!        ║" + Utilities.RESET);
        System.out.println(Utilities.BG_GREEN + Utilities.WHITE + "╚══════════════════════════╝" + Utilities.RESET);
    }
}
