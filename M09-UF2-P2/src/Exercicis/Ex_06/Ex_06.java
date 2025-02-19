package Exercicis.Ex_06;

import java.util.Random;
import java.util.Arrays;

public class Ex_06 {

    public static void main(String[] args) {
        int n = 6;   // files d'A
        int m = 4;   // columnes d'A (i files de B)
        int p = 5;   // columnes de B
        int numThreads = 2;

        double[][] A = generaMatriuAleatoria(n, m);
        double[][] B = generaMatriuAleatoria(m, p);

        System.out.println("Matriu A:");
        imprimeixMatriu(A);

        System.out.println("Matriu B:");
        imprimeixMatriu(B);

        // Multiplicació en paral·lel
        double[][] C = MatriuMultiplicadorParalel.multiplica(A, B, numThreads);

        System.out.println("Resultat (C = A x B):");
        imprimeixMatriu(C);

        // (Opcional) Podríem fer la multiplicació seqüencial per comparar
        double[][] Cseq = multiplicaSeq(A, B);
        System.out.println("Resultat Seqüencial (per comparar):");
        imprimeixMatriu(Cseq);

        // Comprovació (simple) que C i Cseq siguin "iguals"
        boolean iguals = comparaMatrius(C, Cseq);
        System.out.println("Les dues matrius resultants són iguals? " + iguals);
    }

    private static double[][] generaMatriuAleatoria(int files, int cols) {
        Random rand = new Random();
        double[][] mat = new double[files][cols];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = rand.nextInt(5);  // valors de 0 a 4
            }
        }
        return mat;
    }

    private static void imprimeixMatriu(double[][] mat) {
        for (double[] fila : mat) {
            System.out.println(Arrays.toString(fila));
        }
        System.out.println();
    }

    // Multiplicació seqüencial (per validació)
    private static double[][] multiplicaSeq(double[][] A, double[][] B) {
        int n = A.length;
        int m = A[0].length;
        int p = B[0].length;

        double[][] C = new double[n][p];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                double sum = 0;
                for (int k = 0; k < m; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }
        return C;
    }

    // Compara si dues matrius tenen tots els elements iguals
    private static boolean comparaMatrius(double[][] M1, double[][] M2) {
        if (M1.length != M2.length) return false;
        if (M1[0].length != M2[0].length) return false;

        for (int i = 0; i < M1.length; i++) {
            for (int j = 0; j < M1[0].length; j++) {
                if (M1[i][j] != M2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
