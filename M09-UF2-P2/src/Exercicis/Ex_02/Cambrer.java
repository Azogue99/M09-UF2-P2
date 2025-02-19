package Exercicis.Ex_02;

public class Cambrer implements Runnable {
    private final Passaplats passaplats;
    private final String nomCambrer;
    private final int platsAServir; // Quants plats vol agafar abans d'acabar

    public Cambrer(Passaplats passaplats, String nomCambrer, int platsAServir) {
        this.passaplats = passaplats;
        this.nomCambrer = nomCambrer;
        this.platsAServir = platsAServir;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < platsAServir; i++) {
                // Agafar un plat del passaplats
                Plat plat = passaplats.agafarPlat();
                System.out.println(nomCambrer + " està servint el " + plat);
            }

        } catch (InterruptedException e) {
            System.out.println("Error en servir: " + e.getMessage());
        }

        System.out.println(nomCambrer + " ha acabat de SERVIR.");
    }
}

