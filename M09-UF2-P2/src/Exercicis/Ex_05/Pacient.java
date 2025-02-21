package Exercicis.Ex_05;

import Exercicis.Utilities;

public class Pacient implements Runnable, Comparable<Pacient> { // Classe que representa un pacient en la sala d'espera.
    private final String nom; // Nom del pacient.
    private final boolean urgent; // Indica si el pacient és urgent.
    private final SalaEspera sala; // Referència a la sala d'espera.
    private static int contadorId = 1; // Contador estàtic per assignar identificadors únics als pacients.
    private final int id; // Identificador únic del pacient.

    // Banderes per indicar l'estat del pacient.
    private volatile boolean atendido = false; // Indica si el pacient ha estat atès.
    private volatile boolean expulsado = false; // Indica si el pacient ha estat expulsat.

    // Constructor que inicialitza les dades del pacient i assigna un ID únic.
    public Pacient(String nom, boolean urgent, SalaEspera sala) {
        this.nom = nom;
        this.urgent = urgent;
        this.sala = sala;
        this.id = contadorId++;
    }

    // Mètodes d'accés als atributs.
    public boolean isUrgent() {
        return urgent;
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    // Mètodes per modificar l'estat del pacient.
    public void setAtendido(boolean valor) {
        this.atendido = valor;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setExpulsado(boolean valor) {
        this.expulsado = valor;
    }

    public boolean isExpulsado() {
        return expulsado;
    }

    /**
     * Defineix l'ordre d'atenció dels pacients a la cua.
     * - Els pacients urgents tenen prioritat sobre els no urgents.
     * - Si tenen la mateixa prioritat, es prioritza el que té l'ID més baix (arribada abans).
     */
    @Override
    public int compareTo(Pacient altre) {
        if (this.urgent && !altre.urgent) // Els urgents tenen més prioritat.
            return -1;
        if (!this.urgent && altre.urgent) // Els no urgents tenen menys prioritat.
            return 1;
        return Integer.compare(this.id, altre.id); // Si tenen la mateixa urgència, el primer en arribar té prioritat.
    }

    /**
     * Executa el comportament del pacient quan s'executa com a fil.
     * - Intenta entrar a la sala d'espera.
     * - Si és atès, surt del bucle.
     * - Si és expulsat, ho intenta de nou després d'una petita espera.
     */
    @Override
    public void run() {
        while (true) {
            try {
                // El mètode entrarSala bloqueja fins que el pacient sigui atès o expulsat.
                boolean exito = sala.entrarSala(this);
                if (exito) {
                    // Si retorna true, el pacient ha estat atès pel doctor.
                    Utilities.printMultiColored(Utilities.GREEN,
                            nom + " (id=" + id + ") ha estat atès.");
                    break;
                } else {
                    // Si retorna false, significa que ha estat expulsat per donar lloc a un pacient urgent.
                    Utilities.printMultiColored(Utilities.RED,
                            nom + " (id=" + id + ") ha estat expulsat ",
                            Utilities.YELLOW, "i ho intentarà de nou...");
                    // Reiniciem la bandera per al següent intent.
                    this.setExpulsado(false);
                    // Espera un temps abans de tornar a intentar entrar.
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                // Si el pacient és interromput, no entra a la sala.
                Utilities.printMultiColored(Utilities.RED,
                        nom + " interromput, no entra a la sala.");
                return;
            }
        }
    }

    /**
     * Retorna una representació en format text del pacient.
     */
    @Override
    public String toString() {
        return "[Pacient " + id + " " + nom + (urgent ? " (URGENT)" : "") + "]";
    }
}
