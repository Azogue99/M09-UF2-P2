package Exercicis.Ex_02;

import java.util.LinkedList;
import java.util.Queue;

public class Passaplats {
    private final Queue<Plat> cuaPlats;
    private final int MAX_CAP;

    public Passaplats(int maxCap) {
        this.MAX_CAP = maxCap;
        this.cuaPlats = new LinkedList<>();
    }

    // Mètode sincronitzat per al CUINER (productor)
    public synchronized void posarPlat(Plat plat) throws InterruptedException {
        // Si el passaplats està ple, esperem
        while (cuaPlats.size() == MAX_CAP) {
            wait();  // Espera fins que algú alliberi espai
        }

        // Afegim el plat
        cuaPlats.add(plat);
        System.out.println("CUINER ha posat: " + plat);

        // Notifiquem que hi ha almenys un plat disponible
        notifyAll();
    }

    // Mètode sincronitzat per al CAMBRER (consumidor)
    public synchronized Plat agafarPlat() throws InterruptedException {
        // Si el passaplats està buit, esperem
        while (cuaPlats.isEmpty()) {
            wait();  // Espera fins que algú posi algun plat
        }

        // Agafem el primer plat (FIFO)
        Plat plat = cuaPlats.remove();
        System.out.println("CAMBRER ha agafat: " + plat);

        // Notifiquem que hi ha espai per posar plats
        notifyAll();

        return plat;
    }
}
