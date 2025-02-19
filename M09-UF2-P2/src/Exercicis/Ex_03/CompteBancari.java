package Exercicis.Ex_03;

public class CompteBancari {
    private double saldo;

    public CompteBancari(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    /**
     * Mètode sincronitzat per ingressar diners.
     * @param quantitat quantitat a ingressar (ha de ser > 0)
     */
    public synchronized void ingressar(double quantitat) {
        if (quantitat > 0) {
            saldo += quantitat;
            System.out.println(Thread.currentThread().getName() + " INGRESSA " + quantitat 
                               + " €. Saldo actual: " + saldo);
        }
    }

    /**
     * Mètode sincronitzat per retirar diners.
     * @param quantitat quantitat a retirar (ha de ser > 0 i <= saldo disponible)
     * @return true si la retirada s'ha pogut fer, false si no hi ha saldo suficient
     */
    public synchronized boolean retirar(double quantitat) {
        if (quantitat > 0 && quantitat <= saldo) {
            saldo -= quantitat;
            System.out.println(Thread.currentThread().getName() + " RETIRA " + quantitat 
                               + " €. Saldo actual: " + saldo);
            return true;
        } else {
            System.out.println(Thread.currentThread().getName() 
                    + " NO POT RETIRAR " + quantitat + " €. Saldo insuficient.");
            return false;
        }
    }

    /**
     * Mètode sincronitzat per consultar el saldo.
     * @return el saldo actual
     */
    public synchronized double consultarSaldo() {
        return saldo;
    }
}

