package Exercicis.Ex_05;

public class Doctor implements Runnable {
    private final String nom;
    private final SalaEspera sala;
    private boolean acabar = false;  // si volem aturar el doctor

    /**
     * Constructor
     * @param nom Nom del doctor
     * @param sala Sala d'espera de la qual obté els pacients
     */
    public Doctor(String nom, SalaEspera sala) {
        this.nom = nom;
        this.sala = sala;
    }

    /**
     * Bucle principal del fil Doctor:
     * - Mentre no s'hagi indicat "acabar":
     *   - Agafa el següent pacient de la sala (wait() si està buida).
     *   - L'atén i mostra per pantalla.
     *   - Notifica la sala si cal.
     */
    @Override
    public void run() {
        while (!acabar) {
            try {
                // Obtenir pacient (pot bloquejar si cua és buida)
                Pacient p = sala.obtenirSeguentPacient();
                System.out.println("** " + nom + " atén " + p + " **");

                // Suposem que el temps d'atenció és curt, p. ex. dormim un moment
                // (No és requeriment, però serveix d'exemple)
                Thread.sleep(500);

                // En acabar, notifiquem per si algú espera un lloc a la sala (no és del tot necessari)
                synchronized (sala) {
                    sala.notifyAll();
                }

            } catch (InterruptedException e) {
                System.out.println(nom + " ha estat interromput.");
                break;  // sortim del while
            }
        }
        System.out.println(nom + " ha finalitzat la seva jornada.");
    }

    /**
     * Mètode per demanar al Doctor que acabi el seu bucle.
     */
    public void acabarTorn() {
        this.acabar = true;
    }
}
