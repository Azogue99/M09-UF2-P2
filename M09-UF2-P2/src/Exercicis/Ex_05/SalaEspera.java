package Exercicis.Ex_05;

import java.util.PriorityQueue;

public class SalaEspera {
    private final PriorityQueue<Pacient> cuaPacients;
    private final int MAX_PAC;  // capacitat màxima de la sala

    /**
     * Constructor
     * @param maxPac capacitat màxima de pacients asseguts a la sala
     */
    public SalaEspera(int maxPac) {
        this.MAX_PAC = maxPac;
        // La PriorityQueue utilitza el compareTo de la classe Pacient,
        // de manera que primer en surt qui sigui urgent i amb ID més petit.
        this.cuaPacients = new PriorityQueue<>();
    }

    /**
     * Mètode perquè un pacient intenti entrar a la sala.
     * @param p El pacient que vol entrar
     * @return true si ha pogut entrar, false si la sala està plena
     */
    public synchronized boolean entrarSala(Pacient p) {
        if (cuaPacients.size() == MAX_PAC) {
            // Sala plena
            return false;
        }
        // Hi ha espai, el pacient entra
        cuaPacients.offer(p);
        System.out.println(p.getNom() + " HA ENTRAT a la sala (urg=" + p.isUrgent() + "). "
                + "Total a la sala: " + cuaPacients.size());

        // Notifiquem el Doctor (o altres fils) per si estan esperant
        notifyAll();
        return true;
    }

    /**
     * El Doctor obté (i treu) el següent pacient de la cua, seguint la prioritat.
     * Si la cua està buida, farà wait() fins que algú entri.
     * @return El pacient següent (o null, si no n'hi ha i no volem continuar)
     * @throws InterruptedException si el fil és interromput durant el wait()
     */
    public synchronized Pacient obtenirSeguentPacient() throws InterruptedException {
        // Mentre no hi hagi pacients, esperem
        while (cuaPacients.isEmpty()) {
            wait();  // Esperem que algú entri
        }
        // Ara sí que en tenim (cua no és buida), agafem el primer
        return cuaPacients.poll();
    }

    /**
     * Per si volem saber si la sala està buida (pot ajudar a decidir quan aturar el doctor)
     */
    public synchronized boolean estaBuida() {
        return cuaPacients.isEmpty();
    }
}
