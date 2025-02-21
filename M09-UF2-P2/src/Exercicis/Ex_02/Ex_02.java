package Exercicis.Ex_02;

/**
 * Classe principal per executar la simulació del passaplats d'un restaurant.
 * Es crea un entorn amb cuiners que preparen plats i cambrers que els serveixen.
 */
public class Ex_02 {
    public static void main(String[] args) {
        // 1. Creem el passaplats amb una capacitat màxima de plats
        Passaplats passaplats = new Passaplats(4); // Capacitat màxima: 4 plats

        // 2. Definim els plats típics que cada cuiner pot preparar
        String[] platsCuiner1 = {"Pizza", "Amanida", "Pasta"};
        String[] platsCuiner2 = {"Sopa", "Arròs", "Carn"};

        // 3. Creem els objectes Cuiner (productors), que prepararan 5 plats cadascun
        Cuiner cuiner1 = new Cuiner(passaplats, "Cuiner1", platsCuiner1, 5);
        Cuiner cuiner2 = new Cuiner(passaplats, "Cuiner2", platsCuiner2, 5);

        // 4. Creem els objectes Cambrer (consumidors), que serviran 5 plats cadascun
        Cambrer cambrer1 = new Cambrer(passaplats, "Cambrer1", 5);
        Cambrer cambrer2 = new Cambrer(passaplats, "Cambrer2", 5);

        // 5. Creem els fils per als cuiners i cambrers
        Thread tCuiner1 = new Thread(cuiner1);
        Thread tCuiner2 = new Thread(cuiner2);
        Thread tCambrer1 = new Thread(cambrer1);
        Thread tCambrer2 = new Thread(cambrer2);

        // 6. Iniciem els fils (els cuiners comencen a cuinar i els cambrers a servir)
        tCuiner1.start();
        tCuiner2.start();
        tCambrer1.start();
        tCambrer2.start();

        // 7. Esperem que tots els fils acabin abans de finalitzar el programa
        try {
            tCuiner1.join();
            tCuiner2.join();
            tCambrer1.join();
            tCambrer2.join();
        } catch (InterruptedException e) {
            System.out.println("Error en esperar que els fils acabin: " + e.getMessage());
        }

        // 8. Imprimim un missatge final quan tots els fils han acabat.
        System.out.println("\n--- Fi de l'execució del Restaurant ---");
    }
}
