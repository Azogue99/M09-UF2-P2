package Exercicis.Ex_02;

/**
 * Classe que representa un cambrer que agafa plats del passaplats i els serveix.
 */
public class Cambrer implements Runnable {
    private final Passaplats passaplats; // Referència al passaplats compartit.
    private final String nomCambrer; // Nom del cambrer.
    private final int platsAServir; // Nombre de plats que ha de servir abans de finalitzar.

    /**
     * Constructor del cambrer.
     *
     * @param passaplats Objecte compartit d'on es recullen els plats.
     * @param nomCambrer Nom identificatiu del cambrer.
     * @param platsAServir Nombre de plats que el cambrer ha de servir.
     */
    public Cambrer(Passaplats passaplats, String nomCambrer, int platsAServir) {
        this.passaplats = passaplats;
        this.nomCambrer = nomCambrer;
        this.platsAServir = platsAServir;
    }

    /**
     * Mètode executat pel fil. Simula el procés de servir plats.
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < platsAServir; i++) {
                // Agafar un plat del passaplats (això pot bloquejar si no hi ha plats disponibles).
                Plat plat = passaplats.agafarPlat();
                System.out.println(nomCambrer + " està servint el " + plat);
            }
        } catch (InterruptedException e) {
            System.out.println("Error en servir: " + e.getMessage());
        }

        // Un cop el cambrer ha servit tots els plats, imprimeix un missatge de finalització.
        System.out.println(nomCambrer + " ha acabat de SERVIR.");
    }
}
