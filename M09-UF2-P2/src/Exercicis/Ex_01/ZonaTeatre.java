package Exercicis.Ex_01;

public class ZonaTeatre {
    private String nomZona;
    private boolean[] butaques;  // false = lliure, true = ocupada

    public ZonaTeatre(String nomZona, int numButaques) {
        this.nomZona = nomZona;
        this.butaques = new boolean[numButaques];
    }

    /**
     * Mètode sincronitzat per a reservar 'n' butaques.
     *
     * @param n nombre de butaques que es volen reservar
     * @return true si s'han pogut reservar correctament, false en cas contrari
     */
    public synchronized boolean reservar(int n) {
        // 1. Comprovem quantes butaques lliures queden
        int lliures = 0;
        for (boolean ocupada : butaques) {
            if (!ocupada) lliures++;
        }

        // 2. Si no n'hi ha prou, retornem false (reserva no possible)
        if (lliures < n) {
            System.out.println("[" + nomZona + "] No hi ha prou butaques lliures per reservar " + n + " seient(s).");
            return false;
        }

        // 3. Si n'hi ha prou, "bloquegem" les primeres n butaques lliures
        int countReservades = 0;
        for (int i = 0; i < butaques.length && countReservades < n; i++) {
            if (!butaques[i]) {
                butaques[i] = true;
                countReservades++;
            }
        }

        // 4. Retornem true si tot ha anat bé
        System.out.println("[" + nomZona + "] S'han reservat " + n + " butaques correctament.");
        return true;
    }

    /**
     * Mètode per consultar quantes butaques lliures queden (només informatiu).
     */
    public synchronized int getButaquesLliures() {
        int lliures = 0;
        for (boolean ocupada : butaques) {
            if (!ocupada) lliures++;
        }
        return lliures;
    }

    public String getNomZona() {
        return nomZona;
    }
}
