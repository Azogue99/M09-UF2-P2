package Exercicis.Ex_02;

public class Cuiner implements Runnable {
    private final Passaplats passaplats;
    private final String nomCuiner;
    private final String[] platsTipics;  // Varietat de plats
    private final int platsAFer;        // Quants plats vol fer abans d'acabar

    public Cuiner(Passaplats passaplats, String nomCuiner, String[] platsTipics, int platsAFer) {
        this.passaplats = passaplats;
        this.nomCuiner = nomCuiner;
        this.platsTipics = platsTipics;
        this.platsAFer = platsAFer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < platsAFer; i++) {
                // Escollim un nom de plat aleatòriament
                String platNom = platsTipics[(int) (Math.random() * platsTipics.length)];

                // Creem un objecte Plat (id = i, per exemple)
                Plat plat = new Plat(platNom, i);

                // Posem el plat al Passaplats
                passaplats.posarPlat(plat);

                System.out.println(nomCuiner + " ha preparat el " + plat);
            }
        } catch (InterruptedException e) {
            System.out.println("Error en cuinar: " + e.getMessage());
        }

        System.out.println(nomCuiner + " ha acabat de CUINAR.");
    }
}

