package Exercicis.Ex_05;

public class Pacient implements Runnable, Comparable<Pacient> {
    private final String nom;
    private final boolean urgent;
    private final SalaEspera sala;
    private static int contadorId = 1;  // genera un ID únic per a cada pacient
    private final int id;

    /**
     * Constructor
     * @param nom Nom del pacient
     * @param urgent Indica si té prioritat urgent (true) o és normal (false)
     * @param sala Sala d'espera on intentarà entrar
     */
    public Pacient(String nom, boolean urgent, SalaEspera sala) {
        this.nom = nom;
        this.urgent = urgent;
        this.sala = sala;
        this.id = contadorId++;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public String getNom() {
        return nom;
    }

    /**
     * Implementem compareTo per atendre primer els pacients urgents.
     * Si tots dos són igual d'urgents, anirà primer el que tingui ID més petit
     * (és a dir, el que hagi arribat abans).
     */
    @Override
    public int compareTo(Pacient altre) {
        // Si aquest és urgent i l'altre no, aquest va primer
        if (this.urgent && !altre.urgent) {
            return -1;
        }
        // Si l'altre és urgent i aquest no, l'altre va primer
        if (!this.urgent && altre.urgent) {
            return 1;
        }
        // Si són igual d'urgents, comparem per ID (menor ID = més prioritat)
        return Integer.compare(this.id, altre.id);
    }

    /**
     * Codi que executa el fil Pacient.
     * - Intenta entrar a la sala
     * - Si entra, fa un print i "espera" que el Doctor l'atengui. (El mètode poll()
     *   de la sala l'acabarà traient de la cua, i no tornem a fer res al pacient.)
     * - Si no pot entrar (sala plena), printa que marxa.
     */
    @Override
    public void run() {
        boolean haEntrat = sala.entrarSala(this);
        if (!haEntrat) {
            System.out.println(nom + " troba la sala plena i MARXA.");
            return;
        }
        System.out.println(nom + " espera que el doctor l'atengui (urgent=" + urgent + ").");
        // Aquí acaba el run(): el pacient roman a la cua fins que el doctor l'extregui.
    }

    /**
     * Mètode toString per mostrar el pacient de forma clara.
     */
    @Override
    public String toString() {
        return "[Pacient " + id + " " + nom + (urgent ? " (URGENT)" : "") + "]";
    }
}
