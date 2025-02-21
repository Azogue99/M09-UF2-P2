package Exercicis.Ex_03;

import java.util.Random;

public class Client implements Runnable { // Classe que representa un client que realitza operacions en comptes bancaris.
    private final CompteBancari[] comptes; // Array de comptes en els quals el client pot operar.
    private final int numOperacions; // Nombre d'operacions que realitzarà el client.

    /**
     * Constructor de la classe Client.
     *
     * @param comptes Array de comptes bancaris accessibles pel client.
     * @param numOperacions Nombre total d'operacions que realitzarà.
     */
    public Client(CompteBancari[] comptes, int numOperacions) {
        this.comptes = comptes;
        this.numOperacions = numOperacions;
    }

    @Override
    public void run() {
        Random random = new Random(); // Objecte per generar valors aleatoris.

        for (int i = 0; i < numOperacions; i++) {
            // Seleccionem un compte bancari a l'atzar.
            int idxCompte = random.nextInt(comptes.length);
            CompteBancari compteSeleccionat = comptes[idxCompte];

            // Triem una operació a l'atzar: 0 = ingressar, 1 = retirar, 2 = consultar saldo.
            int operacio = random.nextInt(3);

            switch (operacio) {
                case 0 -> {
                    // Ingressar una quantitat entre 1 i 100 euros.
                    double quantitatIng = 1 + random.nextInt(100);
                    compteSeleccionat.ingressar(quantitatIng);
                }
                case 1 -> {
                    // Retirar una quantitat entre 1 i 100 euros.
                    double quantitatRet = 1 + random.nextInt(100);
                    compteSeleccionat.retirar(quantitatRet);
                }
                case 2 -> {
                    // Consultar saldo actual del compte.
                    double saldo = compteSeleccionat.consultarSaldo();
                    System.out.println(Thread.currentThread().getName()
                            + " CONSULTA saldo en compte " + idxCompte
                            + ": " + saldo + " €");
                }
            }
        }

        // Un cop finalitzades totes les operacions, es mostra un missatge.
        System.out.println(Thread.currentThread().getName() + " ha finalitzat les seves operacions.");
    }
}
