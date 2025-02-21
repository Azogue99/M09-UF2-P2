package Exercicis.Ex_01;

/**
 * Classe que representa una zona del teatre amb un conjunt de butaques.
 * Permet reservar butaques de manera concurrent.
 */
public class ZonaTeatre {
    private final String nomZona; // Nom de la zona del teatre.
    private final boolean[] butaques; // Array de butaques (false = lliure, true = ocupada).

    /**
     * Constructor de la classe ZonaTeatre.
     *
     * @param nomZona Nom de la zona del teatre.
     * @param numButaques Nombre total de butaques disponibles en la zona.
     */
    public ZonaTeatre(String nomZona, int numButaques) {
        this.nomZona = nomZona;
        this.butaques = new boolean[numButaques]; // Inicialment totes les butaques estan lliures (false).
    }

    /**
     * Mètode sincronitzat per reservar 'n' butaques en la zona.
     * Garanteix que només un fil pugui modificar l'estat de les butaques a la vegada.
     *
     * @param n Nombre de butaques que es volen reservar.
     * @return true si la reserva s'ha fet correctament, false si no hi ha prou butaques lliures.
     */
    public synchronized boolean reservar(int n) {
        // 1. Comprovem quantes butaques lliures queden.
        int lliures = 0;
        for (boolean ocupada : butaques) {
            if (!ocupada) lliures++;
        }

        // 2. Si no hi ha prou butaques disponibles, la reserva no és possible.
        if (lliures < n) {
            System.out.println("[" + nomZona + "] No hi ha prou butaques lliures per reservar " + n + " seient(s).");
            return false;
        }

        // 3. Si hi ha prou butaques, reservem les primeres disponibles.
        int countReservades = 0;
        for (int i = 0; i < butaques.length && countReservades < n; i++) {
            if (!butaques[i]) {
                butaques[i] = true; // Marquem la butaca com a ocupada.
                countReservades++;
            }
        }

        // 4. Mostrem un missatge de confirmació i retornem true.
        System.out.println("[" + nomZona + "] S'han reservat " + n + " butaques correctament.");
        return true;
    }

    /**
     * Mètode sincronitzat per consultar quantes butaques lliures queden en la zona.
     * Aquest mètode és només informatiu i no altera l'estat de les butaques.
     *
     * @return Nombre de butaques lliures disponibles.
     */
    public synchronized int getButaquesLliures() {
        int lliures = 0;
        for (boolean ocupada : butaques) {
            if (!ocupada) lliures++;
        }
        return lliures;
    }

    /**
     * Retorna el nom de la zona del teatre.
     *
     * @return Nom de la zona.
     */
    public String getNomZona() {
        return nomZona;
    }
}

