package Exercicis.Ex_04;

import java.util.Random;

public class Lector implements Runnable {
    private final String nomLector;
    private final Article[] articles;    // Pot llegir entre diversos articles
    private final int numLectures;       // Quantes lectures realitzarà

    public Lector(String nom, Article[] articles, int numLectures) {
        this.nomLector = nom;
        this.articles = articles;
        this.numLectures = numLectures;
    }

    @Override
    public void run() {
        Random rand = new Random();

        for (int i = 0; i < numLectures; i++) {
            // Triar un article a l'atzar
            int index = rand.nextInt(articles.length);
            Article article = articles[index];

            try {
                // Inici de lectura
                article.startReading(nomLector);

                // Llegim el contingut (o fem la consulta que calgui)
                String contingut = article.readContent();
                System.out.println(nomLector + " està llegint article [" + article.getTitle() 
                                   + "], contingut: " + contingut);

                // Fi de lectura
                article.endReading(nomLector);
            } catch (InterruptedException e) {
                System.out.println("Error en la lectura: " + e.getMessage());
            }
        }

        System.out.println(nomLector + " HA ACABAT de llegir.");
    }
}
