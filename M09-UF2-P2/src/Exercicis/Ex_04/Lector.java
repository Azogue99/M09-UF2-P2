package Exercicis.Ex_04;

import java.util.Random;

public class Lector implements Runnable { // Classe que representa un lector que llegeix articles de forma concurrent.
    private final String nomLector; // Nom del lector.
    private final Article[] articles; // Array d'articles disponibles per llegir.
    private final int numLectures; // Nombre de vegades que el lector llegirà.

    /**
     * Constructor de la classe Lector.
     *
     * @param nom Nom del lector.
     * @param articles Array d'articles sobre els quals pot llegir.
     * @param numLectures Nombre de lectures que realitzarà.
     */
    public Lector(String nom, Article[] articles, int numLectures) {
        this.nomLector = nom;
        this.articles = articles;
        this.numLectures = numLectures;
    }

    @Override
    public void run() {
        Random rand = new Random(); // Objecte per seleccionar articles de manera aleatòria.

        for (int i = 0; i < numLectures; i++) {
            // Seleccionem un article a l'atzar.
            int index = rand.nextInt(articles.length);
            Article article = articles[index];

            try {
                // El lector demana permís per començar a llegir.
                article.startReading(nomLector);

                // Llegeix el contingut de l'article.
                String contingut = article.readContent();
                System.out.println(nomLector + " està llegint article [" + article.getTitle()
                        + "], contingut: " + contingut);

                // Finalitza la lectura i allibera l'article.
                article.endReading(nomLector);
            } catch (InterruptedException e) {
                System.out.println("Error en la lectura: " + e.getMessage());
            }
        }

        // Quan el lector ha acabat totes les seves lectures, imprimeix un missatge final.
        System.out.println(nomLector + " HA ACABAT de llegir.");
    }
}
