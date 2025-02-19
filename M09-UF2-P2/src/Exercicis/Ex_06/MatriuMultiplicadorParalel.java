package Exercicis.Ex_06;

public class MatriuMultiplicadorParalel {

    /**
     * Multiplica A (n x m) per B (m x p) i retorna C (n x p)
     * @param A matriu de mida n x m
     * @param B matriu de mida m x p
     * @param numThreads nombre de fils a utilitzar
     * @return matriu C resultant (n x p)
     */
    public static double[][] multiplica(double[][] A, double[][] B, int numThreads) {
        int n = A.length;        // files d'A
        //int m = A[0].length;     // columnes d'A (i files de B)
        int p = B[0].length;     // columnes de B

        // Creem la matriu C de mida n x p
        double[][] C = new double[n][p];

        // Creem els fils
        Thread[] threads = new Thread[numThreads];

        // Dividim les files d'A (i de C) entre els fils
        int filesPerFil = (n + numThreads - 1) / numThreads; 
        // (això és per repartir encara que no sigui múltiple)

        for (int i = 0; i < numThreads; i++) {
            int filaInici = i * filesPerFil;
            int filaFi = Math.min(filaInici + filesPerFil, n);

            // Creem el worker
            Worker worker = new Worker(A, B, C, filaInici, filaFi);
            threads[i] = new Thread(worker, "Worker-" + i);
            threads[i].start();
        }

        // Esperem que tots els fils acabin
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Fil interromput: " + e.getMessage());
            }
        }

        return C;
    }

    // Classe interna Worker
    private static class Worker implements Runnable {
        private final double[][] A;
        private final double[][] B;
        private final double[][] C;
        private final int filaInici, filaFi;

        public Worker(double[][] A, double[][] B, double[][] C, int filaInici, int filaFi) {
            this.A = A;
            this.B = B;
            this.C = C;
            this.filaInici = filaInici;
            this.filaFi = filaFi;
        }

        @Override
        public void run() {
            // Dimensions
            int m = A[0].length;     // coincideix amb B.length
            int p = B[0].length;     // columnes de B (i de C)

            for (int i = filaInici; i < filaFi; i++) {
                for (int j = 0; j < p; j++) {
                    double sum = 0.0;
                    for (int k = 0; k < m; k++) {
                        sum += A[i][k] * B[k][j];
                    }
                    C[i][j] = sum;
                }
            }
        }
    }
}

