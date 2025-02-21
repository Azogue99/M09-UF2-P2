package Exercicis.Ex_04;

import java.util.Random;

public class Escriptor implements Runnable { // Classe que representa un escriptor que afegeix contingut a articles.
    private final String nomEscriptor; // Nom de l'escriptor.
    private final Article[] articles; // Llista d'articles en els quals pot escriure.
    private final int numEscriptures; // Nombre d'escriptures que realitzarà l'escriptor.

    /**
     * Constructor de la classe Escriptor.
     *
     * @param nom Nom de l'escriptor.
     * @param articles Llista d'articles disponibles per escriure-hi.
     * @param numEscriptures Nombre de vegades que aquest escriptor afegirà contingut.
     */
    public Escriptor(String nom, Article[] articles, int numEscriptures) {
        this.nomEscriptor = nom;
        this.articles = articles;
        this.numEscriptures = numEscriptures;
    }

    @Override
    public void run() {
        Random rand = new Random(); // Objecte per generar números aleatoris.

        for (int i = 0; i < numEscriptures; i++) {
            // Seleccionem un article a l'atzar de la llista.
            int index = rand.nextInt(articles.length);
            Article article = articles[index];

            try {
                // L'escriptor demana permís per començar a escriure.
                article.startWriting(nomEscriptor);

                // Afegim una frase nova al contingut de l'article.
                String textAfegit = "(Nova aportació de " + nomEscriptor + " " + i + ")";
                article.writeContent(textAfegit);
                System.out.println(nomEscriptor + " està ESCRIVINT a l'article ["
                        + article.getTitle() + "]: " + textAfegit);

                // L'escriptor finalitza l'escriptura i allibera l'article.
                article.endWriting(nomEscriptor);
            } catch (InterruptedException e) {
                System.out.println("Error en l'escriptura: " + e.getMessage());
            }
        }

        // Quan l'escriptor ha acabat totes les seves escriptures, imprimeix un missatge final.
        System.out.println(nomEscriptor + " HA ACABAT d'escriure.");
    }
}
