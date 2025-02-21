package Exercicis.Ex_06;

import java.util.Random;
import java.util.Arrays;

public class Ex_06 {

    public static void main(String[] args) {
        int n = 6;   // Nombre de files de la matriu A.
        int m = 4;   // Nombre de columnes de A (i files de B).
        int p = 5;   // Nombre de columnes de la matriu B.
        int numThreads = 2; // Nombre de fils per a la multiplicació en paral·lel.

        // Generació de matrius aleatòries A i B.
        double[][] A = generaMatriuAleatoria(n, m);
        double[][] B = generaMatriuAleatoria(m, p);

        // Imprimim les matrius generades.
        System.out.println("Matriu A:");
        imprimeixMatriu(A);

        System.out.println("Matriu B:");
        imprimeixMatriu(B);

        // Multiplicació de matrius en paral·lel.
        double[][] C = MatriuMultiplicadorParalel.multiplica(A, B, numThreads);

        System.out.println("Resultat (C = A x B):");
        imprimeixMatriu(C);

        // Multiplicació seqüencial per comparar els resultats.
        double[][] Cseq = multiplicaSeq(A, B);
        System.out.println("Resultat Seqüencial (per comparar):");
        imprimeixMatriu(Cseq);

        // Comprovació si les matrius resultants (paral·lela i seqüencial) són iguals.
        boolean iguals = comparaMatrius(C, Cseq);
        System.out.println("Les dues matrius resultants són iguals? " + iguals);
    }

    /**
     * Genera una matriu aleatòria amb valors enters entre 0 i 4.
     *
     * @param files Nombre de files de la matriu.
     * @param cols Nombre de columnes de la matriu.
     * @return Matriu generada aleatòriament.
     */
    private static double[][] generaMatriuAleatoria(int files, int cols) {
        Random rand = new Random();
        double[][] mat = new double[files][cols];
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = rand.nextInt(5);  // Valors entre 0 i 4.
            }
        }
        return mat;
    }

    /**
     * Imprimeix una matriu per pantalla en format llegible.
     *
     * @param mat Matriu a imprimir.
     */
    private static void imprimeixMatriu(double[][] mat) {
        for (double[] fila : mat) {
            System.out.println(Arrays.toString(fila));
        }
        System.out.println();
    }

    /**
     * Multiplica dues matrius de manera seqüencial.
     *
     * @param A Matriu A (n x m).
     * @param B Matriu B (m x p).
     * @return Matriu resultant de la multiplicació (n x p).
     */
    private static double[][] multiplicaSeq(double[][] A, double[][] B) {
        int n = A.length;
        int m = A[0].length;
        int p = B[0].length;

        double[][] C = new double[n][p];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                double sum = 0;
                for (int k = 0; k < m; k++) {
                    sum += A[i][k] * B[k][j]; // Multipliquem i sumem els valors corresponents.
                }
                C[i][j] = sum; // Assignem el resultat a la matriu C.
            }
        }
        return C;
    }

    /**
     * Compara si dues matrius tenen exactament els mateixos valors en cada posició.
     *
     * @param M1 Primera matriu.
     * @param M2 Segona matriu.
     * @return True si les matrius són iguals, false en cas contrari.
     */
    private static boolean comparaMatrius(double[][] M1, double[][] M2) {
        if (M1.length != M2.length) return false; // Comprovem que tenen el mateix nombre de files.
        if (M1[0].length != M2[0].length) return false; // Comprovem que tenen el mateix nombre de columnes.

        for (int i = 0; i < M1.length; i++) {
            for (int j = 0; j < M1[0].length; j++) {
                if (M1[i][j] != M2[i][j]) {
                    return false; // Si trobem una diferència, retornem false.
                }
            }
        }
        return true; // Si totes les posicions coincideixen, les matrius són iguals.
    }
}
