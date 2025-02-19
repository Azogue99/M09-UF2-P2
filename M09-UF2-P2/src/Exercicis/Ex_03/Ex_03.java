package Exercicis.Ex_03;

public class Ex_03 {
    public static void main(String[] args) {
        // 1. Creem diversos comptes bancaris amb saldos inicials
        CompteBancari c1 = new CompteBancari(500);
        CompteBancari c2 = new CompteBancari(1000);
        CompteBancari c3 = new CompteBancari(2000);

        // Posem-los en un array perquè els clients hi puguin accedir
        CompteBancari[] totsElsComptes = { c1, c2, c3 };

        // 2. Creem diversos fils (Clients) que fan operacions
        // Cada client farà, per exemple, 10 operacions
        Client client1 = new Client(totsElsComptes, 10);
        Client client2 = new Client(totsElsComptes, 10);
        Client client3 = new Client(totsElsComptes, 10);
        Client client4 = new Client(totsElsComptes, 10);

        // 3. Convertim cada Client en un Thread
        Thread t1 = new Thread(client1, "Client1");
        Thread t2 = new Thread(client2, "Client2");
        Thread t3 = new Thread(client3, "Client3");
        Thread t4 = new Thread(client4, "Client4");

        // 4. Iniciem els fils
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // 5. Esperem que tots acabin (join)
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            System.out.println("Error en join: " + e.getMessage());
        }

        // 6. Al final, mostrem els saldos finals per veure en quin estat han quedat
        System.out.println("\n-- Saldos finals --");
        System.out.println("Compte 1: " + c1.consultarSaldo() + " €");
        System.out.println("Compte 2: " + c2.consultarSaldo() + " €");
        System.out.println("Compte 3: " + c3.consultarSaldo() + " €");

        System.out.println("--- Fi de l'execució ---");
    }
}
