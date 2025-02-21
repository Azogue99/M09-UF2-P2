package Exercicis.Ex_02;

/**
 * Classe que representa un cuiner que prepara plats i els col·loca al passaplats.
 */
public class Cuiner implements Runnable {
    private final Passaplats passaplats; // Referència al passaplats compartit.
    private final String nomCuiner; // Nom del cuiner.
    private final String[] platsTipics; // Varietat de plats que pot preparar.
    private final int platsAFer; // Nombre de plats que el cuiner ha de preparar.

    /**
     * Constructor de la classe Cuiner.
     *
     * @param passaplats Objecte compartit on es col·loquen els plats preparats.
     * @param nomCuiner Nom identificatiu del cuiner.
     * @param platsTipics Llista de noms de plats típics que pot cuinar.
     * @param platsAFer Nombre de plats que el cuiner ha de fer abans d'acabar.
     */
    public Cuiner(Passaplats passaplats, String nomCuiner, String[] platsTipics, int platsAFer) {
        this.passaplats = passaplats;
        this.nomCuiner = nomCuiner;
        this.platsTipics = platsTipics;
        this.platsAFer = platsAFer;
    }

    /**
     * Mètode executat pel fil. Simula el procés de preparar i col·locar plats al passaplats.
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < platsAFer; i++) {
                // Seleccionem un plat aleatoriament de la llista de plats típics.
                String platNom = platsTipics[(int) (Math.random() * platsTipics.length)];

                // Creem un objecte Plat amb un identificador únic (id = i).
                Plat plat = new Plat(platNom, i);

                // Col·loquem el plat al passaplats (això pot bloquejar si el passaplats està ple).
                passaplats.posarPlat(plat);

                System.out.println(nomCuiner + " ha preparat el " + plat);
            }
        } catch (InterruptedException e) {
            System.out.println("Error en cuinar: " + e.getMessage());
        }

        // Quan el cuiner ha preparat tots els plats, imprimeix un missatge de finalització.
        System.out.println(nomCuiner + " ha acabat de CUINAR.");
    }
}
