package Exercicis.Ex_04;

import java.util.Random;

public class Escriptor implements Runnable {
    private final String nomEscriptor;
    private final Article[] articles;
    private final int numEscriptures; // Quantes vegades escriurà

    public Escriptor(String nom, Article[] articles, int numEscriptures) {
        this.nomEscriptor = nom;
        this.articles = articles;
        this.numEscriptures = numEscriptures;
    }

    @Override
    public void run() {
        Random rand = new Random();

        for (int i = 0; i < numEscriptures; i++) {
            // Triar un article a l'atzar
            int index = rand.nextInt(articles.length);
            Article article = articles[index];

            try {
                // Inici d'escriptura
                article.startWriting(nomEscriptor);

                // Afegim alguna frase al contingut
                String textAfegit = "(Nova aportació de " + nomEscriptor + " " + i + ")";
                article.writeContent(textAfegit);
                System.out.println(nomEscriptor + " està ESCRIVINT al article [" 
                                   + article.getTitle() + "]: " + textAfegit);

                // Fi de l'escriptura
                article.endWriting(nomEscriptor);
            } catch (InterruptedException e) {
                System.out.println("Error en l'escriptura: " + e.getMessage());
            }
        }

        System.out.println(nomEscriptor + " HA ACABAT d'escriure.");
    }
}