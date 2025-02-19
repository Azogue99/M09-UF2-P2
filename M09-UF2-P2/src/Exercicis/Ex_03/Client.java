package Exercicis.Ex_03;

import java.util.Random;

public class Client implements Runnable {
    private final CompteBancari[] comptes;   // Array (o llista) de comptes on aquest client pot operar
    private final int numOperacions;         // Quantes operacions volem fer en total

    public Client(CompteBancari[] comptes, int numOperacions) {
        this.comptes = comptes;
        this.numOperacions = numOperacions;
    }

    @Override
    public void run() {
        Random random = new Random();
        
        for (int i = 0; i < numOperacions; i++) {
            // Triem un compte a l'atzar
            int idxCompte = random.nextInt(comptes.length);
            CompteBancari compteSeleccionat = comptes[idxCompte];

            // Triem una operació a l'atzar: 0 = ingressar, 1 = retirar, 2 = consultar
            int operacio = random.nextInt(3);

            switch (operacio) {
                case 0 -> {
                    // Ingressar una quantitat entre 1 i 100
                    double quantitatIng = 1 + random.nextInt(100);
                    compteSeleccionat.ingressar(quantitatIng);
                }
                case 1 -> {
                    // Retirar una quantitat entre 1 i 100
                    double quantitatRet = 1 + random.nextInt(100);
                    compteSeleccionat.retirar(quantitatRet);
                }
                case 2 -> {
                    // Consultar saldo
                    double saldo = compteSeleccionat.consultarSaldo();
                    System.out.println(Thread.currentThread().getName() 
                            + " CONSULTA saldo en compte " + idxCompte 
                            + ": " + saldo + " €");
                }
            }

        }

        System.out.println(Thread.currentThread().getName() + " ha finalitzat les seves operacions.");
    }
}

