import Exercicis.Ex_01.*;
import Exercicis.Ex_02.*;
import Exercicis.Ex_03.*;
import Exercicis.Ex_04.*;
import Exercicis.Ex_05.*;
import Exercicis.Ex_06.*;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        Boolean close = false;

        do {
            System.out.println("----------------------");
            System.out.println("Selecciona l'exercici:");
            System.out.println("----------------------");
            System.out.println("- 0 - Sortir");
            System.out.println("- 1 - Reserva seients teatre");
            System.out.println("- 2 - Passaplats");
            System.out.println("- 3 - Caixer Automàtic");
            System.out.println("- 4 - Articles, Lectors i Escriptors");
            System.out.println("- 5 - Sala d\'Espera en un Consultori Mèdic");
            System.out.println("- 6 - Multiplicació de matrius en paral·lel");

            int choice = scanner.nextInt();

            switch (choice) {
                case 0 -> close=true;
                case 1 -> Ex_01.main(args);
                case 2 -> Ex_02.main(args);
                case 3 -> Ex_03.main(args);
                case 4 -> Ex_04.main(args);
                case 5 -> Ex_05.main(args);
                case 6 -> Ex_06.main(args);
                default -> System.out.println("Selecció incorrecta, siusplau tria una de la llista");
            }
        } while (!close);

        scanner.close();
    }
}
