package Exercicis.Ex_03;

/**
 * Classe que representa un compte bancari amb operacions concurrents.
 * Tots els mètodes són sincronitzats per garantir l'accés segur en un entorn multithreading.
 */
public class CompteBancari {
    private double saldo; // Saldo actual del compte.

    /**
     * Constructor de la classe CompteBancari.
     *
     * @param saldoInicial Saldo inicial amb el qual es crea el compte.
     */
    public CompteBancari(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    /**
     * Mètode sincronitzat per ingressar diners al compte.
     *
     * Garanteix que només un fil pot modificar el saldo al mateix temps.
     *
     * @param quantitat Quantitat a ingressar (ha de ser superior a 0).
     */
    public synchronized void ingressar(double quantitat) {
        if (quantitat > 0) {
            saldo += quantitat; // Augmentem el saldo amb la quantitat ingressada.
            System.out.println(Thread.currentThread().getName() + " INGRESSA " + quantitat
                    + " €. Saldo actual: " + saldo);
        } else {
            System.out.println(Thread.currentThread().getName() + " intent d'ingrés invàlid.");
        }
    }

    /**
     * Mètode sincronitzat per retirar diners del compte.
     *
     * Garanteix que només un fil pot modificar el saldo al mateix temps.
     *
     * @param quantitat Quantitat a retirar (ha de ser superior a 0 i menor o igual al saldo disponible).
     * @return true si la retirada s'ha pogut fer, false si no hi ha saldo suficient.
     */
    public synchronized boolean retirar(double quantitat) {
        if (quantitat > 0 && quantitat <= saldo) {
            saldo -= quantitat; // Reduïm el saldo amb la quantitat retirada.
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
     * Mètode sincronitzat per consultar el saldo del compte.
     *
     * Garanteix que l'accés al saldo és segur en un entorn multithreading.
     *
     * @return El saldo actual del compte.
     */
    public synchronized double consultarSaldo() {
        return saldo;
    }
}
