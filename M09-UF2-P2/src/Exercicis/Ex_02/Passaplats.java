package Exercicis.Ex_02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Classe que representa un passaplats en un restaurant.
 * S'utilitza com a estructura compartida entre cuiners (productors) i cambrers (consumidors).
 * Implementa un mecanisme de sincronització per evitar condicions de carrera.
 */
public class Passaplats {
    private final Queue<Plat> cuaPlats; // Cua de plats en espera de ser servits.
    private final int MAX_CAP; // Capacitat màxima del passaplats.

    /**
     * Constructor de la classe Passaplats.
     *
     * @param maxCap Capacitat màxima del passaplats (nombre màxim de plats que pot contenir).
     */
    public Passaplats(int maxCap) {
        this.MAX_CAP = maxCap;
        this.cuaPlats = new LinkedList<>();
    }

    /**
     * Mètode sincronitzat per afegir un plat al passaplats (cridat pels cuiners).
     *
     * @param plat Plat a afegir a la cua.
     * @throws InterruptedException Si el fil és interromput mentre espera espai disponible.
     */
    public synchronized void posarPlat(Plat plat) throws InterruptedException {
        // Si el passaplats està ple, esperem fins que hi hagi espai.
        while (cuaPlats.size() == MAX_CAP) {
            wait();  // El cuiner espera fins que un cambrer agafi un plat.
        }

        // Afegim el plat al passaplats.
        cuaPlats.add(plat);
        System.out.println("CUINER ha posat: " + plat);

        // Notifiquem als cambrers que hi ha almenys un plat disponible per servir.
        notifyAll();
    }

    /**
     * Mètode sincronitzat per agafar un plat del passaplats (cridat pels cambrers).
     *
     * @return El plat que ha estat recollit pel cambrer.
     * @throws InterruptedException Si el fil és interromput mentre espera plats disponibles.
     */
    public synchronized Plat agafarPlat() throws InterruptedException {
        // Si el passaplats està buit, esperem fins que hi hagi plats disponibles.
        while (cuaPlats.isEmpty()) {
            wait();  // El cambrer espera fins que un cuiner posi un plat.
        }

        // Agafem el primer plat disponible (FIFO).
        Plat plat = cuaPlats.remove();
        System.out.println("CAMBRER ha agafat: " + plat);

        // Notifiquem als cuiners que hi ha espai per posar nous plats.
        notifyAll();

        return plat;
    }
}
