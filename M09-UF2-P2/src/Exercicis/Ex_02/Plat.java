package Exercicis.Ex_02;

public class Plat {
    private final String nom;
    private final int id;

    public Plat(String nom, int id) {
        this.nom = nom;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[Plat " + id + ": " + nom + "]";
    }
}