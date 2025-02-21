package Exercicis.Ex_04;

public class Ex_04 {
    public static void main(String[] args) {
        // 1. Creem alguns articles amb text inicial
        Article art1 = new Article("Article1", "Text inicial de l'article 1");
        Article art2 = new Article("Article2", "Text inicial de l'article 2");
        Article art3 = new Article("Article3", "Text inicial de l'article 3");

        // Agrupem els articles en un array per facilitar-ne la gestió
        Article[] articles = { art1, art2, art3 };

        // 2. Creem alguns lectors (cadascun llegirà 5 vegades)
        Lector lector1 = new Lector("Lector1", articles, 5);
        Lector lector2 = new Lector("Lector2", articles, 5);
        Lector lector3 = new Lector("Lector3", articles, 5);
        Lector lector4 = new Lector("Lector4", articles, 5);

        // 3. Creem alguns escriptors (cadascun escriurà 3 vegades)
        Escriptor escriptor1 = new Escriptor("Escriptor1", articles, 3);
        Escriptor escriptor2 = new Escriptor("Escriptor2", articles, 3);

        // 4. Llancem els lectors i escriptors com a fils
        Thread tL1 = new Thread(lector1);
        Thread tL2 = new Thread(lector2);
        Thread tL3 = new Thread(lector3);
        Thread tL4 = new Thread(lector4);

        Thread tE1 = new Thread(escriptor1);
        Thread tE2 = new Thread(escriptor2);

        // Iniciem els fils
        tL1.start();
        tL2.start();
        tL3.start();
        tL4.start();

        tE1.start();
        tE2.start();

        // 5. Esperem que tots els fils acabin abans de continuar
        try {
            tL1.join();
            tL2.join();
            tL3.join();
            tL4.join();
            tE1.join();
            tE2.join();
        } catch (InterruptedException e) {
            System.out.println("Error en join: " + e.getMessage());
        }

        // 6. Imprimim l'estat final dels articles després de les lectures i escriptures
        System.out.println("\n--- Estat final dels articles ---");
        for (Article a : articles) {
            System.out.println(a.getTitle() + ": " + a.readContent());
        }

        // 7. Missatge de finalització
        System.out.println("--- Fi de l'execució ---");
    }
}
