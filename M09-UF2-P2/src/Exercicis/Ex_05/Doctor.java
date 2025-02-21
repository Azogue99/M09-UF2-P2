package Exercicis.Ex_05;

import Exercicis.Utilities;

public class Doctor implements Runnable { // Classe que representa un doctor que atén pacients en una sala d'espera.
    private final String nom; // Nom del doctor.
    private final SalaEspera sala; // Referència a la sala d'espera on es gestionen els pacients.
    private boolean acabar = false; // Variable de control per saber si el doctor ha de parar.

    // Constructor que inicialitza el nom del doctor i la sala d'espera.
    public Doctor(String nom, SalaEspera sala) {
        this.nom = nom;
        this.sala = sala;
    }

    @Override
    public void run() { // Mètode que defineix el comportament del doctor quan s'executa en un fil.
        while (!acabar) { // Mentre no es demani acabar el torn...
            try {
                // Obté el següent pacient de la sala d'espera.
                Pacient p = sala.obtenirSeguentPacient();

                // Mostra un missatge indicant que el doctor està atenent el pacient.
                // Els colors s'utilitzen per ressaltar parts del text:
                // - Cian pel prefix i sufix
                // - Blanc pel nom del pacient
                Utilities.printMultiColored(Utilities.CYAN, "** " + nom + " ATÉN a ",
                        Utilities.WHITE, p.toString(),
                        Utilities.CYAN, " **");

                // Simula el temps d'atenció al pacient amb una pausa de 500ms.
                Thread.sleep(500);

                // Notifica a altres fils que poden continuar (per exemple, altres pacients esperant).
                synchronized (sala) {
                    sala.notifyAll();
                }
            } catch (InterruptedException e) { // Si el fil és interromput...
                Utilities.error(nom + " interromput."); // Mostra un missatge d'error.
                break; // Surt del bucle.
            }
        }
        // Un cop finalitza el torn, es mostra un missatge indicant-ho.
        Utilities.printHeader(nom + " acaba la seva jornada.");
    }

    // Mètode per indicar que el doctor ha de finalitzar el seu torn.
    public void acabarTorn() {
        acabar = true;
    }
}
