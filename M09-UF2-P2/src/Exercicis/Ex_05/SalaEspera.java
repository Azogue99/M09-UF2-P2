package Exercicis.Ex_05;

import Exercicis.Utilities;
import java.util.PriorityQueue;

public class SalaEspera { // Classe que representa una sala d'espera per a pacients.
    private final PriorityQueue<Pacient> cuaPacients; // Cua de pacients ordenada per prioritat.
    private final int MAX_PAC; // Capacitat màxima de la sala.

    // Constructor que inicialitza la sala d'espera amb una capacitat màxima determinada.
    public SalaEspera(int maxPac) {
        this.MAX_PAC = maxPac;
        this.cuaPacients = new PriorityQueue<>();
    }

    /**
     * Mètode sincronitzat que gestiona l'entrada d'un pacient a la sala.
     * El pacient queda bloquejat fins que sigui atès o expulsat.
     * Retorna true si el pacient ha estat atès, false si ha estat expulsat.
     */
    public synchronized boolean entrarSala(Pacient p) throws InterruptedException {
        // Si el pacient NO és urgent:
        if (!p.isUrgent()) {
            while (cuaPacients.size() == MAX_PAC) { // Si la sala està plena...
                if (p.isExpulsado()) { // Si el pacient ja ha estat expulsat, surt.
                    notifyAll(); // Notifiquem als altres fils per reavaluar condicions.
                    return false;
                }
                // El pacient ha d'esperar fins que hi hagi espai.
                Utilities.printMultiColored(Utilities.YELLOW, p.getNom() + " (id=" + p.getId() + ") sala plena => ESPERA...");
                wait(); // Bloqueja el pacient fins que hi hagi espai.
            }
            // Hi ha espai disponible, afegim el pacient a la cua.
            cuaPacients.offer(p);
            Utilities.printMultiColored(Utilities.GREEN, p.getNom() + " (NORMAL, id=" + p.getId() + ") ENTRA. [Total=" + cuaPacients.size() + "]");
        }
        // Si el pacient ÉS urgent:
        else {
            if (cuaPacients.size() < MAX_PAC) { // Si encara hi ha espai...
                cuaPacients.offer(p);
                Utilities.printMultiColored(Utilities.PURPLE, p.getNom() + " (URGENT, id=" + p.getId() + ") ENTRA. [Total=" + cuaPacients.size() + "]");
            } else {
                // Si la sala està plena, intentem expulsar un pacient normal (mai un d'urgent).
                Pacient ultimoNormal = findLastNormal();
                if (ultimoNormal == null) { // Si no hi ha pacients normals...
                    while (cuaPacients.size() == MAX_PAC) { // Espera espai lliure.
                        wait();
                    }
                    cuaPacients.offer(p);
                    Utilities.printMultiColored(Utilities.PURPLE, p.getNom() + " (URGENT, id=" + p.getId() + ") ENTRA. [Total=" + cuaPacients.size() + "]");
                } else {
                    // Expulsem el pacient normal amb ID més alt per donar lloc al pacient urgent.
                    cuaPacients.remove(ultimoNormal);
                    ultimoNormal.setExpulsado(true);
                    Utilities.printMultiColored(
                            Utilities.RED, "EXPULSEM a ",
                            Utilities.YELLOW, ultimoNormal.getNom() + " (id=" + ultimoNormal.getId() + ")",
                            Utilities.RED, " perquè entri ",
                            Utilities.PURPLE, p.getNom() + " (URGENT)."
                    );
                    cuaPacients.offer(p);
                    Utilities.printMultiColored(Utilities.PURPLE, p.getNom() + " (URGENT, id=" + p.getId() + ") ENTRA. [Total=" + cuaPacients.size() + "]");
                    notifyAll(); // Notifiquem als altres fils per actualitzar l'estat.
                }
            }
        }
        // Notifiquem qualsevol canvi a la cua per a pacients que esperen.
        notifyAll();

        // El pacient es queda bloquejat fins que sigui atès o expulsat.
        while (cuaPacients.contains(p)) {
            wait();
        }
        // Quan el pacient surt de la cua, significa que ha estat atès o expulsat.
        return p.isAtendido();
    }

    /**
     * Mètode sincronitzat que el doctor crida per obtenir el següent pacient de la cua.
     * Si la cua està buida, el doctor espera fins que arribi un pacient.
     */
    public synchronized Pacient obtenirSeguentPacient() throws InterruptedException {
        while (cuaPacients.isEmpty()) { // Si no hi ha pacients, espera.
            wait();
        }
        Pacient p = cuaPacients.poll(); // Extreu el pacient amb més prioritat de la cua.
        p.setAtendido(true); // Marquem el pacient com a atès.
        notifyAll(); // Notifiquem que hi ha un canvi a la cua.
        return p;
    }

    /**
     * Mètode sincronitzat per verificar si la sala està buida.
     */
    public synchronized boolean estaBuida() {
        return cuaPacients.isEmpty();
    }

    /**
     * Cerca el pacient normal amb l'ID més gran per ser expulsat en cas que un urgent necessiti entrar.
     * Mai es pot expulsar un pacient urgent.
     */
    private Pacient findLastNormal() {
        Pacient ultimo = null;
        for (Pacient p : cuaPacients) {
            if (!p.isUrgent()) { // Només considerem pacients normals.
                if (ultimo == null || p.getId() > ultimo.getId()) {
                    ultimo = p;
                }
            }
        }
        return ultimo;
    }
}
