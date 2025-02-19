package Exercicis.Ex_02;

public class Ex_02 {
    public static void main(String[] args) {
        // 1. Creem el Passaplats amb capacitat màxima
        Passaplats passaplats = new Passaplats(4); // Per exemple, 4 plats màxim

        // 2. Creem matrius de plats típics per als cuiners
        String[] platsCuiner1 = {"Pizza", "Amanida", "Pasta"};
        String[] platsCuiner2 = {"Sopa", "Arròs", "Carn"};

        // 3. Creem els objectes Cuiner (productors)
        Cuiner cuiner1 = new Cuiner(passaplats, "Cuiner1", platsCuiner1, 5);
        Cuiner cuiner2 = new Cuiner(passaplats, "Cuiner2", platsCuiner2, 5);

        // 4. Creem els objectes Cambrer (consumidors)
        Cambrer cambrer1 = new Cambrer(passaplats, "Cambrer1", 5);
        Cambrer cambrer2 = new Cambrer(passaplats, "Cambrer2", 5);

        // 5. Passem-los a fils
        Thread tCuiner1 = new Thread(cuiner1);
        Thread tCuiner2 = new Thread(cuiner2);
        Thread tCambrer1 = new Thread(cambrer1);
        Thread tCambrer2 = new Thread(cambrer2);

        // 6. Iniciem els fils
        tCuiner1.start();
        tCuiner2.start();
        tCambrer1.start();
        tCambrer2.start();

        // 7. Esperem que tots acabin (join) per finalitzar el programa
        try {
            tCuiner1.join();
            tCuiner2.join();
            tCambrer1.join();
            tCambrer2.join();
        } catch (InterruptedException e) {
            System.out.println("Error en esperar que els fils acabin: " + e.getMessage());
        }

        System.out.println("\n--- Fi de l'execució del Restaurant ---");
    }
}