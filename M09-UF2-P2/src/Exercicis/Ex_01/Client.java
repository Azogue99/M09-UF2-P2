package Exercicis.Ex_01;

/**
 * Classe que representa un client que intenta reservar butaques en una zona del teatre.
 * Implementa Runnable per permetre l'execució concurrent en fils.
 */
public class Client implements Runnable {
    private final String nomClient; // Nom del client.
    private final ZonaTeatre zona; // Zona del teatre on vol reservar.
    private final int numeroButaques; // Nombre de butaques que vol reservar.

    /**
     * Constructor de la classe Client.
     *
     * @param nomClient Nom del client.
     * @param zona Zona del teatre on es vol fer la reserva.
     * @param numeroButaques Nombre de butaques a reservar.
     */
    public Client(String nomClient, ZonaTeatre zona, int numeroButaques) {
        this.nomClient = nomClient;
        this.zona = zona;
        this.numeroButaques = numeroButaques;
    }

    /**
     * Mètode executat quan el fil s'executa.
     * El client intenta fer una reserva a la zona especificada.
     */
    @Override
    public void run() {
        // Intentar reservar les butaques
        boolean reservat = zona.reservar(numeroButaques);

        // Mostrar si la reserva ha estat exitosa o no
        if (reservat) {
            System.out.println(nomClient + " ha reservat " + numeroButaques
                    + " butaques a " + zona.getNomZona() + ".");
        } else {
            System.out.println(nomClient + " NO ha pogut reservar " + numeroButaques
                    + " butaques a " + zona.getNomZona() + ".");
        }
    }
}
