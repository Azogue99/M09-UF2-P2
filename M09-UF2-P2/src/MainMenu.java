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
        System.out.println("Selecciona l'exercici:");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> Ex_01.main(args);
            case 2 -> Ex_02.main(args);
            case 3 -> Ex_03.main(args);
            case 4 -> Ex_04.main(args);
            case 5 -> Ex_05.main(args);
            case 6 -> Ex_06.main(args);
            default -> System.out.println("Selecció incorrecta, siusplau tria una de la llista");
        }

        scanner.close();
    }
}
