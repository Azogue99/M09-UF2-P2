package Exercicis.Ex_01;

public class Ex_01 {
    public static void main(String[] args) {
        // 1. Crear zones
        ZonaTeatre platea = new ZonaTeatre("Platea", 30);
        ZonaTeatre amfiteatre = new ZonaTeatre("Amfiteatre", 10);
        ZonaTeatre galeria = new ZonaTeatre("Galeria", 15);

        // 2. Crear objectes Client (Runnable) amb diferents peticions
        Client c1 = new Client("Client1", platea, 5);
        Client c2 = new Client("Client2", platea, 10);
        Client c3 = new Client("Client3", amfiteatre, 4);
        Client c4 = new Client("Client4", galeria, 10);
        Client c5 = new Client("Client5", amfiteatre, 7); // Overbooking intencionat

        // 3. Crear Threads a partir dels clients
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread t3 = new Thread(c3);
        Thread t4 = new Thread(c4);
        Thread t5 = new Thread(c5);

        // 4. Llançar els fils
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        // 5. Esperar que acabin per poder veure el resum final
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            System.out.println("Error esperant que acabin els fils.");
        }

        // 6. Comprovació final de butaques lliures
        System.out.println("\n-- Resultat final --");
        System.out.println("Platea: " + platea.getButaquesLliures() + " lliures.");
        System.out.println("Amfiteatre: " + amfiteatre.getButaquesLliures() + " lliures.");
        System.out.println("Galeria: " + galeria.getButaquesLliures() + " lliures.");
    }
}
