package Exercicis.Ex_06;

public class MatriuMultiplicadorParalel {

    /**
     * Multiplica la matriu A (n x m) per la matriu B (m x p) i retorna la matriu resultant C (n x p).
     * La multiplicació es fa en paral·lel utilitzant diversos fils.
     *
     * @param A matriu de mida n x m
     * @param B matriu de mida m x p
     * @param numThreads nombre de fils a utilitzar per a la computació
     * @return matriu C resultant de mida n x p
     */
    public static double[][] multiplica(double[][] A, double[][] B, int numThreads) {
        int n = A.length;        // Nombre de files de la matriu A.
        //int m = A[0].length;   // Nombre de columnes d'A (i nombre de files de B).
        int p = B[0].length;     // Nombre de columnes de la matriu B.

        // Creem la matriu C de mida n x p per emmagatzemar el resultat.
        double[][] C = new double[n][p];

        // Creem un array de fils per gestionar el processament en paral·lel.
        Thread[] threads = new Thread[numThreads];

        // Determinem quantes files s'assignaran a cada fil.
        int filesPerFil = (n + numThreads - 1) / numThreads;
        // (Aquesta divisió assegura que es reparteixin bé les files, fins i tot si n no és múltiple de numThreads).

        for (int i = 0; i < numThreads; i++) {
            int filaInici = i * filesPerFil; // Primera fila assignada al fil actual.
            int filaFi = Math.min(filaInici + filesPerFil, n); // Última fila assignada (sense superar n).

            // Creem un worker que realitzarà la multiplicació per a aquestes files.
            Worker worker = new Worker(A, B, C, filaInici, filaFi);
            threads[i] = new Thread(worker, "Worker-" + i); // Creem el fil associat al worker.
            threads[i].start(); // Iniciem el fil.
        }

        // Esperem que tots els fils acabin la seva tasca abans de continuar.
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join(); // Espera que el fil i acabi abans de continuar.
            } catch (InterruptedException e) {
                System.out.println("Fil interromput: " + e.getMessage());
            }
        }

        return C; // Retornem la matriu resultant C.
    }

    // Classe interna Worker que realitza la multiplicació de matrius per a un subconjunt de files.
    private static class Worker implements Runnable {
        private final double[][] A;
        private final double[][] B;
        private final double[][] C;
        private final int filaInici, filaFi;

        /**
         * Constructor del worker.
         *
         * @param A matriu A (n x m)
         * @param B matriu B (m x p)
         * @param C matriu resultat (n x p)
         * @param filaInici primera fila que processarà aquest worker
         * @param filaFi última fila (no inclosa) que processarà aquest worker
         */
        public Worker(double[][] A, double[][] B, double[][] C, int filaInici, int filaFi) {
            this.A = A;
            this.B = B;
            this.C = C;
            this.filaInici = filaInici;
            this.filaFi = filaFi;
        }

        @Override
        public void run() {
            // Obtenim les dimensions de les matrius.
            int m = A[0].length;     // Nombre de columnes d'A (coincideix amb nombre de files de B).
            int p = B[0].length;     // Nombre de columnes de B (i nombre de columnes de C).

            // Multiplicació de matrius: calculem només per a les files assignades a aquest worker.
            for (int i = filaInici; i < filaFi; i++) {
                for (int j = 0; j < p; j++) {
                    double sum = 0.0;
                    for (int k = 0; k < m; k++) {
                        sum += A[i][k] * B[k][j]; // Suma de la multiplicació de la fila d'A amb la columna de B.
                    }
                    C[i][j] = sum; // Assignem el resultat a la posició corresponent de la matriu C.
                }
            }
        }
    }
}
