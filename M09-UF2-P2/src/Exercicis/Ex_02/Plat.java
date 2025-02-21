package Exercicis.Ex_02;

/**
 * Classe que representa un plat en un restaurant.
 * Cada plat té un nom i un identificador únic.
 */
public class Plat {
    private final String nom; // Nom del plat (ex. "Pizza", "Sopa").
    private final int id; // Identificador únic del plat.

    /**
     * Constructor de la classe Plat.
     *
     * @param nom Nom del plat.
     * @param id Identificador únic del plat.
     */
    public Plat(String nom, int id) {
        this.nom = nom;
        this.id = id;
    }

    /**
     * Retorna el nom del plat.
     *
     * @return Nom del plat.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retorna l'identificador del plat.
     *
     * @return ID del plat.
     */
    public int getId() {
        return id;
    }

    /**
     * Retorna una representació en format String del plat.
     *
     * @return Representació del plat en format "[Plat ID: Nom]".
     */
    @Override
    public String toString() {
        return "[Plat " + id + ": " + nom + "]";
    }
}
